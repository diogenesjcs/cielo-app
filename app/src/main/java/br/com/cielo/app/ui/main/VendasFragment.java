package br.com.cielo.app.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.cielo.app.R;

/**
 * Created by diogenes.j.da.silva on 02/12/2017.
 */

public class VendasFragment extends Fragment {
    Button buttonCadastro;
    Button buttonExistente;

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static  VendasFragment newInstance(int page, String title) {
        VendasFragment fragmentFirst = new  VendasFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vendas, container, false);
        buttonCadastro=(Button)view.findViewById(R.id.buttonNovoCadastro);
        buttonCadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getActivity().getApplicationContext(),CadastroActivity.class);
                startActivity(i);
            }
        });

        buttonExistente=(Button)view.findViewById(R.id.buttonCadastrado);
        buttonExistente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getActivity().getApplicationContext(),CadastroActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}
