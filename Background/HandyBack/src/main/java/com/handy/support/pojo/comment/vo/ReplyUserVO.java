package com.handy.support.pojo.comment.vo;

import com.handy.support.pojo.Follow.dto.UserBrief;
import com.handy.support.pojo.comment.dto.ReplyDTO;
import com.handy.support.pojo.comment.dto.ReplyUserDTO;

import java.util.Date;

public class ReplyUserVO {

    private int toReplyType;
    private Integer replyId;
    private String replyContent;
    private Integer toReplyId;//有可能是commentTable中的，有可能是replyTable中的，视type定
    private Date updateTime;
    private int toReplyUserId;
    private String toReplyNickName;
    private String toReplyUserPic;

    //   private UserBrief toReplyUser;//被回复者用户信息
    //   private UserBrief commentUser;//所在一级评论的用户信息
    //  private Integer userId;
    // private Integer commentId;
    public ReplyUserVO(ReplyDTO dto,int type){
        this.toReplyType=type;
        if(type==1){
            this.toReplyId=dto.getComment().getCommentId();
        }
        else{
            this.toReplyId=dto.getToReply().getToReplyId();
        }
        this.replyId=dto.getReplyId();
        this.replyContent=dto.getReplyContent();
        this.updateTime=dto.getUpdateTime();
    }
    public ReplyUserVO(ReplyUserDTO dto){
        if(dto.getToReplyUser()==null){
            this.toReplyType=1;
            this.toReplyUserId=dto.getCommentUser().getUserId();
            this.toReplyNickName=dto.getCommentUser().getNickName();
            this.toReplyUserPic=dto.getCommentUser().getUserPic();
            this.toReplyId=dto.getCommentId();
        }
        else{
            this.toReplyType=2;
            this.toReplyUserId=dto.getToReplyUser().getUserId();
            this.toReplyNickName=dto.getToReplyUser().getNickName();
            this.toReplyUserPic=dto.getToReplyUser().getUserPic();
            this.toReplyId=dto.getToReplyId();
        }
        this.replyId=dto.getReplyId();
        this.replyContent=dto.getReplyContent();
        this.updateTime=dto.getUpdateTime();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public Integer getToReplyId() {
        return toReplyId;
    }

    public String getToReplyNickName() {
        return toReplyNickName;
    }

    public int getToReplyType() {
        return toReplyType;
    }

    public int getToReplyUserId() {
        return toReplyUserId;
    }

    public String getToReplyUserPic() {
        return toReplyUserPic;
    }
}
