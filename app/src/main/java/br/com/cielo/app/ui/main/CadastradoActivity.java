package br.com.cielo.app.ui.main;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import br.com.cielo.app.R;
import br.com.cielo.app.ui.base.BaseActivity;
import butterknife.ButterKnife;
import cielo.sdk.order.OrderManager;

/**
 * Created by dioge on 03-Dec-17.
 */

public class CadastradoActivity extends BaseActivity {

    Context context;
    OrderManager orderManager;

    CallbackManager callbackManager;

    Button buttonEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrado);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_bar);

        ProfilePictureView profilePictureView;

        profilePictureView = (ProfilePictureView) findViewById(R.id.action_bar_back);

        profilePictureView.setProfileId(AccessToken.getCurrentAccessToken().getUserId());
        Profile profile = Profile.getCurrentProfile();
        ((TextView)findViewById(R.id.store_name)).setText(profile.getName());

        buttonEnviar=(Button)findViewById(R.id.buttonEnviarEmail);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(),ResultadoActivity.class);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}