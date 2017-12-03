package br.com.cielo.app.ui.main;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import org.jetbrains.annotations.NotNull;

import br.com.cielo.app.R;
import br.com.cielo.app.ui.base.BaseActivity;
import butterknife.ButterKnife;
import cielo.orders.domain.Credentials;
import cielo.orders.domain.Order;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.ServiceBindListener;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;

public class ResultadoActivity extends BaseActivity {

    Context context;
    OrderManager orderManager;

    CallbackManager callbackManager;

    Button buttonMensagem;
    Button buttonEmail;
    Button buttonImprimir;
    Button buttonContinuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_bar);

        ProfilePictureView profilePictureView;

        profilePictureView = (ProfilePictureView) findViewById(R.id.action_bar_back);

        profilePictureView.setProfileId(AccessToken.getCurrentAccessToken().getUserId());
        Profile profile = Profile.getCurrentProfile();
        ((TextView)findViewById(R.id.store_name)).setText(profile.getName());

        buttonMensagem=findViewById(R.id.buttonMensagem);
        buttonMensagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });

        buttonEmail=findViewById(R.id.buttonEmail);
        buttonEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });

        buttonImprimir=findViewById(R.id.buttonImprimir);
        buttonImprimir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });
        ServiceBindListener serviceBindListener = new ServiceBindListener() {
            @Override
            public void onServiceBound() {
                buttonContinuar=findViewById(R.id.buttonContinuar);
                buttonContinuar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PaymentListener paymentListener = new PaymentListener() {
                            @Override
                            public void onStart() {
                                Log.d("MinhaApp", "O pagamento começou.");
                            }

                            @Override
                            public void onPayment(@NotNull Order order) {
                                Log.d("MinhaApp", "Um pagamento foi realizado.");
                            }

                            @Override public void onCancel() {
                                Log.d("MinhaApp", "A operação foi cancelada.");
                            }

                            @Override public void onError(@NotNull PaymentError paymentError) {
                                Log.d("MinhaApp", "Houve um erro no pagamento.");
                            }
                        };
                        Order order = orderManager.createDraftOrder("Pedido de teste");
                        orderManager.checkoutOrder(order.getId(), paymentListener);

                    }
                });
                // O serviço está vinculado
            }

            @Override
            public void onServiceUnbound() {
                // O serviço foi desvinculado
            }
        };
        Credentials credentials = new Credentials("zK33GGEOY6QA", "McRAnEmKi6Qm");
        orderManager = new OrderManager(credentials, context);
        orderManager.bind(this, serviceBindListener);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }




}
