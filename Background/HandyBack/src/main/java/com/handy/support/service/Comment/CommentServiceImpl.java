package com.handy.support.service.Comment;

import com.handy.support.entity.Comment;
import com.handy.support.entity.CommentReply;
import com.handy.support.mapper.CommentMapper;
import com.handy.support.mapper.CommentReplyMapper;
import com.handy.support.mapper.customMapper.MyCommentMapper;
import com.handy.support.pojo.comment.dto.CommentDTO;
import com.handy.support.pojo.comment.dto.CommentPush;
import com.handy.support.pojo.comment.dto.ReplyPush;
import com.handy.support.pojo.comment.vo.ComRepReq;
import com.handy.support.pojo.comment.vo.CourseComReq;
import com.handy.support.pojo.comment.dto.ReplyUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service("commentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private MyCommentMapper commentMapper;
    @Autowired
    private CommentMapper autoCommentMapper;
    @Autowired
    private CommentReplyMapper commentReplyMapper;
    public List<Comment> getCourseComment(CourseComReq req){
        return commentMapper.getCommentsByCourseLimited(req.getCourse_id(),req.getPage_no()*req.getN(),req.getN());
    }
    public List<CommentDTO> getFullCourseComment(CourseComReq req){
        return commentMapper.getFullCommentsByCourseLimited(req.getCourse_id(),req.getPage_no()*req.getN(),req.getN());
    }
public List<ReplyUserDTO> getCommentReplyUserLimited(ComRepReq req){
    return commentMapper.getCommentsReplyUserLimited(req.getComment_id(),req.getPage_no()*req.getN(),req.getN());
}
    public void pushCommentToCourse(CommentPush req) {
        autoCommentMapper.insertSelective(req.getComment());
    }

    public List<CommentReply> getCommentReply(ComRepReq req) {
        return commentMapper.getCommentReplyByCommentIdLimited(req.getComment_id(),req.getPage_no()*req.getN(),req.getN());
    }
    public void pushCommentReply(ReplyPush req){
        commentReplyMapper.insertSelective(req.getReply());
    }
}
