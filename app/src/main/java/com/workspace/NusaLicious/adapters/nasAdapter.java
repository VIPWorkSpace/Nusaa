package com.workspace.NusaLicious.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;
import com.workspace.NusaLicious.nasDetail;
import com.workspace.societybeta.R;
import com.workspace.NusaLicious.models.nasModel;

import java.util.ArrayList;

public class nasAdapter extends RecyclerView.Adapter<nasAdapter.MyViewHolder> {
    Context context;
    ArrayList<nasModel> nasList;
    DataSnapshot dataSnapshot;

    public nasAdapter(Context c, ArrayList<nasModel>p){
        context = c;
        nasList = p;
    }
    @NonNull
    @Override
    public nasAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull nasAdapter.MyViewHolder holder, final int position) {
        nasModel Nasmodel = nasList.get(position);
        holder.tvJudul.setText(Nasmodel.getJudul());
        holder.tvHarga.setText(Nasmodel.getHarga().toString());
        //holder.tvDesc.setText(nasList.get(position).getDesc());
        Picasso.get().load(Nasmodel.getGambar()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, nasDetail.class);
                //Send using putextra
                intent.putExtra("judul", nasList.get(position));
                intent.putExtra("gambar", nasList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvHarga,tvDesc;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_Judul);
            tvHarga = itemView.findViewById(R.id.tv_Harga);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
