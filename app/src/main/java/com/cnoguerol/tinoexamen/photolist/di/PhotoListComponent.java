package com.cnoguerol.tinoexamen.photolist.di;

import com.cnoguerol.tinoexamen.PhotoFeedAppModule;
import com.cnoguerol.tinoexamen.domain.di.DomainModule;
import com.cnoguerol.tinoexamen.libs.di.LibsModule;
import com.cnoguerol.tinoexamen.photolist.ui.PhotoListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cnoguerol.
 */
@Singleton
@Component(modules = {PhotoListModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface PhotoListComponent {
    void inject(PhotoListFragment activity);
}
