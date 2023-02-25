package com.example.getaccomadation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    EditText edUsername ,edPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);

        DatabaseHelper db;
         //saving data to squlite
        btn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               //Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
               String username = edUsername.getText().toString();
               String password = edPassword.getText().toString();
               Database db =  new Database(getApplicationContext(),"getaccomoadation",null , 1);
               if (username.length() == 0 &&password.length() == 0) {
                   Toast.makeText(getApplicationContext(), "please fill all details", Toast.LENGTH_SHORT).show();
               }else{
                   if(db.Login(username,password) == 1) {
                       Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                       // Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
                       //  Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                       SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("username", username);
                       // to save our data with key and value.
                       editor.apply();
                       startActivity(new Intent(loginActivity.this, MainActivity.class));
                   } else {
                       Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
                   }
               }



            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(loginActivity.this,MainActivity.class));
            }
        });
    }
}




