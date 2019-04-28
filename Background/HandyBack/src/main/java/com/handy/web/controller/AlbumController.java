package com.handy.web.controller;

import com.handy.support.pojo.album.dto.AlbumCourseDto;
import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.album.vo.AlbumCourseVO;
import com.handy.support.pojo.album.vo.AlbumVO;
import com.handy.support.service.Album.IAlbumService;
import com.handy.support.utils.status.ErrorEnum;
import com.handy.support.utils.status.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */
@RestController
public class AlbumController {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private IAlbumService albumService;
    
    @RequestMapping(value = "/album/recommend",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getRecommendAlbum(int uid){
        //todo: 待开发
        List<AlbumDto> dtos = albumService.getRecommendedAlbum(uid);
        List<AlbumVO> vos = new ArrayList<AlbumVO>();
        ErrorEnum error = null;
        if(dtos== null){
            error = ErrorEnum.USER_NOT_EXIST;
        }else{
            error = ErrorEnum.SUCCESS;
            for(AlbumDto dto:dtos){
                AlbumVO vo = new AlbumVO(dto);
                vos.add(vo);
            }
        }

        ReturnCode<List<AlbumVO>> code = new ReturnCode<List<AlbumVO>>(error,vos);
        return code.returnHandler();
    }

    @RequestMapping(value = "/album/mylist",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getMyAlbumList(int uid){
        List<AlbumDto> dtos = albumService.getAlbumList(uid);
        List<AlbumVO> vos = new ArrayList<AlbumVO>();
        for(AlbumDto dto:dtos){
            if(dto.getAlbumState()){
                AlbumVO vo = new AlbumVO(dto);
                vos.add(vo);
            }
        }
        ReturnCode<List<AlbumVO>> code = new ReturnCode<List<AlbumVO>>(vos);
        return code.returnHandler();
    }

    @RequestMapping(value = "/album/collection",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getListCollection(int uid){
        List<AlbumDto> dtos = albumService.getAlbumList(uid);
        List<AlbumVO> vos = new ArrayList<AlbumVO>();
        for(AlbumDto dto:dtos){
            if(!dto.getAlbumState()){
                AlbumVO vo = new AlbumVO(dto);
                vos.add(vo);
            }
        }
        ReturnCode<List<AlbumVO>> code = new ReturnCode<List<AlbumVO>>(vos);
        return code.returnHandler();
    }


    @RequestMapping(value = "/album/detail",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getListDetail(int uid, int albumid,int page,int n){
        AlbumCourseDto list = albumService.getAlbumDetail(albumid,page,n);
        AlbumCourseVO vo = list.revert2VO();

        ReturnCode<AlbumCourseVO> code = new ReturnCode<AlbumCourseVO>(vo);
        return code.returnHandler();
    }

}
