package com.app.foodienitw;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderPage extends AppCompatActivity {

    private List<Order> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Order_adapter mAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    public  int[] myList = new int[20];
    private Button pay;
//    Intent intent = getIntent();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_page);

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv_order);

        mAdapter = new Order_adapter(orderList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        TextView shopName = findViewById(R.id.shopNameOrder) ;
        Intent intent = getIntent();

        String ss =intent.getStringExtra("name");
         Log.e("asd" ,intent.getStringExtra("name"));
          shopName.setText(ss);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        pumpData();
        pay = findViewById(R.id.pay);
        Log.d("test", String.valueOf(myList[1]));
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList = mAdapter.getmyList();
                Log.d("test" , String.valueOf(myList[2]));
                Intent  intent =  new Intent(OrderPage.this, PaymentTest.class);
                intent.putExtra("name", ss);
                intent.putExtra("order" , myList);
                OrderPage.this.startActivity(intent);

                }
        });

    }


    private void pumpData() {



        DatabaseReference databaseReference = firebaseDatabase.getReferenceFromUrl("https://foodie-9167e.firebaseio.com/");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Intent intent = getIntent();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("userType").getValue().toString().equals("Owner")) {
                        Log.e("asjchb", snapshot.child("Shop Details").child("name").getValue().toString());

                        if(snapshot.child("Shop Details").child("name").getValue().toString().equals(intent.getStringExtra("name"))){


                            Log.e("sadads", snapshot.child("Menu Items").toString());

                            for (DataSnapshot ss : snapshot.child("Menu Items").getChildren()){
                                Log.e("log", ss.getValue().toString());

                                String nameOrder =ss.child("name").getValue().toString();
                                String rateOrder =ss.child("rate").getValue().toString();
                                Order order = new Order(nameOrder ,rateOrder);
                                orderList.add(order);
                                mAdapter.notifyDataSetChanged();

                            }

                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(OrderPage.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
//
////    public void onClick(View v) {
//        startActivity(new Intent(OrderPage.this , PaymentTest.class));
//    }