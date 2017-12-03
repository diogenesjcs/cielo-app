package br.com.cielo.app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import br.com.cielo.app.R;
import br.com.cielo.app.ui.base.BaseActivity;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    Context context;

    CallbackManager callbackManager;

    LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();
        if(AccessToken.getCurrentAccessToken()!=null && !AccessToken.getCurrentAccessToken().isExpired()){
            Intent myIntent = new Intent(context, PrincipalActivity.class);
            startActivity(myIntent);
        }

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent myIntent = new Intent(context, PrincipalActivity.class);
                startActivity(myIntent);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }




}
