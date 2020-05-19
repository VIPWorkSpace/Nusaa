package com.workspace.NusaLicious.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.workspace.NusaLicious.SplashActivity;
import com.workspace.NusaLicious.CheckoutActivity;
import com.workspace.societybeta.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Akun extends Fragment {

    Button btn_keluar, chart;
    FirebaseAuth mAuth;

    String userIdKey = "";
    String userId = "";

    private FirebaseAuth.AuthStateListener mAuthListener;

    public Akun() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        btn_keluar = view.findViewById(R.id.btn_Keluar);
        chart = view.findViewById(R.id.btn_chart);


        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsernameLocal();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), SplashActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    public void getUsernameLocal() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", null);

    }

}
