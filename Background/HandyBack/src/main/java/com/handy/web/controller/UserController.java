package com.handy.web.controller;

import com.google.gson.Gson;
import com.handy.support.entity.User;
import com.handy.support.pojo.user.dto.UserDto;
import com.handy.support.pojo.user.vo.UserAuthVO;
import com.handy.support.pojo.user.vo.UserLabelVO;
import com.handy.support.pojo.user.vo.UserVO;
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
    private Gson gson;



    /**
     * 登录
     * @param userAuthVO 包含username和password
     * @return 该user的json
     */
    @RequestMapping(value="/user/login",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String login(@RequestBody UserAuthVO userAuthVO){
        User user = iUserService.getUserByEmail(userAuthVO.getUsername());

        Integer statusCode=0;
        String msg = "";
        ErrorEnum error = null;
        if(user == null){
            error = ErrorEnum.USER_NOT_EXIST;
        }
        else if(!user.getLoginPassword().equals(userAuthVO.getPassword())){
            error = ErrorEnum.WRONG_PASSWORD;
        }
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user,dto);

        UserVO vo=null;
        if(msg.equals("")) {
           vo = new UserVO(dto);
        }
        ReturnCode<UserVO> code = new ReturnCode<UserVO>(vo);
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
        String msg = iUserService.updateUser(userDto);
        return msg;
    }

    /**
     * 选择兴趣标签
     * @param userLabelVO 包含用户id和选择的标签
     * @return 结果json
     */
    @RequestMapping(value = "/user/choose_labels", produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String chooseLabels(@RequestBody UserLabelVO userLabelVO){
        String msg = iUserService.addUserLabels(userLabelVO.getUid(),userLabelVO.getLabelId());
        return msg;
    }

    /**
     * 获取用户信息
     * @param uid 用户id
     * @return 结果json
     */
    @RequestMapping(value = "/user", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getUserInfo(int uid){
        UserVO vo = new UserVO(iUserService.getUserByID(uid));
        gson = GsonSetting.GSON;
        System.out.print(gson.toJson(vo));
        return gson.toJson(vo);
    }

}
