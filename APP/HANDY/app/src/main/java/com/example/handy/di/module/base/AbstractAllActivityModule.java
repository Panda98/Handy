package com.example.handy.di.module.base;

import com.example.handy.di.module.MainActivityModule;
import com.example.handy.di.module.PublishCourseActivityModule;
import com.example.handy.view.activity.LoginActivity;
import com.example.handy.view.activity.MainActivity;
import com.example.handy.view.activity.PublishCourseActivity;
import com.example.handy.view.activity.RegisterActivity;
import com.example.handy.di.component.BaseActivityComponent;
import com.example.handy.di.module.LoginActivityModule;
import com.example.handy.di.module.RegisterActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author quchao
 * @date 2018/5/3
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivityInjector();

    @ContributesAndroidInjector(modules = RegisterActivityModule.class)
    abstract RegisterActivity contributesRegisterActivityInjector();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = PublishCourseActivityModule.class)
    abstract PublishCourseActivity contributesPublishCourseActivityInjector();


}
