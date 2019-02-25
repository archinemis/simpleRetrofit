package com.example.retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Result> result;

    public RecyclerViewAdapter(Context context, List<Result> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Result results = result.get(position);
        holder.txtViewNim.setText(results.getNim());
        holder.txtViewNama.setText(results.getNama());
        holder.txtViewJenkel.setText(results.getJenisKelamin());
        holder.txtViewProdi.setText(results.getProdi());
    }

    @Override
    public int getItemCount(){
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textNIM)
        TextView txtViewNim;
        @BindView(R.id.textNama)
        TextView txtViewNama;
        @BindView(R.id.textJenkel)
        TextView txtViewJenkel;
        @BindView(R.id.textProdi)
        TextView txtViewProdi;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
