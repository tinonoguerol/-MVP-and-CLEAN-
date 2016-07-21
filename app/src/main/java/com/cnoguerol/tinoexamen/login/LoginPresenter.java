package com.cnoguerol.tinoexamen.login;

import com.cnoguerol.tinoexamen.login.events.LoginEvent;

/**
 * Created by cnoguerol.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();

    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
