package com.cnoguerol.tinoexamen.main.di;

import com.cnoguerol.tinoexamen.PhotoFeedAppModule;
import com.cnoguerol.tinoexamen.domain.di.DomainModule;
import com.cnoguerol.tinoexamen.libs.di.LibsModule;
import com.cnoguerol.tinoexamen.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cnoguerol.
 */
@Singleton
@Component(modules = {MainModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
