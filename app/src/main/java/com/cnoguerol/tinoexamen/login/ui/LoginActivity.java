package com.cnoguerol.tinoexamen.login.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.cnoguerol.tinoexamen.PhotoFeedApp;
import com.cnoguerol.tinoexamen.R;
import com.cnoguerol.tinoexamen.login.LoginPresenter;
import com.cnoguerol.tinoexamen.main.ui.MainActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity
        implements LoginView {
    @Bind(R.id.btnSignin)
    Button btnSignIn;
    @Bind(R.id.btnSignup)
    Button btnSignUp;
    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)

    RelativeLayout container;

    @Inject
    LoginPresenter presenter;

    @Inject
    SharedPreferences sharedPreferences;

    private PhotoFeedApp app;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        app = (PhotoFeedApp) getApplication();

        setupInjection();

        presenter.onCreate();

        presenter.validateLogin(null, null);
    }

    private void setupInjection() {
        app.getLoginComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    @Override
    @OnClick(R.id.btnSignup)
    public void handleSignUp() {
        if (!validateForm()) {
            return;
        }else {
        presenter.registerNewUser(inputEmail.getText().toString(),
                inputPassword.getText().toString());
        }
    }

    @Override
    @OnClick(R.id.btnSignin)
    public void handleSignIn() {
        if (!validateForm()) {
            return;
        }else {
            presenter.validateLogin(inputEmail.getText().toString(),
                    inputPassword.getText().toString());
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void disableInputs() {

        setInputs(false);
    }

    @Override
    public void enableInputs() {
        setInputs(true);

    }


    @Override
    public void loginError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setUserEmail(String email) {
        if (email != null) {
            String key = app.getEmailKey();
            sharedPreferences.edit().putString(key, email).commit();
        }
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();
    }

    private void setInputs(boolean enabled) {
        inputEmail.setEnabled(true);
        inputPassword.setEnabled(true);
        inputEmail.setEnabled(true);
        inputPassword.setEnabled(true);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = inputEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Required.");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        String password = inputPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Required.");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
