package com.workspace.NusaLicious;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.societybeta.R;
import com.workspace.NusaLicious.adapters.nasAdapter;
import com.workspace.NusaLicious.models.nasModel;

import java.util.ArrayList;

public class naskotMenu extends AppCompatActivity {

    DatabaseReference mref;
    RecyclerView naskotRecycler;
    ArrayList<nasModel> list;
    nasAdapter adapter;

    String userIdKey = "";
    String userId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naskot_menu);
        getUsernameLocal();
        naskotRecycler = findViewById(R.id.RecyclerNaskot);
        naskotRecycler.setHasFixedSize(true);
        naskotRecycler.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<nasModel>();

        mref = FirebaseDatabase.getInstance().getReference().child("Data").child("Menu").child("Naskot");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    nasModel nasModel = dataSnapshot1.getValue(nasModel.class);
                    list.add(nasModel);
                }
                adapter = new nasAdapter(naskotMenu.this, list);
                naskotRecycler.setAdapter(adapter);
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
