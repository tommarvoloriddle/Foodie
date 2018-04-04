package com.app.foodienitw;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class signup extends AppCompatActivity {

    private EditText name2;
    private EditText email2;
    private EditText password2;
    private EditText phoneno2;
    private Button signup2;
    private CheckBox owner2;
    private Spinner spinner;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String name1, email1, password1, phoneno1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name2 = findViewById(R.id.etnewname);
        email2 = findViewById(R.id.etnewemail);
        password2 = findViewById(R.id.etnewpassword);
        phoneno2 = findViewById(R.id.etnewphone);
        signup2 = findViewById(R.id.btnsignup);
        owner2 = findViewById(R.id.isOwner);
        spinner = findViewById(R.id.etspinner);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String user_name = email2.getText().toString().trim();
                    String user_password = password2.getText().toString().trim();

                    progressDialog.setMessage("Signing Up...");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(user_name, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(signup.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                                progressDialog.dismiss();
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    private boolean validate(){
        boolean result = false;

        //make these variable global
        name1 = name2.getText().toString().trim();
        email1 = email2.getText().toString().trim();
        password1 = password2.getText().toString().trim();
        phoneno1 = phoneno2.getText().toString().trim();

        if(name1.isEmpty() || email1.isEmpty() || password1.isEmpty() || phoneno1.isEmpty()){
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        //Toast.makeText(signup.this, "Successfully Registered -- Verification mail sent", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(signup.this,main_screen.class));
                    }else{
                        Toast.makeText(signup.this, "Verification mail not sent / Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData(){
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        UserProfile userProfile = new UserProfile(name1,email1,password1,phoneno1);
        databaseReference.setValue(userProfile);

    }

}
