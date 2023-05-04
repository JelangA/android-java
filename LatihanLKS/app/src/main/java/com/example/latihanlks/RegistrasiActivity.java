package com.example.latihanlks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.latihanlks.backgtoundTasks.BackgroundTask;

public class RegistrasiActivity extends AppCompatActivity {

    Button btnRegister, btnSudahPunyaAkun;
    EditText namaLengkap, username, alamat, password, Cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        btnRegister = findViewById(R.id.btnRegisters);
        btnSudahPunyaAkun = findViewById(R.id.btnBack);

        namaLengkap = findViewById(R.id.txtNamaLengkap);
        username = findViewById(R.id.txtUsernames);
        alamat = findViewById(R.id.txtAlamat);
        password = findViewById(R.id.txtPasswords);
        Cpassword = findViewById(R.id.txtFKonfirmasi);

        btnSudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrasiActivity.this, MainActivity.class);
               startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
                backgroundTask.execute("registrasi",
                        namaLengkap.getText().toString(),
                        username.getText().toString(),
                        alamat.getText().toString(),
                        password.getText().toString(),
                        Cpassword.getText().toString()
                );
            }
        });

    }
}