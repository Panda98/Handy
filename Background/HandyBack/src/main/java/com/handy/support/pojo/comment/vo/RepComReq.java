package com.handy.support.pojo.comment.vo;

import org.springframework.stereotype.Component;

@Component
public class RepComReq {
    private int uid;
    private int comment_id;
    private  int reply_id;
    private String content;
    public RepComReq(){}
    public RepComReq(int uid,int comment_id,int reply_id,String content){
        this.comment_id=comment_id;
        this.uid=uid;
        this.reply_id=reply_id;
        this.content=content;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getUid() {
        return uid;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }
}
