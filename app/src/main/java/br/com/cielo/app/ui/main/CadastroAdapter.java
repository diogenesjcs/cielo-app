package br.com.cielo.app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import br.com.cielo.app.R;
import br.com.cielo.app.data.SyncService;
import br.com.cielo.app.ui.base.BaseActivity;
import br.com.cielo.app.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import cielo.orders.domain.Credentials;
import cielo.orders.domain.Order;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;

public class CadastroAdapter extends BaseActivity {

    Context context;
    OrderManager orderManager;

    CallbackManager callbackManager;

    Button buttonEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.cadastro);
        ButterKnife.bind(this);

        buttonEnviar=(Button)findViewById(R.id.buttonEnviar);
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
