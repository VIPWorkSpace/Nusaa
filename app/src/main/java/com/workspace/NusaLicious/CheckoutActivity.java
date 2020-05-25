package com.workspace.NusaLicious;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.NusaLicious.adapters.ChartAdapter;
import com.workspace.NusaLicious.models.ChartModel;
import com.workspace.societybeta.R;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    DatabaseReference referenceChart;

    RecyclerView recyclerViewChart;
    ArrayList<ChartModel> list;
    ChartAdapter chartAdapter;
    private Integer totalChart = 0;
    String userIdKey = "";
    String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getUsernameLocal();
        EditText name = findViewById(R.id.name);

        recyclerViewChart = findViewById(R.id.CheckoutRecycler);
        recyclerViewChart.setHasFixedSize(true);
        recyclerViewChart.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ChartModel>();


        referenceChart = FirebaseDatabase.getInstance().getReference().child("Keranjang").child(userId);
        referenceChart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ChartModel chartModel = dataSnapshot1.getValue(ChartModel.class);
                        list.add(chartModel);

                    }
                        chartAdapter = new ChartAdapter(CheckoutActivity.this, list);
                        recyclerViewChart.setAdapter(chartAdapter);

                        for (int i = 0; i < list.size(); i++) {
                            DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
                            ChartModel chartModel = snapshot.getValue(ChartModel.class);
                            totalChart += Integer.parseInt(list.get(i).getHarga().toString());
                            String toChart = Integer.toString(totalChart);
                            TextView tvTotal = findViewById(R.id.TotalPrice);
                            tvTotal.setText(String.format("Rp%s", toChart));
                        }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }

    public void getUsernameLocal() {

        SharedPreferences sharedPreferences = getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");

    }
}

