package com.handy.support.recommend.process;

import com.handy.support.recommend.model.CourseDataModel;
import com.handy.support.recommend.recommender.CourseRecommender;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Recommend {
    private Recommender recommender;

    public MysqlDataSource getDataSource(){
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setServerName("106.13.106.249");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("handy");
        dataSource.setUser("handy");
        dataSource.setPassword("handycraft@205");
        return dataSource;
    }
    public boolean init(){
        try {
            DataModel firstGet = new MySQLJDBCDataModel(getDataSource(),"recommend","user_id"
                    ,"item_id","preference","update_time");
            FastByIDMap<PreferenceArray> preferences=((MySQLJDBCDataModel) firstGet).exportWithPrefs();
            DataModel dataModel = new CourseDataModel(preferences);
            /*FastByIDMap<PreferenceArray>preferences=
                    new FastByIDMap<PreferenceArray>();
            PreferenceArray preferences1=new GenericUserPreferenceArray(4);
            preferences1.setUserID(0,1L);
            preferences1.setItemID(0,1L);
            preferences1.setValue(0,10.0f);
            preferences1.setItemID(1,2L);
            preferences1.setValue(1,8.5f);
            preferences1.setItemID(2,3L);
            preferences1.setValue(2,7.5f);
            preferences1.setItemID(3,5L);
            preferences1.setValue(3,8.5f);
            preferences.put(1L,preferences1);
            PreferenceArray preferences2=new GenericUserPreferenceArray(4);
            preferences2.setUserID(0,2L);
            preferences2.setItemID(0,1L);
            preferences2.setValue(0,9.0f);
            preferences2.setItemID(1,3L);
            preferences2.setValue(1,10.4f);
            preferences2.setItemID(2,5L);
            preferences2.setValue(2,8.4f);
            preferences2.setItemID(3,7L);
            preferences2.setValue(3,8.2f);
            PreferenceArray preferences3=new GenericUserPreferenceArray(4);
            preferences3.setUserID(0,3L);
            preferences3.setItemID(0,3L);
            preferences3.setValue(0,8.0f);
            preferences3.setItemID(1,5L);
            preferences3.setValue(1,10.0f);
            preferences3.setItemID(2,6L);
            preferences3.setValue(2,9.0f);
            preferences3.setItemID(3,7L);
            preferences3.setValue(3,9.0f);
            preferences.put(1L,preferences1);
            preferences.put(2L,preferences2);
            preferences.put(3L,preferences3);
            DataModel dataModel=new GenericDataModel(preferences);*/
            recommender = new CourseRecommender(dataModel);
        }
        catch (TasteException ex){
            return false;
        }
        catch (IOException ex){
            return false;
        }
        return true;
    }
    public List<RecommendedItem> getRecommend(int uid){
        try {
            List<RecommendedItem>list=recommender.recommend(uid, 2);
            return list;
        }
        catch (TasteException ex){
            return null;
        }
    }
}
