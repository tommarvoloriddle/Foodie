package com.app.foodienitw;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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
        holder.phoneNo.setText(shop.phoneNo);

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        public TextView name, location, phoneNo;

        public ShopViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.shop_name);
            location = view.findViewById(R.id.shop_location);
            phoneNo = view.findViewById(R.id.shop_phoneNo);
        }
    }
}
