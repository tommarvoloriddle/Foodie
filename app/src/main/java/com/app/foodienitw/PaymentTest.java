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

public class PaymentTest extends AppCompatActivity {

    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_test);


        b=  findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent();
            }
        });
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

                + "&tn=" + trxnNote + "&am=" + payeeAmount
                 ;
        return UPI.replace(" ", "+");
    }
}
