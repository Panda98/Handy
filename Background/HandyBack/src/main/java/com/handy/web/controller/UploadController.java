package com.handy.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * Created by joanie on 2019/5/2.
 */

@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 当不知道传入前端的参数是什么时,可调用该接口进行判断.
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/doUploadFile" , method = RequestMethod.POST )
    public @ResponseBody Map<String,Object> doUploadFile(
            HttpServletRequest request,//请求对象
            HttpServletResponse response) throws IOException{//响应对象

        System.out.println("doTestMultipartFile:");

        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        //此时说明请求对象是MultipartHttpServletRequest对象
        MultipartFile picture = req.getFile("UploadedImage");

        //遍历请求得到所有的数据.
        if(req != null){

            //获取所有属性名
            Enumeration enume= req.getAttributeNames();
            while(enume.hasMoreElements()){
                System.out.println("enume:"+enume.nextElement());
            }

            //获取所有文件名
            Iterator<String> fileNames = req.getFileNames();
            while(fileNames.hasNext()){
                System.out.println("fileNames:"+fileNames.next());
            }

            //获取操作文件的map
            Map<String,MultipartFile> fileMap =  req.getFileMap();
            if(fileMap != null && fileMap.size() > 0){
                Set<String> set = fileMap.keySet();
                for(String key:set){
                    System.out.println("String:"+key);
                }
            }

            //获取请求流
            InputStream is = req.getInputStream();
            System.out.println("InputStream:"+is);
            int length = -1;
            while( (length = is.read()) != -1 ){
                System.err.println("data:"+length);
            }

            //获取所有请求参数
            Enumeration enumee = req.getParameterNames();
            while(enumee.hasMoreElements()){
                System.out.println("enumee:"+enumee.nextElement());
            }
        }

        System.out.println(picture);


        return null;
    }

}
