package com.workspace.NusaLicious;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.workspace.societybeta.R;
import com.workspace.NusaLicious.models.nasModel;

import java.util.ArrayList;
import java.util.Random;

public class nasDetail extends AppCompatActivity {

    private DatabaseReference mref;
    ImageView imageView;
    ArrayList<nasModel> nasList;
    DatabaseReference referenceBeli;
    Integer nomor_transaksi = new Random().nextInt();
    Integer angkaBeli = 1;
    Integer totalHarga = 0;


    String userIdKey = "";
    String userId = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nas_detail);
        getUsernameLocal();
        //Parcelable
        Intent intent = getIntent();
        nasModel nasModel = intent.getParcelableExtra("judul");

        String Judul = nasModel.getJudul();
        final String Harga = nasModel.getHarga().toString();
        final Integer hargaMenu = Integer.valueOf(Harga);

        String Desc = nasModel.getDesc();
        String Gambar = nasModel.getGambar();



        imageView = findViewById(R.id.imageV);
        Picasso.get().load(Gambar).into(imageView);
        //ImageView imageView = findViewById(R.id.imageV);
        //imageView.setImageResource(Integer.parseInt(Images));
        final TextView mJudul = findViewById(R.id.textJudul);
        mJudul.setText(Judul);
        final TextView JmlHarga = findViewById(R.id.jmlHarga);
        JmlHarga.setText(Harga);
        TextView mDesc = findViewById(R.id.textDesc);
        mDesc.setText(Desc);

        Button btnBeli = findViewById(R.id.btn_Beli);
        final TextView btnMin = findViewById(R.id.btnMin);
        TextView btnPlus = findViewById(R.id.btnPlus);
        final TextView jmlBeli  = findViewById(R.id.jmlBeli);
        jmlBeli.setText(angkaBeli.toString());

        final TextView jmlHarga = findViewById(R.id.jmlHarga);

        jmlHarga.setText(hargaMenu.toString());



        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angkaBeli-=1;
                jmlBeli.setText(angkaBeli.toString());
                if(angkaBeli < 2){
                    btnMin.setEnabled(false);
                }

                totalHarga = angkaBeli * hargaMenu;

                jmlHarga.setText(totalHarga.toString());
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angkaBeli +=1;
                jmlBeli.setText(angkaBeli.toString());
                if(angkaBeli > 1){
                    btnMin.setEnabled(true);
                }

                totalHarga = angkaBeli * hargaMenu;

                jmlHarga.setText(totalHarga.toString());
            }
        });






        mref = FirebaseDatabase.getInstance().getReference().child("Naskot");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                referenceBeli = FirebaseDatabase.getInstance().getReference().child("Keranjang").child(userId)
                        .child(mJudul.getText().toString() +nomor_transaksi);
                referenceBeli.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        referenceBeli.getRef().child("judul").setValue(mJudul.getText().toString());
                        referenceBeli.getRef().child("jumlahBeli").setValue(angkaBeli);
                        String totalHargaa = (String) jmlHarga.getText();
                        Integer angkaHarga = Integer.parseInt(totalHargaa);
                        referenceBeli.getRef().child("harga").setValue(angkaHarga);

                        Intent chart = new Intent(nasDetail.this, CheckoutActivity.class);
                        startActivity(chart);
                        finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

    }

    public void getUsernameLocal() {

        SharedPreferences sharedPreferences = getSharedPreferences(userIdKey, MODE_PRIVATE);
         userId = sharedPreferences.getString("firebaseKey", "");

    }
}
