package com.example.ecomarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.ViewHolder> {
    private ArrayList<com.example.ecomarket.ItemRVModal> itemRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private ItemClickInterface itemClickInterface;

    public ItemRVAdapter(ArrayList<com.example.ecomarket.ItemRVModal> itemRVModalArrayList, Context context, ItemClickInterface itemClickInterface) {
        this.itemRVModalArrayList = itemRVModalArrayList;
        this.context = context;
        this.itemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position2) {
    com.example.ecomarket.ItemRVModal itemRVModal = itemRVModalArrayList.get(position2);
    holder.itemNameTV.setText(itemRVModal.getItemName());
    holder.itemPriceTV.setText("Rs. "+itemRVModal.getItemPrice());
    Picasso.get().load(itemRVModal.getItemImage()).into(holder.itemIV);
    setAnimation(holder.itemView,position2);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         itemClickInterface.onItemClick(position2);
        }
    });
    }

    private void setAnimation(View itemView,int position){
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;

        }
    }
    @Override
    public int getItemCount() {
        return itemRVModalArrayList.size();
    }
    public interface ItemClickInterface{
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView itemNameTV,itemPriceTV;
    private ImageView itemIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTV = itemView.findViewById(R.id.idTVItemName);
            itemPriceTV = itemView.findViewById(R.id.idTVPrice);
            itemIV = itemView.findViewById(R.id.idIVItem);
        }
    }


}
