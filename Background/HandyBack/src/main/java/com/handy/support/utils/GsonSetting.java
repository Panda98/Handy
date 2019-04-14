package com.handy.support.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Pan on 2019/4/12.
 */
public class GsonSetting {
    public static final Gson GSON;
    static {
        GSON =new GsonBuilder()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }
}
