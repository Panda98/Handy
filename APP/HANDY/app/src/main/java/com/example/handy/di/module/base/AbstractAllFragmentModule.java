package com.example.handy.di.module.base;

import com.example.handy.di.component.BaseFragmentComponent;
import com.example.handy.di.module.AccountPagerFragmentModule;
import com.example.handy.di.module.CollectPagerFragmentModule;
import com.example.handy.di.module.CommentDialogFragmentModule;
import com.example.handy.di.module.FollowPagerFragmentModule;
import com.example.handy.di.module.MainPagerFragmentModule;
import com.example.handy.di.module.ReceivedCommentFragmentModule;
import com.example.handy.di.module.ReceivedLikeFragmentModule;
import com.example.handy.di.module.ReceivedReplyFragmentModule;
import com.example.handy.di.module.SelectAlbumFragmentModule;
import com.example.handy.view.fragment.CommentDialogFragment;
import com.example.handy.view.fragment.SelectAlbumFragment;
import com.example.handy.view.fragment.main.AccountPagerFragment;
import com.example.handy.view.fragment.main.CollectPagerFragment;
import com.example.handy.view.fragment.main.FollowPagerFragment;
import com.example.handy.view.fragment.main.MainPagerFragment;
import com.example.handy.view.fragment.message.ReceivedCommentFragment;
import com.example.handy.view.fragment.message.ReceivedLikeFragment;
import com.example.handy.view.fragment.message.ReceivedReplyFragment;

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
    @ContributesAndroidInjector(modules = MainPagerFragmentModule.class)
    abstract MainPagerFragment contributesMainPagerFragmentInject();

    @ContributesAndroidInjector(modules = FollowPagerFragmentModule.class)
    abstract FollowPagerFragment contributesFollowPagerFragmentInject();

    @ContributesAndroidInjector(modules = CollectPagerFragmentModule.class)
    abstract CollectPagerFragment contributesCollectPagerFragmentInject();

    @ContributesAndroidInjector(modules = AccountPagerFragmentModule.class)
    abstract AccountPagerFragment contributesAccountPagerFragmentInject();

    @ContributesAndroidInjector(modules = ReceivedCommentFragmentModule.class)
    abstract ReceivedCommentFragment contributesReceivedCommentFragmentInject();

    @ContributesAndroidInjector(modules = ReceivedReplyFragmentModule.class)
    abstract ReceivedReplyFragment contributesReceivedReplyFragmentInject();

    @ContributesAndroidInjector(modules = ReceivedLikeFragmentModule.class)
    abstract ReceivedLikeFragment contributesReceivedLikeFragmentInject();

    @ContributesAndroidInjector(modules = SelectAlbumFragmentModule.class)
    abstract SelectAlbumFragment contributesSelectAlbumFragmentInject();

    @ContributesAndroidInjector(modules = CommentDialogFragmentModule.class)
    abstract CommentDialogFragment contributesCommentDialogFragmentInject();

}
