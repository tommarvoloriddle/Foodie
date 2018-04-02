package com.app.foodienitw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private Button login;
    private EditText email, password;
    private TextView forgot, registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = findViewById((R.id.btnlogin));
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        forgot = findViewById(R.id.btnforgot);
        registration = findViewById(R.id.btnRegister);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, signup.class));
            }
        });



    }
}
