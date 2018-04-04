package com.app.foodienitw;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class main_screen extends AppCompatActivity {

    private List<ShopDetails> shopsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Shop_Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        mAdapter = new Shop_Adapter(shopsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        pumpData();
    }

    private void pumpData() {
        ShopDetails shop =new ShopDetails();
        shopsList.add(shop);
        shopsList.add(shop);
        shopsList.add(shop);
        shopsList.add(shop);
        shopsList.add(shop);
        shopsList.add(shop);
        shopsList.add(shop);
        shopsList.add(shop);
        shopsList.add(shop);
        shopsList.add(shop);

        mAdapter.notifyDataSetChanged();
    }
}
