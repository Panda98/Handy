package com.handy.web.controller;

import com.google.gson.Gson;
import com.handy.support.entity.User;
import com.handy.support.pojo.user.dto.UserDto;
import com.handy.support.pojo.user.vo.UserAuthVO;
import com.handy.support.pojo.user.vo.UserLabelVO;
import com.handy.support.pojo.user.vo.UserModifyVO;
import com.handy.support.pojo.user.vo.UserVO;
import com.handy.support.service.Follow.IFollowService;
import com.handy.support.utils.GsonSetting;
import com.handy.support.utils.status.ErrorEnum;
import com.handy.support.utils.status.ReturnCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.handy.support.service.User.IUserService;

/**
 * Created by Pan on 2019/4/11.
 */

@RestController
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IFollowService followService;
    @Autowired
    private Gson gson;



    /**
     * 登录
     * @param userAuthVO 包含username和password
     * @return 该user的json
     */
    @RequestMapping(value="/user/login",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String login(@RequestBody UserAuthVO userAuthVO){
        User user = iUserService.getUserByEmail(userAuthVO.getUsername());

        ErrorEnum error = null;
        UserDto dto = new UserDto();
        UserVO vo=null;
        ReturnCode<UserVO> code = new ReturnCode<UserVO>(vo);
        if(user == null){
            error = ErrorEnum.USER_NOT_EXIST;
        }
        else if(!user.getLoginPassword().equals(userAuthVO.getPassword())){
            error = ErrorEnum.WRONG_PASSWORD;
        }


        if(error == null) {
            BeanUtils.copyProperties(user,dto);
            vo = new UserVO(dto);
            code = new ReturnCode<UserVO>(vo);
            error = ErrorEnum.SUCCESS;
        }
        code.setErrorEnum(error);
        return code.returnHandler();
    }

    /**
     * 注册
     * @param userAuthVO 包含username和password
     * @return 该user的json
     */
    @RequestMapping(value = "/user/regist",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String regist(@RequestBody UserAuthVO userAuthVO){
        ErrorEnum res = iUserService.addUser(userAuthVO.getUsername(), userAuthVO.getPassword());

        UserVO vo = null;
        if(res == ErrorEnum.SUCCESS){
            User user = iUserService.getUserByEmail(userAuthVO.getUsername());
            vo = iUserService.revert2VO(user);

        }
        ReturnCode<UserVO> code = new ReturnCode<UserVO>(res,vo);
        return code.returnHandler();
    }

    /**
     * 选择难易度
     * @param uid 用户id
     * @param level 选择的难易度
     * @return 结果json
     */
    @RequestMapping(value = "/user/choose_level", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String chooseLevel(int uid,byte level){
        UserDto userDto = new UserDto();
        userDto.setUserId(uid);
        userDto.setLevelId(level);
        ErrorEnum res = iUserService.updateUser(userDto);
        ReturnCode<String> code = new ReturnCode<String>(res,null);
        return code.returnHandler();
    }

    /**
     * 选择兴趣标签
     * @param userLabelVO 包含用户id和选择的标签
     * @return 结果json
     */
    @RequestMapping(value = "/user/choose_labels", produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String chooseLabels(@RequestBody UserLabelVO userLabelVO){
        ErrorEnum error = iUserService.addUserLabels(userLabelVO.getUid(),userLabelVO.getLabelId());
        ReturnCode<String> code = new ReturnCode<String>(error,null);
        return code.returnHandler();
    }

    /**
     * 获取用户信息
     * @param uid 用户id
     * @return 结果json
     */
    @RequestMapping(value = "/user", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getUserInfo(int uid){
        UserDto user = iUserService.getUserByID(uid);
        int followCount = followService.getFollowNum(uid);

        //todo: fanCount
        int fansCount = followService.getFansNum(uid);
        user.setFollowCount(followCount);
        user.setFansCount(fansCount);

        ErrorEnum error = null;
        UserVO vo = null;
        if(user == null){
            error = ErrorEnum.USER_NOT_EXIST;
        }else{
            error = ErrorEnum.SUCCESS;
            vo = new UserVO(user);
        }

        ReturnCode<UserVO> code = new ReturnCode<UserVO>(error,vo);
        return code.returnHandler();
    }

    @RequestMapping(value = "/user/modify", produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String modify(@RequestBody UserModifyVO vo){
        UserDto dto = vo.revert2DTO();
        ErrorEnum error = iUserService.modify(dto);
        ReturnCode<String> code = new ReturnCode<String>();
        code.setErrorEnum(error);
        return code.returnHandler();
    }


}
