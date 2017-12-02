package br.com.cielo.app.ui.main;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.cielo.app.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginAdapter extends RecyclerView.Adapter<LoginAdapter.LoginViewHolder> {

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    @Inject
    public LoginAdapter() {
    }

    @Override
    public LoginViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.login, parent, false);
        final ViewGroup parentF = parent;

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) itemView.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                RecyclerView view = parentF.findViewById(R.id.recycler_view);
                view.swapAdapter(new PrincipalAdapter(),false);
            }

            @Override
            public void onCancel() {
                RecyclerView view = parentF.findViewById(R.id.recycler_view);
                view.swapAdapter(new PrincipalAdapter(),false);
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                RecyclerView view = parentF.findViewById(R.id.recycler_view);
                view.swapAdapter(new PrincipalAdapter(),false);
                // App code
            }
        });
        return new LoginViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LoginViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class LoginViewHolder extends RecyclerView.ViewHolder {

        public LoginViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
