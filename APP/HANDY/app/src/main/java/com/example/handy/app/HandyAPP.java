package com.example.handy.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.handy.di.component.AppComponent;
import com.example.handy.di.component.DaggerAppComponent;
import com.example.handy.di.module.base.AppModule;
import com.example.handy.di.module.base.HttpModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class HandyAPP extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> mAndroidInjector;

    // 内存泄漏检测
    private RefWatcher refWatcher;
    private static HandyAPP instance;
    public static boolean isFirstRun = true;
    private static volatile AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        refWatcher = LeakCanary.install(this);
        instance = this;


        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .httpModule(new HttpModule())
                .build();
        appComponent.inject(this);
    }

    public static synchronized HandyAPP getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher(Context context) {
        HandyAPP application = (HandyAPP) context.getApplicationContext();
        return application.refWatcher;
    }

    public static synchronized AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mAndroidInjector;
    }
}
