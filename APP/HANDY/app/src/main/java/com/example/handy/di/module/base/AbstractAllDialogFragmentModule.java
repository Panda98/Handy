package com.example.handy.di.module.base;

import com.example.handy.di.component.BaseDialogFragmentComponent;
import com.example.handy.di.module.CommentDialogFragmentModule;
import com.example.handy.di.module.SelectAlbumFragmentModule;
import com.example.handy.view.fragment.CommentDialogFragment;
import com.example.handy.view.fragment.SelectAlbumFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author wangziang
 * @date 2019/04/07
 */

@Module(subcomponents = BaseDialogFragmentComponent.class)
public abstract class AbstractAllDialogFragmentModule {

    @ContributesAndroidInjector(modules = SelectAlbumFragmentModule.class)
    abstract SelectAlbumFragment contributesSelectAlbumFragmentInject();

    @ContributesAndroidInjector(modules = CommentDialogFragmentModule.class)
    abstract CommentDialogFragment contributesCommentDialogFragmentInject();

}
