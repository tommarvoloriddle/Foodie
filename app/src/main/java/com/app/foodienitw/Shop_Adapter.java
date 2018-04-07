package com.app.foodienitw;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class Shop_Adapter extends RecyclerView.Adapter<Shop_Adapter.ShopViewHolder> {

    public List<ShopDetails> shops;
    public Shop_Adapter(List<ShopDetails> shops) {
        this.shops = shops;
    }




    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_recycler, parent, false);
        return new ShopViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        ShopDetails shop =shops.get(position);
        holder.name.setText(shop.name);
        holder.location.setText(shop.location);
        holder.phoneNo.setText(shop.phoneno);
        holder.openorclose.setText(shop.openorclose);
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, location, phoneNo, openorclose;

        public ShopViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            name = view.findViewById(R.id.shop_name);
            location = view.findViewById(R.id.shop_location);
            phoneNo = view.findViewById(R.id.shop_phoneNo);
            openorclose = view.findViewById(R.id.show_isOpen);
        }

        @Override
        public void onClick(View view) {
            Context context=view.getContext();
            ShopDetails shop =shops.get(getLayoutPosition());
            Toast.makeText(view.getContext(), "position = " + shop.getName(), Toast.LENGTH_SHORT).show();

           Intent  intent =  new Intent(context, OrderPage.class);
           intent.putExtra("name" , shop.getName());
            context.startActivity(intent);


        }
    }
}
