package com.cnoguerol.tinoexamen.domain.di;

import com.cnoguerol.tinoexamen.PhotoFeedAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cnoguerol.
 */
@Singleton
@Component(modules = {DomainModule.class, PhotoFeedAppModule.class})
public interface DomainComponent {
}
