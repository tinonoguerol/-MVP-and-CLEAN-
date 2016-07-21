package com.cnoguerol.tinoexamen.libs.di;

import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cnoguerol.tinoexamen.libs.GlideImageLoader;
import com.cnoguerol.tinoexamen.libs.GreenRobotEventBus;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cnoguerol.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }


}

