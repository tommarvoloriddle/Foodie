package com.app.foodienitw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private TextView resetemail;
    private Button resetpassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        resetemail = findViewById(R.id.etresetemail);
        resetpassword = findViewById(R.id.btnresetpassword);

        firebaseAuth = FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(view -> {
            String useremail = resetemail.getText().toString().trim();
            if(useremail.equals("")){
                Toast.makeText(ForgotPassword.this, "Enter your registered email ID", Toast.LENGTH_SHORT).show();
            }else{
                firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(ForgotPassword.this, "Password reset mail sent", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(ForgotPassword.this, Login.class));
                    }else{
                        Toast.makeText(ForgotPassword.this, "Register your email", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }
}
