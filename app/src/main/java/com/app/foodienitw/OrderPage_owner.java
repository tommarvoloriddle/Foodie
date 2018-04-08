package com.app.foodienitw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderPage_owner extends AppCompatActivity {

    private List<Order> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Order_owner_adapter mAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Button ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page_owner);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        recyclerView = findViewById(R.id.rv_order_owner);
        ready = findViewById(R.id.btnready);

        mAdapter = new Order_owner_adapter(orderList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        pumpData();

        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid());

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            dataSnapshot.child("Orders").getRef().removeValue();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }

    private void pumpData() {


        DatabaseReference abc = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());

        abc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ss : dataSnapshot.child("Orders").getChildren()){

                    String nameOrder =ss.child("itemName").getValue().toString();
                    String qOrder =ss.child("quantity").getValue().toString();
                    String nOrder =ss.child("rate").getValue().toString();
                    Order order = new Order();
                    order.setQuantity(qOrder + "  Nos");
                    order.setItemName(nameOrder);
                    order.setRate("By  " + nOrder);
                    orderList.add(order);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(OrderPage_owner.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
