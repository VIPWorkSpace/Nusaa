package com.workspace.NusaLicious;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.workspace.societybeta.R;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText textEmail, textPassword;
    Button btn_Masuk;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;
    //tescommit dong
    SharedPreferences sharedPreferences;
    String userIdKey = "";
    String userId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);
        textEmail = findViewById(R.id.textEmailLogin);
        textPassword = findViewById(R.id.textPasswordLogin);
        btn_Masuk = findViewById(R.id.btn_Masuk);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (currentUser != null){
                    userId = currentUser.getUid();
                    sharedPreferences = getSharedPreferences(userIdKey, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("firebaseKey", userId);
                    editor.apply();
                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    }, 5000);
                    Toast.makeText(LoginActivity.this, "Kamu sudah Masuk", Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(login);
                } else {
                    Toast.makeText(LoginActivity.this, "Kamu Belum Masuk, Tolong Masuk", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btn_Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 6000);
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Tolong Isi Email",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Tolong Lengkapi Kolom Tersebut",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                else if (!email.isEmpty() && !password.isEmpty()){

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if (!task.isSuccessful()){
                               Toast.makeText(LoginActivity.this, "Maaf Anda Gagal Masuk, Silahkan Ulangi", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               Intent login = new Intent(LoginActivity.this, MainActivity.class);
                               startActivity(login);
                           }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
