package com.cnoguerol.tinoexamen.login;

/**
 * Created by cnoguerol.
 */
public interface LoginRepository {
    void signUp(final String email, final String password);
    void signIn(String email, String password);

}
