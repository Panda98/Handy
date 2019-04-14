package com.handy.support.pojo.comment.vo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CourseComReq {
    private int course_id;
    private int page_no;
    private int n;
    public CourseComReq(){}
public CourseComReq(int course_id,int page_no,int n){
    this.n=n;
    this.page_no=page_no;
    this.course_id=course_id;
}
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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

    public int getCourse_id() {
        return course_id;
    }
}
