package com.app.foodienitw;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
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

import static java.sql.Types.NULL;

public class main_screen extends AppCompatActivity {

    private List<ShopDetails> shopsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Shop_Adapter mAdapter;

    private DrawerLayout mDrawerLayout;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
 


        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        recyclerView = findViewById(R.id.rv);

        mAdapter = new Shop_Adapter(shopsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        pumpData();
    }

    private void pumpData() {

        DatabaseReference databaseReference = firebaseDatabase.getReferenceFromUrl("https://foodie-9167e.firebaseio.com/");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot :  dataSnapshot.getChildren()) {
                    if (snapshot.child("userType").getValue() != null) {
                        if (snapshot.child("userType").getValue().toString().equals("Owner")){
                            //Log.e("asjchb", snapshot.child("Shop Details").child("name").getValue().toString());

                            ShopDetails shop = new ShopDetails();
                            shop.name = snapshot.child("Shop Details").child("name").getValue().toString();
                            shop.location = snapshot.child("Shop Details").child("location").getValue().toString();
                            shop.setPhoneno(snapshot.child("userNumber").getValue().toString());
                            shop.openTime = snapshot.child("Shop Details").child("openTime").getValue().toString();
                            shop.closeTime = snapshot.child("Shop Details").child("closeTime").getValue().toString();

                            Date currentTime = Calendar.getInstance().getTime();
                            String abcde = currentTime.toString();

                            Integer ot, ct;

                            String oo = shop.openTime;
                            if (oo.length() < 4) {
                                oo = "0" + oo;
                            }

                            String co = shop.closeTime;
                            if (co.length() < 4) {
                                co = "0" + co;
                            }

                            Character ab = oo.charAt(2);
                            Character cd = co.charAt(2);

                            if (ab.toString().equals("P")) {
                                //ot = 12 + Character.getNumericValue(oo.charAt(0) + oo.charAt(1));

                                if (Character.getNumericValue(oo.charAt(0)) == 0) {
                                    ot = 12 + Character.getNumericValue(oo.charAt(1));
                                } else {
                                    ot = 12 + Character.getNumericValue(oo.charAt(0) + oo.charAt(1));
                                }

                            } else {
                                if (Character.getNumericValue(oo.charAt(0)) == 0) {
                                    ot = Character.getNumericValue(oo.charAt(1));
                                } else {
                                    ot = Character.getNumericValue(oo.charAt(0) + oo.charAt(1));
                                }
                            }

                            if (cd.toString().equals("P")) {
                                Log.e("qwerty", "in pm");
                                //ct = 12 + Character.getNumericValue(co.charAt(0) + co.charAt(1));

                                if (Character.getNumericValue(co.charAt(0)) == 0) {
                                    ct = 12 + Character.getNumericValue(co.charAt(1));
                                } else {
                                    ct = 12 + Character.getNumericValue(co.charAt(0) + co.charAt(1));
                                }

                            } else {
                                if (Character.getNumericValue(co.charAt(0)) == 0) {
                                    ct = Character.getNumericValue(co.charAt(1));
                                } else {
                                    ct = Character.getNumericValue(co.charAt(0) + co.charAt(1));
                                }
                            }

                            if (ot < ct) {
                                if (Character.getNumericValue(abcde.charAt(11) + abcde.charAt(12)) >= ot && Character.getNumericValue(abcde.charAt(11) + abcde.charAt(12)) <= ct) {
                                    shop.setOpenorclose("Open");
                                } else {
                                    shop.setOpenorclose("Close");
                                }
                            } else {
                                if (Character.getNumericValue(abcde.charAt(11) + abcde.charAt(12)) >= ot && Character.getNumericValue(abcde.charAt(11) + abcde.charAt(12)) <= ct) {
                                    shop.setOpenorclose("Close");
                                } else {
                                    shop.setOpenorclose("Open");
                                }
                            }

                            shopsList.add(shop);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(main_screen.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
