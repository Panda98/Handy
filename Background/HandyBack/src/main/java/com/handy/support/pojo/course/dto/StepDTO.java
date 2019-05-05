package com.handy.support.pojo.course.dto;

/**
 * Created by joanie on 2019/5/5.
 */
public class StepDTO {
private Byte stepTag;
private String stepImg;
private String stepDetail;

    public StepDTO() {
    }

    public StepDTO(Byte stepTag, String stepImg, String stepDetail) {
        this.stepTag = stepTag;
        this.stepImg = stepImg;
        this.stepDetail = stepDetail;
    }

    public Byte getStepTag() {
        return stepTag;
    }

    public void setStepTag(Byte stepTag) {
        this.stepTag = stepTag;
    }

    public String getStepImg() {
        return stepImg;
    }

    public void setStepImg(String stepImg) {
        this.stepImg = stepImg;
    }

    public String getStepDetail() {
        return stepDetail;
    }

    public void setStepDetail(String stepDetail) {
        this.stepDetail = stepDetail;
    }
}
