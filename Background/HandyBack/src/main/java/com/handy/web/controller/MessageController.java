package com.handy.web.controller;

import com.handy.support.pojo.Message.dto.FavorDTO;
import com.handy.support.pojo.Message.vo.CourseMessageVO;
import com.handy.support.pojo.Message.vo.FavorVO;
import com.handy.support.pojo.Message.vo.ReplyMessageVO;
import com.handy.support.pojo.comment.dto.CommentDTO;
import com.handy.support.pojo.comment.dto.ReplyDTO;
import com.handy.support.recommend.operation.Recommend;
import com.handy.support.service.Message.MessageServiceImpl;
import com.handy.support.service.Recommend.RecommendServiceImpl;
import com.handy.support.utils.status.ReturnCode;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping(value = "/message",produces = "application/json; charset=utf-8")
public class MessageController {
    @Autowired
    private  Recommend recommend;
    @Autowired
    MessageServiceImpl messageService;
    @Autowired
    RecommendServiceImpl recommendService;
        @RequestMapping(value = "/courseComment",method = GET)
    public String getMessageComment(int uid,int page_no,int n){
        List<CommentDTO> comments=messageService.getCourseCommentMessage(uid,page_no,n);
        List<CourseMessageVO>results=new ArrayList<CourseMessageVO>();
        for(int i=0;i<comments.size();i++){
            results.add(new CourseMessageVO(comments.get(i)));
        }
        ReturnCode<List> code = new ReturnCode<List>(results);
        return code.returnHandler();
    }
    @RequestMapping(value = "/commentReply",method = GET)
    public String getMessageCommentReply(int uid,int page_no,int n){
            List<ReplyDTO>list=messageService.getCommentReplyMessage(uid,page_no,n);
            List<ReplyMessageVO> message=new ArrayList<ReplyMessageVO>();
            for(int i=0;i<list.size();i++){
                ReplyDTO dto=list.get(i);
                message.add(new ReplyMessageVO(dto,1));
            }
            List<ReplyDTO> list2=messageService.getReplyReplyMessage(uid,page_no,n);
        List<ReplyMessageVO> message2=new ArrayList<ReplyMessageVO>();
        for(int i=0;i<list2.size();i++){
            ReplyDTO dto=list2.get(i);
            message2.add(new ReplyMessageVO(dto,2));
        }
        message.addAll(message2);
        Collections.sort(message);
        List<ReplyMessageVO> result=new ArrayList<ReplyMessageVO>();
        for(int i=0;i<n&&i<message.size();i++){
            result.add(message.get(i));
        }
        ReturnCode<List> code = new ReturnCode<List>(result);
        return code.returnHandler();
    }
    @RequestMapping(value = "/courseLike",method = GET)
    public String getMessageCourseLike(int uid,int page_no,int n){
            List<FavorDTO> list=messageService.getFavorMessage(uid,page_no,n);
            List<FavorVO> result=new ArrayList<FavorVO>();
            for(int i=0;i<list.size();i++){
                result.add(new FavorVO(list.get(i)));
            }
        ReturnCode<List> code = new ReturnCode<List>(result);
        return code.returnHandler();
    }
    @RequestMapping(value = "/test",method = GET)
    public String getRecommend(int uid,int page_no,int n)throws TasteException {
            if(recommend.isHasInit()==false) {
                recommend.init();
            }
            else {
                recommend.refresh();
            }
        List<RecommendedItem> list=recommend.getRecommend(uid,page_no,n);
        ReturnCode<List> code = new ReturnCode<List>(list);
        return code.returnHandler();
    }
}
