package com.workspace.NusaLicious;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.workspace.NusaLicious.fragments.Akun;
import com.workspace.NusaLicious.fragments.Beranda;
import com.workspace.NusaLicious.fragments.Pesanan;
import com.workspace.societybeta.R;

public class MainActivity extends AppCompatActivity {
    String userIdKey = "";
    String userId = "";
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUsernameLocal();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navigationListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Beranda()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectFragment = null;
            switch (item.getItemId()){
                case R.id.navBeranda:
                    selectFragment = new Beranda();
                    break;
                case R.id.navPesanan:
                    selectFragment = new Pesanan();
                    break;
                case R.id.navAkun:
                    selectFragment = new Akun();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectFragment).commit();
            return true;
        }
    };


    public void getUsernameLocal() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
            sharedPreferences = getSharedPreferences(userIdKey, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("firebaseKey", userId);
            editor.apply();
        }


    }
}
