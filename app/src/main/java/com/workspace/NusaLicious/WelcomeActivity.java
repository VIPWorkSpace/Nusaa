package com.workspace.NusaLicious;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.workspace.societybeta.R;

public class WelcomeActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_register;
    private TextView btn_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_admin = findViewById(R.id.btn_admin);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(WelcomeActivity.this, DaftarActivity.class);
                startActivity(register);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
    btn_admin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent loginadmin = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(loginadmin);
        }
    });
    }
}
