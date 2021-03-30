package com.example.barcode;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>{

    private ArrayList<DbClasses.RecipeBasic> listRecipe = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipe, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listRecipe.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        DbClasses.RecipeBasic recipeBasic = listRecipe.get(i);
        itemViewHolder.RecipeName.setText(recipeBasic.getRecipename());
        itemViewHolder.RecipeCode.setText(recipeBasic.getRecipecode());
        itemViewHolder.RecipeCategory.setText(recipeBasic.getTypecategory());
        itemViewHolder.img.setBackgroundColor(Color.LTGRAY);
    }


    void addRecipe(DbClasses.RecipeBasic recipeBasic) {
        listRecipe.add(recipeBasic);
        notifyDataSetChanged();
    }

    void removeItem(int position){
        listRecipe.remove(position);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView RecipeName;
        private TextView RecipeCode;
        private TextView RecipeCategory;
        private ImageView img;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            RecipeName = itemView.findViewById(R.id.RcName);
            RecipeCode = itemView.findViewById(R.id.RcCode);
            RecipeCategory = itemView.findViewById(R.id.RcCategory);
            img = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        DbClasses.RecipeBasic item = listRecipe.get(pos);
                    }
                }
            });
        }
    }
}