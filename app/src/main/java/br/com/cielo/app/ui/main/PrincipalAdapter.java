package br.com.cielo.app.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import javax.inject.Inject;

import br.com.cielo.app.R;
import butterknife.ButterKnife;

public class PrincipalAdapter extends RecyclerView.Adapter<PrincipalAdapter.LoginViewHolder> {

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    @Inject
    public PrincipalAdapter() {
    }

    @Override
    public LoginViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.principal, parent, false);
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
