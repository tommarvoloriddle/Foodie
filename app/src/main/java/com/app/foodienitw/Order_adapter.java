package com.app.foodienitw;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Order_adapter extends RecyclerView.Adapter<Order_adapter.OrderViewHolder>{

    public List<Order> orderList;

    public Order_adapter(List<Order> order) {
        this.orderList = order;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);

        return new Order_adapter.OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order =  orderList.get(position);
        holder.itemName.setText(order.itemName);
        holder.rate.setText(order.rate);
        holder.quatity.setText(order.quantity);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }




    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName,rate,quatity;

        public OrderViewHolder(View view) {
            super(view);
            itemName=view.findViewById(R.id.order_name);
            rate=view.findViewById(R.id.order_rate);
            quatity =view.findViewById(R.id.order_quantity);
        }
    }
}
