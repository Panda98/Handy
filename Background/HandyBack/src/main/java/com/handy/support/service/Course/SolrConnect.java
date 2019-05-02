package com.handy.support.service.Course;


import com.handy.support.entity.Course;
//import com.handy.support.mapper.UserMapper;
import com.handy.support.pojo.course.vo.CourseSimpleVO;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
//import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Service("solrConnect")
public class SolrConnect {

    @Autowired
    private ICourseService iCourseService;

//    @Autowired
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    private UserMapper userMapper;
    // solr url
    private final static String BASE_URL = "http://localhost:8983/solr/#/test";

    /**
     * 获取SolrClient
     * solrj 6.5及以后版本获取方式
     * @return
     */
//    public static HttpSolrClient getSolrClient(){
//        /*
//         * 设置超时时间
//         * .withConnectionTimeout(10000)
//         * .withSocketTimeout(60000)
//         */
//        return new HttpSolrClient.Builder(BASE_URL)
//                .withConnectionTimeout(10000)
//                .withSocketTimeout(60000)
//                .build();
//    }



//    public List<CourseSimpleVO> courseQuery(String text) throws IOException, SolrServerException {
//        HttpSolrClient solrClient = getSolrClient();
        // 定义查询条件
        Map<String, String> params = new HashMap<String, String>();
//        params.put("q", "course_title:"+text+" OR course_intro:"+text+" OR diy_label:"+text);

//        SolrParams mapSolrParams = new MapSolrParams(params);
//        //执行查询 第一个参数是collection，就是我们在solr中创建的core
//        QueryResponse response = solrClient.query("test", mapSolrParams);
        // 获取结果集
//        SolrDocumentList results = response.getResults();
        List<CourseSimpleVO> simpleList=new ArrayList<CourseSimpleVO>();
//        for (SolrDocument result : results) {
//            // SolrDocument 数据结构为Map
//            Integer id=Integer.parseInt(result.get("course_id").toString());
//            Course c=iCourseService.getCourseByID(id);
//            CourseSimpleVO simpleVO=new CourseSimpleVO(id,c.getCourseTitle(),c.getCourseCover(),c.getCourseIntro(),userMapper.selectByPrimaryKey(c.getUserId()).getNickName(),c.getLevelId(),iCourseService.getLabelList(id),c.getDiyLabel());
//            simpleList.add(simpleVO);
//            System.out.println(result);
//        }
//        return simpleList;
//    }



}
