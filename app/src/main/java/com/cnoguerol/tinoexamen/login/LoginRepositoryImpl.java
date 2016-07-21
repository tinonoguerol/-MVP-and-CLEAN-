package com.cnoguerol.tinoexamen.login;

import com.cnoguerol.tinoexamen.domain.FirebaseActionListenerCallback;
import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.login.events.LoginEvent;

/**
 * Created by cnoguerol.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private EventBus eventBus;
    private FirebaseApi mAuth;

    public LoginRepositoryImpl(FirebaseApi mAuth, EventBus eventBus) {
        this.mAuth= mAuth;
        this.eventBus = eventBus;
    }


    @Override
    public void signUp(final String email, final String password) {
        mAuth.signUp(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onAuthSuccess() {
                post(LoginEvent.onSignUpSuccess);
                signIn(email, password);
            }
            @Override
            public void onAuthFailed(String error) {
                post(LoginEvent.onSignUpError, error, null);
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        if (email != null && password != null) {
            mAuth.signIn(email, password, new FirebaseActionListenerCallback() {
                @Override
                public void onAuthSuccess() {
                    String email = mAuth.getAuthEmail();
                    post(LoginEvent.onSignInSuccess, null, email);
                }
                @Override
                public void onAuthFailed(String error) {
                    post(LoginEvent.onSignUpError, error, null);
                }
            });
        } else {
            mAuth.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onAuthSuccess() {
                    String email = mAuth.getAuthEmail();
                    post(LoginEvent.onSignInSuccess, null, email);
                }
                @Override
                public void onAuthFailed(String error) {
                    post(LoginEvent.onFailedToRecoverSession);
                }
            });
        }
    }

    private void post(int type, String errorMessage, String loggedUserEmail) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setErrorMesage(errorMessage);
        loginEvent.setLoggedUserEmail(loggedUserEmail);
        eventBus.post(loginEvent);
    }

    private void post(int type){
        post(type, null, null);
    }

    private void post(int type, String loggedUserEmail){
        post(type, null, loggedUserEmail);
    }
}
