package com.app.foodienitw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Owner_MainPage extends AppCompatActivity {

    private List<FoodItem> menuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Menu_Adapter mAdapter;

    public ImageView  addItem,removeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_main);


        addItem = findViewById(R.id.addFoodItem);
        removeItem = findViewById(R.id.removeFoodItem);

        recyclerView = (RecyclerView) findViewById(R.id.rv_food);

        mAdapter = new Menu_Adapter(menuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        pumpData();



        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodItem();
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFoodItem();
            }
        });


    }

    private void removeFoodItem() {

        //add a check function
        menuList.remove(menuList.size()-1);
        mAdapter.notifyDataSetChanged();
    }

    private void addFoodItem() {

        FoodItem foodItem = new FoodItem();
        menuList.add(foodItem);
        mAdapter.notifyDataSetChanged();


    }


    private void pumpData() {

        //testData

        FoodItem foodItem = new FoodItem();
        menuList.add(foodItem);
    }
}
