package com.handy.support.service.Comment;

import com.handy.support.entity.Comment;
import com.handy.support.entity.CommentExample;
import com.handy.support.entity.CommentReply;
import com.handy.support.mapper.CommentMapper;
import com.handy.support.mapper.CommentReplyMapper;
import com.handy.support.mapper.comment.MyCommentMapper;
import com.handy.support.pojo.comment.dto.CommentPush;
import com.handy.support.pojo.comment.dto.ReplyPush;
import com.handy.support.pojo.comment.vo.ComPush;
import com.handy.support.pojo.comment.vo.ComRepReq;
import com.handy.support.pojo.comment.vo.CourseComReq;
import org.apache.ibatis.session.RowBounds;
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

    public void pushCommentToCourse(CommentPush req) {
        autoCommentMapper.insert(req.getComment());
    }

    public List<CommentReply> getCommentReply(ComRepReq req) {
        return commentMapper.getCommentReplyByCommentIdLimited(req.getComment_id(),req.getPage_no()*req.getN(),req.getN());
    }
    public void pushCommentReply(ReplyPush req){
        commentReplyMapper.insert(req.getReply());
    }
}
