package com.app.foodienitw;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Order_owner_adapter extends RecyclerView.Adapter<Order_owner_adapter.OrderVH> {

    public List<Order> orderList;

    public Order_owner_adapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public Order_owner_adapter.OrderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.owner_order_item, parent, false);

        return new Order_owner_adapter.OrderVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Order_owner_adapter.OrderVH holder, int position) {
        Order order =  orderList.get(position);
        holder.itemName.setText(order.itemName);
        holder.quantity.setText(order.quantity);
        holder.person.setText(order.rate);
    }

    @Override
    public int getItemCount() {
        return this.orderList.size();
    }

    public class OrderVH extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView quantity;
        public TextView person;

        public OrderVH(View view) {
            super(view);

            itemName=view.findViewById(R.id.order_name);
            quantity =view.findViewById(R.id.order_quantity);
            person=view.findViewById(R.id.orderperson);

        }
    }
}
