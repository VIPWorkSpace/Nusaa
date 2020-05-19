package com.workspace.NusaLicious.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.societybeta.R;
import com.workspace.NusaLicious.models.ChartModel;


import java.util.ArrayList;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.MyViewHolder> {
    public Context context;
    private ArrayList<ChartModel> chartList;


    public ChartAdapter(Context c, ArrayList<ChartModel> p) {
        context = c;
        chartList = p;
    }

    @NonNull
    @Override
    public ChartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_cekout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChartAdapter.MyViewHolder holder, int position) {

        ChartModel chartModel = chartList.get(position);
        holder.title.setText(chartModel.getJudul());
        holder.totalPrice.setText(chartModel.getHarga().toString());
        holder.quantity.setText(chartModel.getJumlahBeli().toString());
        //holder.tvDesc.setText(nasList.get(position).getDesc());

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, totalPrice, quantity;


        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Title);
            totalPrice = itemView.findViewById(R.id.TotalPrice);
            quantity = itemView.findViewById(R.id.Quantity);


        }
    }



    @Override
    public int getItemCount() {
        return chartList.size();
    }
}
