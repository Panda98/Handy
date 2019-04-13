package com.handy.support.pojo.user.vo;

import java.util.List;

/**
 * Created by Pan on 2019/4/12.
 */
public class UserLabelVO {
    private int uid;
    private List<Integer> labels;

    public UserLabelVO(int uid, List<Integer> labels) {
        this.uid = uid;
        this.labels = labels;
    }
    public UserLabelVO(){
        super();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<Integer> getLabelId() {
        return labels;
    }

    public void setLabelId(List<Integer> labelId) {
        this.labels = labelId;
    }
}
