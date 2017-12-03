package br.com.cielo.app.ui.main;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
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

public class CadastroActivity extends BaseActivity {

    Context context;
    OrderManager orderManager;

    CallbackManager callbackManager;

    Button buttonEnviar;
    Button buttonLimpar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_bar);

        ProfilePictureView profilePictureView;

        profilePictureView = (ProfilePictureView) findViewById(R.id.action_bar_back);

        profilePictureView.setProfileId(AccessToken.getCurrentAccessToken().getUserId());
        Profile profile = Profile.getCurrentProfile();
        ((TextView)findViewById(R.id.store_name)).setText(profile.getName());
        final AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.shipper);
        acTextView.setAdapter(new SuggestionAdapter(this,acTextView.getText().toString()));

        acTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SuggestionAdapter.ProfileAuto book = (SuggestionAdapter.ProfileAuto) adapterView.getItemAtPosition(position);
                acTextView.setText(book.name);
                ((TextView)findViewById(R.id.outputName)).setText(book.name);
                ((TextView)findViewById(R.id.outputName)).setVisibility(View.VISIBLE);
                ProfilePictureView profilePictureView;
                profilePictureView = (ProfilePictureView) findViewById(R.id.inputPic);
                profilePictureView.setProfileId(book.id);
                profilePictureView.setVisibility(View.VISIBLE);
            }
        });

        buttonEnviar=(Button)findViewById(R.id.buttonEnviar);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(),ResultadoActivity.class);
                startActivity(i);

            }
        });

        buttonLimpar=(Button)findViewById(R.id.buttonLimpar);
        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.outputName)).setVisibility(View.INVISIBLE);
                ProfilePictureView profilePictureView;
                profilePictureView = (ProfilePictureView) findViewById(R.id.inputPic);
                profilePictureView.setVisibility(View.INVISIBLE);
                acTextView.setText("");
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }




}
