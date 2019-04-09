package com.example.handy.di.module.base;


import com.example.handy.app.HandyAPP;
import com.example.handy.core.DataManager;
import com.example.handy.core.http.HttpHelper;
import com.example.handy.core.http.HttpHelperImpl;
import com.example.handy.core.prefs.PreferenceHelper;
import com.example.handy.core.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author wangziang
 * @date 2019/04/07
 */

@Module
public class AppModule {

    private final HandyAPP application;

    public AppModule(HandyAPP application) {
        this.application = application;
    }

    @Provides
    @Singleton
    HandyAPP provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferenceHelperImpl implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferenceHelper preferencesHelper) {
        return new DataManager(httpHelper, preferencesHelper);
    }

}
