package com.handy.support.service.Album;

import com.handy.support.entity.Album;
import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.album.vo.AlbumVO;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */
public class AlbumRecommend {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private MysqlDataSource dataSource;

    private List<RecommendedItem> recommendByUser(int uid){
        //todo: 推荐算法
        List<RecommendedItem> list = new ArrayList();
//        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
//        DataModel dataModel = new MySQLJDBCDataModel(dataSource);
//        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
//        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
//        //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
//        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
//        ///构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
//        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
//        //给用户ID等于5的用户推荐10部电影
//        List<RecommendedItem> recommendedItemList = recommender.recommend(5, 10);
//        //打印推荐的结果
//        System.out.println("使用基于用户的协同过滤算法");
//        System.out.println("为用户5推荐10个商品");
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(recommendedItem);
//        }
        return list;
    }

    public static List<AlbumVO> getRecommendedAlbums(int uid){
        //todo: 推荐专辑
        List<AlbumVO> list = new ArrayList<AlbumVO>();

        return list;
    }
}
