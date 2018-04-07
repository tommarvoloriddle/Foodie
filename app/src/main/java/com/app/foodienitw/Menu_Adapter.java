package com.app.foodienitw;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Menu_Adapter extends RecyclerView.Adapter<Menu_Adapter.MenuViewHolder> {

    public List<FoodItem> menuList;

    public Menu_Adapter(List<FoodItem> menuList) {
        this.menuList = menuList;
    }

    public void add(){
        FoodItem foodItem = new FoodItem();
        this.menuList.add(foodItem);

        Log.e("size" , String.valueOf(menuList.size()));
        Log.e("pposition" ,menuList.toString());
        notifyItemInserted(menuList.size()-1);
    }

    public  void  delete(){
        menuList.remove(0);
        Log.e("pposition" ,menuList.toString());
        notifyItemRemoved(0);
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);

        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        FoodItem item =  menuList.get(position);

        String a = holder.name.getText().toString().trim();
        String b = holder.rate.getText().toString().trim();

        holder.name.setText(a);
        holder.rate.setText(b);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        public TextView name,rate;

        public MenuViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.food_name);
            rate = view.findViewById(R.id.food_price);
        }
    }
}
