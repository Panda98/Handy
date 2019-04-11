package com.example.handy.app;

import android.graphics.Color;

import java.io.File;

public class Constants {

    /**
     * Shared Preference
     */

    public static final String MY_SHARED_PREFERENCE = "my_shared_preference";

    /**
     * Shared Preference key
     */
    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String LOGIN_STATUS = "login_status";

    /**
     * Path
     */
    public static final String PATH_DATA = HandyAPP.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";


    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    /**
     * Avoid double click time area
     */
    public static final long CLICK_TIME_AREA = 1000;

    public static final long DOUBLE_INTERVAL_TIME = 2000;
}
