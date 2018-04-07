package com.app.foodienitw;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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



        holder.name.setText(item.name);
        holder.rate.setText(item.rate);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        public TextView name,rate;

        public MenuViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.food_name);
            rate=view.findViewById(R.id.food_price);
        }
    }

}
