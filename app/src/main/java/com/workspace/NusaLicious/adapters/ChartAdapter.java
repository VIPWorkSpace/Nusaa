package com.workspace.NusaLicious.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.NusaLicious.CheckoutActivity;
import com.workspace.societybeta.R;
import com.workspace.NusaLicious.models.ChartModel;


import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.MyViewHolder> {
    public Context context;
    private ArrayList<ChartModel> chartList;

    DatabaseReference referenceRemove;

    String userIdKey = "";
    String userId = "";


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
    public void onBindViewHolder(@NonNull ChartAdapter.MyViewHolder holder, final int position) {

        final ChartModel chartModel = chartList.get(position);
        holder.title.setText(chartModel.getJudul());
        holder.totalPrice.setText(chartModel.getHarga().toString());
        holder.quantity.setText(chartModel.getJumlahBeli().toString());
        //holder.tvDesc.setText(nasList.get(position).getDesc());

        //delete chart
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeAt(position);
            }
        });

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, totalPrice, quantity;
        ImageView remove;


        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Title);
            totalPrice = itemView.findViewById(R.id.TotalPrice);
            quantity = itemView.findViewById(R.id.Quantity);
            remove = itemView.findViewById(R.id.Btn_remove);


        }
    }



    @Override
    public int getItemCount() {
        return chartList.size();
    }

    public void removeAt(final int position){
        getUsernameLocal();
        referenceRemove = FirebaseDatabase.getInstance().getReference().child("Keranjang").child(userId);

        referenceRemove.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chartList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, chartList.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");

    }

}




