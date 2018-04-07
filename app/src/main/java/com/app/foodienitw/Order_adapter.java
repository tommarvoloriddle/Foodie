package com.app.foodienitw;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Order_adapter extends RecyclerView.Adapter<Order_adapter.OrderViewHolder>{

    public List<Order> orderList;
    public  int[] myList = new int[20];




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
        holder.quantity.setText(String.valueOf(holder.count));


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public int[] getmyList() {
        return myList;
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        //public  int[] myList = new int[20];
        public TextView itemName,rate;
        public ImageView minusOrder,plusOrder;
        public TextView quantity;
        public int count =0;



        public OrderViewHolder(View view) {
            super(view);


            Log.d("tes","test00");
            itemName=view.findViewById(R.id.order_name);
            rate=view.findViewById(R.id.order_rate);
            quantity =view.findViewById(R.id.order_quantity);
            plusOrder = view.findViewById(R.id.add_order_item);
            minusOrder= view.findViewById(R.id.minus_order_item);

            plusOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    myList[getLayoutPosition()] = count;
//                    Log.e("asd",String.valueOf(orderQuantity.get(getLayoutPosition())));
                    notifyDataSetChanged();
//                    Log.e("count", String.valueOf(getLayoutPosition()));
//                    Log.e("size", String.valueOf(orderQuantity.size()));
//                    quantity.setText(count);
                }
            });

            minusOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count--;
                    if(count<=0){
                        count= 0;

                    }
                    notifyDataSetChanged();
//                    orderQuantity.set(getLayoutPosition() , count);
                    myList[getLayoutPosition()]= count;
                    Log.e("count", String.valueOf(myList.length));
                    Log.e("count", String.valueOf(myList[0]));
                    Log.e("count", String.valueOf(myList[1]));
                    Log.e("count", String.valueOf(myList[2]));
                }
            });
        }


    }


}
