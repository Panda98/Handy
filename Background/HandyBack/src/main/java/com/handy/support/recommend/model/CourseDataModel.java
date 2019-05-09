package com.handy.support.recommend.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.mahout.cf.taste.common.NoSuchItemException;
import org.apache.mahout.cf.taste.common.NoSuchUserException;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.AbstractDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericItemPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CourseDataModel extends AbstractDataModel {
    private static final Logger log = LoggerFactory.getLogger(GenericDataModel.class);
    private long[] userIDs;
    private FastByIDMap<PreferenceArray> preferenceFromUsers;
    private long[] itemIDs;
    private FastByIDMap<PreferenceArray> preferenceForItems;
    private FastByIDMap<FastByIDMap<Long>> timestamps;
    private Date lastRefreshTime;
    public CourseDataModel(FastByIDMap<PreferenceArray> userData) {
        this(userData, (FastByIDMap)null);
        lastRefreshTime=new Date();
    }
    public Date getLastRefreshTime(){
        return this.lastRefreshTime;
    }

    public CourseDataModel(FastByIDMap<PreferenceArray> userData, FastByIDMap<FastByIDMap<Long>> timestamps) {
        Preconditions.checkArgument(userData != null, "userData is null");
        this.preferenceFromUsers = userData;
        FastByIDMap<Collection<Preference>> prefsForItems = new FastByIDMap();
        FastIDSet itemIDSet = new FastIDSet();
        int currentCount = 0;
        float maxPrefValue = -1.0F / 0.0F;
        float minPrefValue = 1.0F / 0.0F;
        Iterator i$ = this.preferenceFromUsers.entrySet().iterator();

        Map.Entry entry;
        while(i$.hasNext()) {
            entry = (Map.Entry)i$.next();
            PreferenceArray prefs = (PreferenceArray)entry.getValue();
            prefs.sortByItem();
            Iterator i$2 = prefs.iterator();

            while(i$2.hasNext()) {
                Preference preference = (Preference)i$2.next();
                long itemID = preference.getItemID();
                itemIDSet.add(itemID);
                Collection<Preference> prefsForItem = (Collection)prefsForItems.get(itemID);
                if (prefsForItem == null) {
                    prefsForItem = Lists.newArrayListWithCapacity(2);
                    prefsForItems.put(itemID, prefsForItem);
                }

                ((Collection)prefsForItem).add(preference);
                float value = preference.getValue();
                if (value > maxPrefValue) {
                    maxPrefValue = value;
                }

                if (value < minPrefValue) {
                    minPrefValue = value;
                }
            }

            ++currentCount;//userNum
            if (currentCount % 10000 == 0) {
                log.info("Processed {} users", currentCount);
            }
        }

        log.info("Processed {} users", currentCount);
        this.setMinPreference(minPrefValue);
        this.setMaxPreference(maxPrefValue);
        this.itemIDs = itemIDSet.toArray();
        itemIDSet = null;
        Arrays.sort(this.itemIDs);
        this.preferenceForItems = toDataMap(prefsForItems, false);
        i$ = this.preferenceForItems.entrySet().iterator();

        while(i$.hasNext()) {
            entry = (Map.Entry)i$.next();
            ((PreferenceArray)entry.getValue()).sortByUser();
        }

        this.userIDs = new long[userData.size()];
        int i = 0;

        for(LongPrimitiveIterator it = userData.keySetIterator(); it.hasNext(); this.userIDs[i++] = (Long)it.next()) {
            ;
        }

        Arrays.sort(this.userIDs);
        this.timestamps = timestamps;
    }

    /** @deprecated */
    @Deprecated
    public CourseDataModel(DataModel dataModel) throws TasteException {
        this(toDataMap(dataModel));
    }

    public static FastByIDMap<PreferenceArray> toDataMap(FastByIDMap<Collection<Preference>> data, boolean byUser) {
        /*原版
        Iterator i$ = data.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry<Long, Object> entry = (Map.Entry)i$.next();
            List<Preference> prefList = (List)entry.getValue();
            entry.setValue(byUser ? new GenericUserPreferenceArray(prefList) : new GenericItemPreferenceArray(prefList));
        }
        return data;
        */
        FastByIDMap<PreferenceArray>newData=new FastByIDMap<PreferenceArray>();
        Iterator i$ = data.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry<Long, Object> entry = (Map.Entry)i$.next();
            List<Preference> prefList = (List)entry.getValue();
          //  entry.setValue(byUser ? new GenericUserPreferenceArray(prefList) : new GenericItemPreferenceArray(prefList));
            PreferenceArray value=(byUser ? new GenericUserPreferenceArray(prefList) : new GenericItemPreferenceArray(prefList));
            newData.put(entry.getKey(),value);
        }
        return newData;
    }

    public static FastByIDMap<PreferenceArray> toDataMap(DataModel dataModel) throws TasteException {
        FastByIDMap<PreferenceArray> data = new FastByIDMap(dataModel.getNumUsers());
        LongPrimitiveIterator it = dataModel.getUserIDs();

        while(it.hasNext()) {
            long userID = it.nextLong();
            data.put(userID, dataModel.getPreferencesFromUser(userID));
        }

        return data;
    }

    public FastByIDMap<PreferenceArray> getRawUserData() {
        return this.preferenceFromUsers;
    }

    public FastByIDMap<PreferenceArray> getRawItemData() {
        return this.preferenceForItems;
    }

    public LongPrimitiveArrayIterator getUserIDs() {
        return new LongPrimitiveArrayIterator(this.userIDs);
    }

    public PreferenceArray getPreferencesFromUser(long userID) throws NoSuchUserException {
        PreferenceArray prefs = (PreferenceArray)this.preferenceFromUsers.get(userID);
        if (prefs == null) {
            throw new NoSuchUserException(userID);
        } else {
            return prefs;
        }
    }

    public FastIDSet getItemIDsFromUser(long userID) throws TasteException {
        PreferenceArray prefs = this.getPreferencesFromUser(userID);
        int size = prefs.length();
        FastIDSet result = new FastIDSet(size);

        for(int i = 0; i < size; ++i) {
            result.add(prefs.getItemID(i));
        }

        return result;
    }

    public LongPrimitiveArrayIterator getItemIDs() {
        return new LongPrimitiveArrayIterator(this.itemIDs);
    }

    public PreferenceArray getPreferencesForItem(long itemID) throws NoSuchItemException {
        PreferenceArray prefs = (PreferenceArray)this.preferenceForItems.get(itemID);
        if (prefs == null) {
            throw new NoSuchItemException(itemID);
        } else {
            return prefs;
        }
    }

    public Float getPreferenceValue(long userID, long itemID) throws TasteException {
        PreferenceArray prefs = this.getPreferencesFromUser(userID);
        int size = prefs.length();

        for(int i = 0; i < size; ++i) {
            if (prefs.getItemID(i) == itemID) {
                return prefs.getValue(i);
            }
        }

        return null;
    }

    public Long getPreferenceTime(long userID, long itemID) throws TasteException {
        if (this.timestamps == null) {
            return null;
        } else {
            FastByIDMap<Long> itemTimestamps = (FastByIDMap)this.timestamps.get(userID);
            if (itemTimestamps == null) {
                throw new NoSuchUserException(userID);
            } else {
                return (Long)itemTimestamps.get(itemID);
            }
        }
    }

    public int getNumItems() {
        return this.itemIDs.length;
    }

    public int getNumUsers() {
        return this.userIDs.length;
    }

    public int getNumUsersWithPreferenceFor(long itemID) {
        PreferenceArray prefs1 = (PreferenceArray)this.preferenceForItems.get(itemID);
        return prefs1 == null ? 0 : prefs1.length();
    }

    public int getNumUsersWithPreferenceFor(long itemID1, long itemID2) {
        PreferenceArray prefs1 = (PreferenceArray)this.preferenceForItems.get(itemID1);
        if (prefs1 == null) {
            return 0;
        } else {
            PreferenceArray prefs2 = (PreferenceArray)this.preferenceForItems.get(itemID2);
            if (prefs2 == null) {
                return 0;
            } else {
                int size1 = prefs1.length();
                int size2 = prefs2.length();
                int count = 0;
                int i = 0;
                int j = 0;
                long userID1 = prefs1.getUserID(0);
                long userID2 = prefs2.getUserID(0);

                while(true) {
                    if (userID1 < userID2) {
                        ++i;
                        if (i == size1) {
                            break;
                        }

                        userID1 = prefs1.getUserID(i);
                    } else if (userID1 > userID2) {
                        ++j;
                        if (j == size2) {
                            break;
                        }

                        userID2 = prefs2.getUserID(j);
                    } else {
                        ++count;
                        ++i;
                        if (i == size1) {
                            break;
                        }

                        ++j;
                        if (j == size2) {
                            break;
                        }

                        userID1 = prefs1.getUserID(i);
                        userID2 = prefs2.getUserID(j);
                    }
                }

                return count;
            }
        }
    }
    public void addPreference(long userID, long itemID,float value)throws TasteException {
      //  PreferenceArray prefs = this.getPreferencesFromUser(userID);
            PreferenceArray prefs = this.preferenceFromUsers.get(userID);
            boolean hasItem=false;
            for(int i=0;i<this.itemIDs.length;i++){
                if(itemIDs[i]==itemID)
                    hasItem=true;
            }
            if(hasItem==false){
                long[]it=this.itemIDs;
                this.itemIDs=new long[it.length+1];
                for(int i=0;i<it.length;i++){
                    this.itemIDs[i]=it[i];
                }
                this.itemIDs[it.length]=itemID;
            }
        if(prefs==null){
            PreferenceArray temp=new GenericUserPreferenceArray(1);
            temp.setUserID(0,userID);
            temp.setItemID(0,itemID);
            temp.setValue(0,value);
            this.preferenceFromUsers.put(userID,temp);
            long[]idt=this.userIDs;
            this.userIDs=new long[idt.length+1];
            for(int i=0;i<idt.length;i++){
                this.userIDs[i]=idt[i];
            }
            this.userIDs[idt.length]=userID;
            return;
        }
        PreferenceArray temp=new GenericUserPreferenceArray(prefs.length()+1);
        int size = prefs.length();
        for(int i=0;i<size;i++){
            long id=prefs.getItemID(i);
            if(id==itemID){
                temp.setValue(i,value);
                return;
            }
            temp.setItemID(i,id);
           temp.setValue(i,prefs.getValue(i));
        }
        temp.setItemID(size,itemID);
        temp.setValue(size,value);
        this.preferenceFromUsers.put(userID,temp);
        // throw new UnsupportedOperationException();
    }
    public void removePreference(long userID, long itemID)throws TasteException {
        PreferenceArray prefs = this.getPreferencesFromUser(userID);
        PreferenceArray temp=new GenericUserPreferenceArray(prefs.length()-1);
        int size = prefs.length();
        boolean hasIt=false;
        for(int i=0;i<size;i++){
            long id=prefs.getItemID(i);
            if(id==itemID){
               hasIt=true;
            }
            temp.setItemID(i,id);
            temp.setValue(i,prefs.getValue(i));
        }
        if(hasIt)
        this.preferenceFromUsers.put(userID,temp);
       // throw new UnsupportedOperationException();
    }

    public void setPreference(long userID, long itemID, float value)throws TasteException {
        PreferenceArray prefs = this.getPreferencesFromUser(userID);
        int size = prefs.length();
        for(int i = 0; i < size; ++i) {
            if (prefs.getItemID(i) == itemID) {
                prefs.setValue(i,value);
               // return prefs.getValue(i);
            }
        }
     //   throw new UnsupportedOperationException();
    }

    public void refresh(Collection<Refreshable> alreadyRefreshed) {

    }

    public boolean hasPreferenceValues() {
        return true;
    }

    public String toString() {
        StringBuilder result = new StringBuilder(200);
        result.append("GenericDataModel[users:");

        for(int i = 0; i < Math.min(3, this.userIDs.length); ++i) {
            if (i > 0) {
                result.append(',');
            }

            result.append(this.userIDs[i]);
        }

        if (this.userIDs.length > 3) {
            result.append("...");
        }

        result.append(']');
        return result.toString();
    }
    public void setLastRefreshTime(Date lastRefreshTime){
        this.lastRefreshTime=lastRefreshTime;
    }
}
