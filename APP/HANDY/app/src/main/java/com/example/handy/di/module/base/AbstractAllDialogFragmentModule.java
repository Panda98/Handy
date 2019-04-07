package com.example.handy.di.module.base;

import com.example.handy.di.component.BaseDialogFragmentComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author wangziang
 * @date 2019/04/07
 */

@Module(subcomponents = BaseDialogFragmentComponent.class)
public abstract class AbstractAllDialogFragmentModule {

    //@ContributesAndroidInjector(modules = SearchDialogFragmentModule.class)
    //abstract SearchDialogFragment contributesSearchDialogFragmentInject();

}
