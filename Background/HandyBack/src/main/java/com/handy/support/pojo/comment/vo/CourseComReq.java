package com.handy.support.pojo.comment.vo;

public class CourseComReq {
    private int user_id;
    private int page_no;
    private int n;
public CourseComReq(int user_id,int page_no,int n){
    this.n=n;
    this.page_no=page_no;
    this.user_id=user_id;
}
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setPage_no(int page_on) {
        this.page_no = page_on;
    }

    public int getN() {
        return n;
    }

    public int getPage_no() {
        return page_no;
    }

    public int getUser_id() {
        return user_id;
    }
}
