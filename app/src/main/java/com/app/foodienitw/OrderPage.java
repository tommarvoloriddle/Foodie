package com.app.foodienitw;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends AppCompatActivity {

    private List<Order> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Order_adapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv_order);

        mAdapter = new Order_adapter(orderList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        pumpData();

    }


    private void pumpData() {

        Order order =new Order();

        orderList.add(order);
        orderList.add(order);

        orderList.add(order);

        orderList.add(order);
        orderList.add(order);
        orderList.add(order);



        mAdapter.notifyDataSetChanged();
    }
}
