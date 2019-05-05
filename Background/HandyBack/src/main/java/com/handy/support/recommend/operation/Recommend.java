package com.handy.support.recommend.operation;

import com.handy.support.entity.Course;
import com.handy.support.pojo.recommend.dto.UserItemLike;
import com.handy.support.recommend.model.CourseDataModel;
import com.handy.support.recommend.recommender.CourseRecommender;
import com.handy.support.service.Recommend.RecommendServiceImpl;
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
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class Recommend {

    private Recommender recommender;
    private CourseDataModel dataModel;
    private MysqlDataSource dataSource;
    @Autowired
   RecommendServiceImpl recommendService;
    /*
    * 获得数据库
    * */
    public MysqlDataSource getDataSource(){
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setServerName("106.13.106.249");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("handy");
        dataSource.setUser("handy");
        dataSource.setPassword("handycraft@205");
        return dataSource;
    }
    /*
    * 注入数据
    *
    * */
    public boolean init(){
        try {
            if(dataSource==null)
                dataSource=getDataSource();
            DataModel firstGet = new MySQLJDBCDataModel(dataSource,"recommend","user_id"
                    ,"item_id","preference","update_time");
            FastByIDMap<PreferenceArray> preferences=((MySQLJDBCDataModel) firstGet).exportWithPrefs();
            dataModel = new CourseDataModel(preferences);
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
    /*
    * 给出推荐
    * */
    public List<RecommendedItem> getRecommend(int uid,int page_no,int n){
        try {
            List<RecommendedItem>list=recommender.recommend(uid, (page_no+1)*n);
            List<RecommendedItem>list1=new ArrayList<RecommendedItem>();
            for(int i=page_no*n;i<(page_no+1)*n&&i<list.size();i++){
                list1.add(list.get(i));
            }
            return list1;
        }
        catch (TasteException ex){
            return null;
        }
    }
    public void refresh(){
       Date lastRefreshTime=dataModel.getLastRefreshTime();
       List<UserItemLike>updates=recommendService.getUpdates(lastRefreshTime);
    }
}
