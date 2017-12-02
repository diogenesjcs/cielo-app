package br.com.cielo.app.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.cielo.app.R;
import butterknife.ButterKnife;

public class PrincipalAdapter extends RecyclerView.Adapter<PrincipalAdapter.PrincipalViewHolder> {

    @Inject
    public PrincipalAdapter() {
    }

    @Override
    public PrincipalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.principal, parent, false);
        return new PrincipalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PrincipalViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class PrincipalViewHolder extends RecyclerView.ViewHolder {

        public PrincipalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
