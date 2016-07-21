package com.cnoguerol.tinoexamen.libs.di;

import com.cnoguerol.tinoexamen.PhotoFeedAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cnoguerol.
 */
@Singleton
@Component(modules = {LibsModule.class, PhotoFeedAppModule.class})
public interface LibsComponent {
}
