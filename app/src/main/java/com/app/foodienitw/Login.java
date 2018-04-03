package com.app.foodienitw;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private Button login;
    private EditText email, password;
    private TextView forgot, registration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = findViewById((R.id.btnlogin));
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        forgot = findViewById(R.id.btnforgot);
        registration = findViewById(R.id.btnRegister);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        //FirebaseUser user = firebaseAuth.getCurrentUser();
        //if(user != null) {
        //    finish();
        //    startActivity(new Intent(Login.this, main_screen.class));
        //}

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, signup.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString().trim(), password.getText().toString().trim());
            }
        });

    }

    private void validate(String userName, String userPassword){

        if(userName.isEmpty() || userPassword.isEmpty()){
            Toast.makeText(this, "Enter complete Login details", Toast.LENGTH_SHORT).show();
        }else {
            progressDialog.setMessage("Loging In...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        checkEmailVerification();
                    } else {
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }
            });
        }
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();
        if(emailFlag){
            finish();
            startActivity(new Intent(Login.this, main_screen.class));
        }else{
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

}
