package com.cnoguerol.tinoexamen.login;

/**
 * Created by cnoguerol.
 */
public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }


}
