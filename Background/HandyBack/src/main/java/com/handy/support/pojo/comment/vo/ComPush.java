package com.handy.support.pojo.comment.vo;

import org.springframework.stereotype.Component;

@Component
public class ComPush {
    private int uid;
    private int course_id;
    private String content;
    public ComPush(){}
public ComPush(int uid,int course_id,String content){
    this.content=content;
    this.uid=uid;
    this.course_id=course_id;
}
    public int getCourse_id() {
        return course_id;
    }

    public int getUid() {
        return uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
