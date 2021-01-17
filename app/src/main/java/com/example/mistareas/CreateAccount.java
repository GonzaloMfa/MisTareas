package com.example.mistareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();

    }
    public void createAccount(View view){
        TextInputEditText user,password;
        user = (TextInputEditText)findViewById(R.id.userRegistro);
        password = (TextInputEditText)findViewById(R.id.password);
        if(!user.getText().toString().equalsIgnoreCase("") && !password.getText().toString().equalsIgnoreCase("")){
            // pasamos a la app
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }else{
            Toast t1 = Toast.makeText(this, "campos por rellenar", Toast.LENGTH_SHORT);
            t1.show();
        }

    }
}