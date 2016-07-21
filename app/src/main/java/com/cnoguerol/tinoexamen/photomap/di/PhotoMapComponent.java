package com.cnoguerol.tinoexamen.photomap.di;

import com.cnoguerol.tinoexamen.PhotoFeedAppModule;
import com.cnoguerol.tinoexamen.domain.di.DomainModule;
import com.cnoguerol.tinoexamen.libs.di.LibsModule;
import com.cnoguerol.tinoexamen.photomap.ui.PhotoMapFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cnoguerol.
 */
@Singleton
@Component(modules = {PhotoMapModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface PhotoMapComponent {
    void inject(PhotoMapFragment target);
}


