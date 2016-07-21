package com.cnoguerol.tinoexamen.login;

/**
 * Created by cnoguerol.
 */
public class SignupInteractorImpl implements SignupInteractor {

    private LoginRepository loginRepository;

    public SignupInteractorImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void doSignUp(String email, String password) {
        loginRepository.signUp(email, password);
    }


}

