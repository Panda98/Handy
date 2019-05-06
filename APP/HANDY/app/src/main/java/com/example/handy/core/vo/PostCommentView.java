package com.example.handy.core.vo;

public class PostCommentView {

    private int uid;
    private int course_id;
    private String content;

    public PostCommentView(int uid, int course_id, String content) {
        this.uid = uid;
        this.course_id = course_id;
        this.content = content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
