package com.app.foodienitw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Owner_MainPage extends AppCompatActivity {

    public ImageView addItem;
    public Button update1;
    public TextView item_name, item_price;
    public EditText shop_Name,shop_Location,open_Time,close_Time;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Spinner spinclose, spinopen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_main);

        addItem = findViewById(R.id.addFoodItem);
        item_name = findViewById(R.id.food_name);
        item_price = findViewById(R.id.food_price);
        shop_Name = findViewById(R.id.shopName);
        shop_Location = findViewById(R.id.shopLocation);
        open_Time = findViewById(R.id.openingTime);
        close_Time = findViewById(R.id.ClosingTime);
        update1 = findViewById(R.id.updateDetails);
        spinclose = findViewById(R.id.spinnerClose);
        spinopen = findViewById(R.id.spinnerOpen);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        addItem.setOnClickListener(v -> addFoodItem());

        DatabaseReference abc = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());

        abc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ShopDetails shopDetails = dataSnapshot.child("Shop Details").getValue(ShopDetails.class);
                if(shopDetails != null){
                    startActivity(new Intent(Owner_MainPage.this, OrderPage_owner.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Owner_MainPage.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        update1.setOnClickListener(v -> {
            String close = spinclose.getSelectedItem().toString();
            String open = spinopen.getSelectedItem().toString();
            DatabaseReference a = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            DatabaseReference shopdetailsref = a.child("Shop Details");
            ShopDetails shopDetails = new ShopDetails(shop_Name.getText().toString().trim(),shop_Location.getText().toString().trim(),
                    open_Time.getText().toString().trim() + open,
                    close_Time.getText().toString().trim() + close);
            shopdetailsref.setValue(shopDetails);
        });
    }

    private void addFoodItem() {
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        DatabaseReference menuref = databaseReference.child("Menu Items");
        FoodItem foodItem = new FoodItem(item_name.getText().toString().trim(),item_price.getText().toString().trim());
        menuref.push().setValue(foodItem);
    }
}
