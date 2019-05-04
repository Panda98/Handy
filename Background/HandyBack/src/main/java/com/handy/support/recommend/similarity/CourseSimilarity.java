package com.handy.support.recommend.similarity;

import com.google.common.base.Preconditions;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.Cache;
import org.apache.mahout.cf.taste.impl.common.RefreshHelper;
import org.apache.mahout.cf.taste.impl.common.Retriever;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.LongPair;

import java.util.Collection;
import java.util.concurrent.Callable;

public class CourseSimilarity  implements UserSimilarity  {

    private final UserSimilarity similarity;
    private final Cache<LongPair,Double> similarityCache;
    private final RefreshHelper refreshHelper;

    /**
     * Creates this on top of the given {@link UserSimilarity}.
     * The cache is sized according to properties of the given {@link DataModel}.
     */
    public CourseSimilarity(UserSimilarity similarity, DataModel dataModel) throws TasteException {
        this(similarity, dataModel.getNumUsers());
    }

    /**
     * Creates this on top of the given {@link UserSimilarity}.
     * The cache size is capped by the given size.
     */
    public CourseSimilarity(UserSimilarity similarity, int maxCacheSize) {
        Preconditions.checkArgument(similarity != null, "similarity is null");
        this.similarity = similarity;
        this.similarityCache = new Cache<LongPair,Double>(new CourseSimilarity.SimilarityRetriever(similarity), maxCacheSize);
        this.refreshHelper = new RefreshHelper(new Callable<Void>() {
            public Void call() {
                similarityCache.clear();
                return null;
            }
        });
        refreshHelper.addDependency(similarity);
    }

    public double userSimilarity(long userID1, long userID2) throws TasteException {
        LongPair key = userID1 < userID2 ? new LongPair(userID1, userID2) : new LongPair(userID2, userID1);
        return similarityCache.get(key);
    }

    public void setPreferenceInferrer(PreferenceInferrer inferrer) {
        similarityCache.clear();
        similarity.setPreferenceInferrer(inferrer);
    }


    public void refresh(Collection<Refreshable> alreadyRefreshed) {
        refreshHelper.refresh(alreadyRefreshed);
    }

    private static final class SimilarityRetriever implements Retriever<LongPair,Double> {
        private final UserSimilarity similarity;

        private SimilarityRetriever(UserSimilarity similarity) {
            this.similarity = similarity;
        }

        public Double get(LongPair key) throws TasteException {
            return similarity.userSimilarity(key.getFirst(), key.getSecond());
        }
    }
}
