package com.example.handy.di.module.base;

import com.example.handy.di.module.AlbumDetailActivityModule;
import com.example.handy.di.module.AuthorHomepageActivityModule;
import com.example.handy.di.module.CourseDetailActivityModule;
import com.example.handy.di.module.CourseListByLabelActivityModule;
import com.example.handy.di.module.MainActivityModule;
import com.example.handy.di.module.MessageActivityModule;
import com.example.handy.di.module.MyPublishAlbumActivityModule;
import com.example.handy.di.module.MyPublishCourseActivityModule;
import com.example.handy.di.module.PublishCourseActivityModule;
import com.example.handy.view.activity.AlbumDetailActivity;
import com.example.handy.view.activity.AuthorHomepageActivity;
import com.example.handy.view.activity.CourseDetailActivity;
import com.example.handy.view.activity.CourseListByLabelActivity;
import com.example.handy.view.activity.LoginActivity;
import com.example.handy.view.activity.MainActivity;
import com.example.handy.view.activity.MessageActivity;
import com.example.handy.view.activity.MorePublishAlbumActivity;
import com.example.handy.view.activity.MorePublishCourseActivity;
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

    @ContributesAndroidInjector(modules = CourseDetailActivityModule.class)
    abstract CourseDetailActivity contributesCourseDetailActivityInjector();

    @ContributesAndroidInjector(modules = AlbumDetailActivityModule.class)
    abstract AlbumDetailActivity contributesAlbumDetailActivityInjector();

    @ContributesAndroidInjector(modules = MyPublishAlbumActivityModule.class)
    abstract MorePublishAlbumActivity contributesMyPublishAlbumActivityInjector();

    @ContributesAndroidInjector(modules = MyPublishCourseActivityModule.class)
    abstract MorePublishCourseActivity contributesMyPublishCourseActivityInjector();

    @ContributesAndroidInjector(modules = MessageActivityModule.class)
    abstract MessageActivity contributesMessageActivityInjector();

    @ContributesAndroidInjector(modules = AuthorHomepageActivityModule.class)
    abstract AuthorHomepageActivity contributesAuthorHomepageActivityInjector();

    @ContributesAndroidInjector(modules = CourseListByLabelActivityModule.class)
    abstract CourseListByLabelActivity contributesCourseListByLabelActivityModuleInjector();

}
