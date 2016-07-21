package com.cnoguerol.tinoexamen.login.di;

import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.login.LoginInteractor;
import com.cnoguerol.tinoexamen.login.LoginInteractorImpl;
import com.cnoguerol.tinoexamen.login.LoginPresenter;
import com.cnoguerol.tinoexamen.login.LoginPresenterImpl;
import com.cnoguerol.tinoexamen.login.LoginRepository;
import com.cnoguerol.tinoexamen.login.LoginRepositoryImpl;
import com.cnoguerol.tinoexamen.login.SignupInteractor;
import com.cnoguerol.tinoexamen.login.SignupInteractorImpl;
import com.cnoguerol.tinoexamen.login.ui.LoginView;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cnoguerol.
 */

@Module
public class LoginModule {
    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides @Singleton
    LoginView providesLoginView() {
        return this.view;
    }

    @Provides @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor, SignupInteractor signupInteractor) {
        return new LoginPresenterImpl(eventBus, loginView, loginInteractor, signupInteractor);
    }

    @Provides @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }

    @Provides @Singleton
    SignupInteractor providesSignupInteractor(LoginRepository repository) {
        return new SignupInteractorImpl(repository);
    }

    @Provides
    @Singleton
    LoginRepository providesLoginRepository(FirebaseApi mAuth, EventBus eventBus) {
        return new LoginRepositoryImpl(mAuth, eventBus);
    }
}



