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

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private ArrayList<CategoryRVModal>categoryRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private CategoryClickInterface categoryClickInterface;

    public CategoryRVAdapter(ArrayList<CategoryRVModal> categoryRVModalArrayList, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRVModalArrayList = categoryRVModalArrayList;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.category_rv_item,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryRVModal categoryRVModal = categoryRVModalArrayList.get(position);
        holder.categoryNameTV.setText(categoryRVModal.getCategoryName());
        Picasso.get().load(categoryRVModal.getCategoryImage()).into(holder.categoryIV);
        seAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(position);

            }
        });



    }
//categeory RV adapter
    private void seAnimation(View itemView,int position){
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }

    }
    @Override
    public int getItemCount() {
        return categoryRVModalArrayList.size();
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView categoryNameTV;
        private ImageView categoryIV;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            categoryNameTV = itemView.findViewById(R.id.idTVCategoryName);
            categoryIV = itemView.findViewById(R.id.idTVCategory);
        }

    }

}
