package com.cnoguerol.tinoexamen.login.di;

import com.cnoguerol.tinoexamen.PhotoFeedAppModule;
import com.cnoguerol.tinoexamen.domain.di.DomainModule;
import com.cnoguerol.tinoexamen.libs.di.LibsModule;
import com.cnoguerol.tinoexamen.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cnoguerol.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}

