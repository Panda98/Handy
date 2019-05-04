package com.handy.support.recommend.recommender;


import com.handy.support.recommend.rescorer.CourseRescorer;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.GenericUserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CourseRecommender implements Recommender{
    @Autowired
    DataSource dataSource;
    private final Recommender delegate;
    private  final DataModel model;
    public CourseRecommender(DataModel model)throws TasteException,IOException{
        UserSimilarity similarity=
                new UncenteredCosineSimilarity(model);
        UserNeighborhood neighborhood=
                new NearestNUserNeighborhood(2,similarity,model);
        this.model=model;
              delegate=new GenericUserBasedRecommender(model,neighborhood,similarity);
    }

    public float estimatePreference(long l, long l1) throws TasteException{
            return delegate.estimatePreference(l, l1);
    }

    public List<RecommendedItem> recommend(long userID, int howMany)throws TasteException {
        IDRescorer rescorer=new CourseRescorer();
        return delegate.recommend(userID,howMany,rescorer);
    }

    public List<RecommendedItem> recommend(long l, int i, IDRescorer idRescorer) throws TasteException{
            return delegate.recommend(l, i, idRescorer);
    }

    public void refresh(Collection<Refreshable> collection) {
        delegate.refresh(collection);
    }

    public DataModel getDataModel() {
        return delegate.getDataModel();
    }

    public void setPreference(long l, long l1, float v) throws TasteException{
        delegate.setPreference(l,l1,v);
    }

    public void removePreference(long l, long l1) throws TasteException{
        delegate.removePreference(l,l1);
    }
}
