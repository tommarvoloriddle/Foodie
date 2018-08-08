package com.app.foodienitw;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentTest extends AppCompatActivity {

    Button b;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    public int sum =0 ,i =0;
    public  int[] myList = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_test);
        Intent intent = new Intent();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        Bundle extras = getIntent().getExtras();
         myList = extras.getIntArray("order");


//        Log.e("intetn",extras.getString("name"));
//
//       String newString= (String) savedInstanceState.getSerializable("name");
//       if(newString != null){
//           Log.e("string" ,newString);
//       }

        b=  findViewById(R.id.totalAmount);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent();
            }
        });
        getAmount();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

           Log.d("requestcode",requestCode+"");
          Log.d("resultcode",resultCode+"");
        String abc=null;
        if(data!=null){
            //intent data is available here for marshmallow
            Bundle bundle = data.getExtras();
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                    Log.d("VAL", String.format("%s %s (%s)", key,
                value.toString(), value.getClass().getName()));
                abc=value.toString();



            }
        }
        else{
            //intent data is null for kitkat
            Toast.makeText(this,"Data is Null",Toast.LENGTH_LONG).show();

        }
    }



    @SuppressLint("RestrictedApi")
    private void sendIntent() {
       String UPI = getUPIString("8297249188@upi","Saaketh Koundinya", "test", "1");

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(UPI));

        Intent chooser = Intent.createChooser(intent, "Pay with...");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivityForResult(chooser, 1, null);
        }



    }
    private String getUPIString(String payeeAddress, String payeeName,
                                String trxnNote, String payeeAmount) {


        String UPI = "upi://pay?pa=" + payeeAddress + "&pn=" + payeeName

                + "&tn=" + trxnNote + "&am=" + String.valueOf(sum)
                 ;
        return UPI.replace(" ", "+");
    }

    public void getAmount() {


        DatabaseReference databaseReference = firebaseDatabase.getReferenceFromUrl("https://foodie-9167e.firebaseio.com/");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Intent intent = getIntent();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.child("userType").getValue() != null) {
                        if (snapshot.child("userType").getValue().toString().equals("Owner")) {
                            Log.e("asjchb", snapshot.child("Shop Details").child("name").getValue().toString());


                            if (snapshot.child("Shop Details").child("name").getValue().toString().equals(intent.getStringExtra("name"))) {


                                Log.e("sadads", snapshot.child("Menu Items").toString());

                                for (DataSnapshot ss : snapshot.child("Menu Items").getChildren()) {
                                    Log.e("log", ss.getValue().toString());

                                    String nameOrder = ss.child("name").getValue().toString();
                                    String rateOrder = ss.child("rate").getValue().toString();

                                    sum = sum + Integer.valueOf(rateOrder) * (int) myList[i];
                                    i++;
                                    Log.e("amoount", String.valueOf(sum));

                                }

                            }
                        }
                    }

                b.setText("Please pay " + String.valueOf(sum));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PaymentTest.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        Log.e("here" , String.valueOf(sum));
    }
}
