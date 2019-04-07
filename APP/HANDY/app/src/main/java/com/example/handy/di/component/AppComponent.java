package com.example.handy.di.component;

import com.example.handy.app.HandyAPP;
import com.example.handy.core.DataManager;
import com.example.handy.di.module.base.AbstractAllActivityModule;
import com.example.handy.di.module.base.AbstractAllFragmentModule;
import com.example.handy.di.module.base.AppModule;
import com.example.handy.di.module.base.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author wangziang
 * @date 2019/04/07
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AbstractAllFragmentModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {

    /**
     * 注入WanAndroidApp实例
     *
     * @param wanAndroidApp WanAndroidApp
     */
    void inject(HandyAPP wanAndroidApp);

    /**
     * 提供App的Context
     *
     * @return GeeksApp context
     */
    HandyAPP getContext();

    /**
     * 数据中心
     *
     * @return DataManager
     */
    DataManager getDataManager();

}
