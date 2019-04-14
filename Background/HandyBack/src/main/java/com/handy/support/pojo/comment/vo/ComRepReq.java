package com.handy.support.pojo.comment.vo;

import org.springframework.stereotype.Component;

@Component
public class ComRepReq {
    private int comment_id;
    private int page_no;
    int n;
    public ComRepReq(){}
    public ComRepReq(int comment_id,int page_no,int n){
        this.comment_id=comment_id;
        this.page_no=page_no;
        this.n=n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getComment_id() {
        return comment_id;
    }

    public int getN() {
        return n;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getPage_no() {
        return page_no;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }
}
