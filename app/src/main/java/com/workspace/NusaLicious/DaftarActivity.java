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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.workspace.NusaLicious.models.usersModel;
import com.workspace.societybeta.R;

public class DaftarActivity extends AppCompatActivity {

    TextInputEditText textEmail, numberphone, textNama, textPassword;
    Button btn_daftar;
    FirebaseDatabase mDatabase;
    private SharedPreferences sharedPreferences;

    DatabaseReference mref;
    FirebaseAuth mAuth;
   private String USERNAME_KEY = "usernamekey";
   private String username_key = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        //findViewById
        final LoadingDialog loadingDialog = new LoadingDialog(DaftarActivity.this);
        textPassword = findViewById(R.id.textPasswordLogin);
        textEmail = findViewById(R.id.textEmailLogin);
        numberphone = findViewById(R.id.phone);
        textNama = findViewById(R.id.textNama);
        btn_daftar = findViewById(R.id.btn_daftar);

        mref = FirebaseDatabase.getInstance().getReference("User");
        mAuth = FirebaseAuth.getInstance();
        btn_daftar.setOnClickListener(new View.OnClickListener() {
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
                final String name = textNama.getText().toString();
                final String email = textEmail.getText().toString();
                final String phone = numberphone.getText().toString();
                final String password = textPassword.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Tolong Masukkan Nama",
                            Toast.LENGTH_LONG).show();

                } else if (TextUtils.isEmpty(email) || email.length() < 5) {
                    Toast.makeText(getApplicationContext(), "Email tidak Valid",
                            Toast.LENGTH_LONG).show();

                } else if (TextUtils.isEmpty(phone) || phone.length() <= 11) {
                    Toast.makeText(getApplicationContext(), "telepon minimal 11digit",
                            Toast.LENGTH_LONG).show();


                }  else if (TextUtils.isEmpty(password)|| password.length() < 6){
                    Toast.makeText(getApplicationContext(), "password minimal 6 karakter",
                            Toast.LENGTH_SHORT).show();

                }

                else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        // Daftar Berhasil
                                        usersModel info = new usersModel(name, phone, email, password);
                                        //Menyimpan data kedalam firebaseDatabase
                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(DaftarActivity.this, "Daftar Sukses",
                                                        Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            }
                                        });
                                    }

                                    else {
                                        // Daftar Gagal jika email sudah terdaftar sebelumnya.
                                        Toast.makeText(DaftarActivity.this, "Email sudah terdaftar, silahkan login",
                                                Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
