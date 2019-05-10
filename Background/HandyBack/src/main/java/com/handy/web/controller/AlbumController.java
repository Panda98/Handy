package com.handy.web.controller;

import com.handy.support.pojo.album.dto.AlbumCourseInfoDto;
import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.album.vo.AlbumCourseInfoVO;
import com.handy.support.pojo.album.vo.AlbumCreateVO;
import com.handy.support.pojo.album.vo.AlbumVO;
import com.handy.support.service.Album.IAlbumService;
import com.handy.support.utils.status.ErrorEnum;
import com.handy.support.utils.status.ReturnCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "/album/my_shared_list",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getMySharedAlbumList(int uid){
        List<AlbumDto> dtos = albumService.getAlbumListByUserID(uid);
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
    @RequestMapping(value = "/album/my_private_list",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getMyPrivateAlbumList(int uid){
        List<AlbumDto> dtos = albumService.getAlbumListByUserID(uid);
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

    @RequestMapping(value = "/album/collection",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getAlbumCollection(int uid){
        List<AlbumDto> dtos = albumService.getCollectedAlbum(uid);
        List<AlbumVO> vos = new ArrayList<AlbumVO>();
        for(AlbumDto dto:dtos){
            AlbumVO vo = new AlbumVO(dto);
            vos.add(vo);
        }
        ReturnCode<List<AlbumVO>> code = new ReturnCode<List<AlbumVO>>(vos);
        return code.returnHandler();
    }


    @RequestMapping(value = "/album/detail",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getAlbumDetail(int uid, int albumid, int page, int n){
        List<AlbumCourseInfoDto> list = albumService.getAlbumDetail(albumid,page,n);
        List<AlbumCourseInfoVO> vos = new ArrayList<AlbumCourseInfoVO>();
        for(AlbumCourseInfoDto dto:list){
            AlbumCourseInfoVO vo = dto.revert2VO();
            vos.add(vo);
        }

        ReturnCode<List<AlbumCourseInfoVO>> code = new ReturnCode<List<AlbumCourseInfoVO>>(vos);
        return code.returnHandler();
    }

    @RequestMapping(value = "/album/brief",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getAlbumBriefInfo(int albumid){
        AlbumDto dto = albumService.getAlbumBriefInfo(albumid);
        AlbumVO vo = new AlbumVO(dto);

        ReturnCode<AlbumVO> code = new ReturnCode<AlbumVO>(vo);
        return code.returnHandler();
    }

    @RequestMapping(value = "/album/collect",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String collectAlbum(int uid,int albumid){
        ErrorEnum errorEnum = albumService.collect(uid,albumid);

        ReturnCode<String> returnCode = new ReturnCode<String>();
        returnCode.setErrorEnum(errorEnum);
        return returnCode.returnHandler();
    }
    @RequestMapping(value = "/album/uncollect",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String uncollectAlbum(int uid,int albumid){
        ErrorEnum errorEnum = albumService.uncollect(uid,albumid);

        ReturnCode<String> returnCode = new ReturnCode<String>();
        returnCode.setErrorEnum(errorEnum);
        return returnCode.returnHandler();
    }

    @RequestMapping(value = "/album/create",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String createAlbum(@RequestBody AlbumCreateVO albumVO){
        AlbumDto dto = new AlbumDto();
        BeanUtils.copyProperties(albumVO,dto);
        if(dto.getAlbumPic() == null){
        }
        ErrorEnum errorEnum = albumService.createAlbum(dto);

        ReturnCode<String> returnCode = new ReturnCode<String>();
        returnCode.setErrorEnum(errorEnum);
        return returnCode.returnHandler();
    }

    @RequestMapping(value = "/album/delete",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String deleteAlbum(int albumid){
        ErrorEnum errorEnum = albumService.deleteAlbum(albumid);

        ReturnCode<String> returnCode = new ReturnCode<String>();
        returnCode.setErrorEnum(errorEnum);
        return returnCode.returnHandler();
    }

    @RequestMapping(value = "/album/state",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getAlbumState(int uid,int albumid){
        List<AlbumDto> collected = albumService.getCollectedAlbum(uid);
        boolean isCollected = false;
        for(AlbumDto collection:collected){
            if (collection.getAlbumId() == albumid){
                isCollected = true;
                break;
            }
        }
        ReturnCode<Boolean> returnCode = new ReturnCode<Boolean>(isCollected);
        return returnCode.returnHandler();
    }

}
