package com.example.mistareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }
    // create -> account
    public void crearCuenta(View view){
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);

    }
    // login -> method
    public void login(View view){
        TextInputEditText user,password;
        user = (TextInputEditText)findViewById(R.id.user);
        password = (TextInputEditText)findViewById(R.id.password);
        if(user.getText().toString().equalsIgnoreCase("admin") && password.getText().toString().equalsIgnoreCase("admin")){
            // pasamos a la app
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }else{
            Toast t1 = Toast.makeText(this, "credenciales incorrectas", Toast.LENGTH_SHORT);
            t1.show();
        }

    }
}