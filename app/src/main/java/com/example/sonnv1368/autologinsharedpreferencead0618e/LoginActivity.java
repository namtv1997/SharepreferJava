package com.example.sonnv1368.autologinsharedpreferencead0618e;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername;
    private EditText edPassword;
    private CheckBox cbSave;

    //luu du lieu dang xml
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        cbSave = findViewById(R.id.cbSave);

        preferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        getAccount();
    }

    public void login(View view) {
        String username = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Nhap thong tin", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, MainActivity.class));

            if (cbSave.isChecked()) {
                //luu che do login (auto login cho lan sau)
                saveAccount(username, password);
            } else {
                saveAccount("", "");
            }
        }
    }

    //dung de luu du lieu vao file xml
    private void saveAccount(String username, String password) {
        preferences.edit().putString("Username", username).commit();
        preferences.edit().putString("Password", password).commit();
        preferences.edit().putBoolean("isSave", cbSave.isChecked()).commit();
    }

    //lay thong tin trong file xml
    private void getAccount() {
        String username = preferences.getString("Username", "");
        String password = preferences.getString("Password", "");
        boolean isSave = preferences.getBoolean("isSave", false);
        if (isSave) {
            edUsername.setText(username);
            edPassword.setText(password);
            cbSave.setChecked(true);
        } else {
            edUsername.setText("");
            edPassword.setText("");
            cbSave.setChecked(false);
        }
    }
}
