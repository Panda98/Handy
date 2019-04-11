package com.example.handy.core.http;

import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.LoginData;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpHelper {
    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 项目类别数据
     */
    Observable<BaseResponse<LoginData>> getLoginData(String username, String password);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param rePassword re password
     * @return 登陆数据
     */
    Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String rePassword);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();
}
