package com.handy.support.recommend.rescorer;

import org.apache.mahout.cf.taste.recommender.IDRescorer;

public class CourseRescorer implements IDRescorer {
    public boolean isFiltered(long l) {
        return false;
    }

    public double rescore(long profileID, double originalScore) {
        return originalScore;
    }
}
