package com.cnoguerol.tinoexamen.login.ui;

/**
 * Created by cnoguerol.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void newUserSuccess();
    void newUserError(String error);

    void navigateToMainScreen();
    void loginError(String error);

    void setUserEmail(String email);
}
