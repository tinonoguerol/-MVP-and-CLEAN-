package com.cnoguerol.tinoexamen.login;

import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.login.events.LoginEvent;
import com.cnoguerol.tinoexamen.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by cnoguerol.
 */
public class LoginPresenterImpl implements LoginPresenter {
    EventBus eventBus;
    LoginView loginView;
    LoginInteractor loginInteractor;
    SignupInteractor signupInteractor;

    public LoginPresenterImpl(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor, SignupInteractor signupInteractor) {
        this.eventBus = eventBus;
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        this.signupInteractor = signupInteractor;
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }



    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        signupInteractor.doSignUp(email, password);
    }


    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
       switch (event.getEventType()) {
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMesage());
                break;
            case LoginEvent.onSignInSuccess:
                onSignInSuccess(event.getLoggedUserEmail());
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMesage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }

    }

    private void onFailedToRecoverSession() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();

        }
    }

    private void onSignInSuccess(String email) {
        if (loginView != null) {
            loginView.setUserEmail(email);
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess() {
        if (loginView != null) {
            loginView.newUserSuccess();
        }
    }

    private void onSignInError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }


}
