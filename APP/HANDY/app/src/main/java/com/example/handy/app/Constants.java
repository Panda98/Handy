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

    public static final String NO_IMAGE_STATE = "no_image_state";

    /**
     * Path
     */
    public static final String PATH_DATA = HandyAPP.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * 一次加载条目的个数
     */
    public static final int LOAD_NUM = 5;


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

    /**
     * Tag fragment classify
     */
    public static final int TYPE_MAIN_PAGER = 0;

    public static final int TYPE_FOLLOW_PAGER = 1;

    public static final int TYPE_COLLECT_PAGER = 2;

    public static final int TYPE_ACCOUNT_PAGER = 3;


    public static final int TYPE_COLLECT = 5;

    public static final int TYPE_SETTING = 6;


    /**
     * Bottom Navigation tab classify
     */
    public static final int TAB_ONE = 0;

    /**
     * Intent params
     */
    public static final String ARG_PARAM1 = "param1";

    public static final String ARG_PARAM2 = "param2";

    /**
     * Main Pager
     */
    //public static final String SEARCH_TEXT = "search_text";

    //public static final String MENU_BUILDER = "MenuBuilder";

    //public static final String LOGIN_DATA = "login_data";

    public static final String BANNER_DATA = "banner_data";
    public static final String RECOMMEND_ALBUM_DATA = "recommend_album_data";
    public static final String RECOMMEND_COURSE_DATA = "recommend_course_data";
    public static final String CURRENT_PAGE = "current_page";

    public static final int COURSE_COVER_UPLOAD = 0;
    public static final int STEP_PIC_UPLOAD = 1;


    /**
     * Course Detail
     */
    public static final String COURSE_ID = "course_id";
    public static final String COURSE_Title = "course_title";
    public static final String AUTHOR_ID = "author_id";

    /**
     * Album Detail
     */
    public static final String ALBUM_ID = "album_id";


}
