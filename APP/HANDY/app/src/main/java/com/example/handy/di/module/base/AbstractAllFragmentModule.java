package com.example.handy.di.module.base;

import com.example.handy.di.component.BaseFragmentComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author quchao
 * @date 2018/5/4
 */

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {

    //@ContributesAndroidInjector(modules = SettingFragmentModule.class)
    //abstract SettingFragment contributesSettingFragmentInject();

}
