package com.example.barcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ItemViewHolder> implements Filterable {

    private List<DbClasses.RecipeBasic> listRecipe;
    private List<DbClasses.RecipeBasic> unlistRecipe;
    private ArrayList<DbClasses.RecipeBasic> RecipeBookMark = new ArrayList<>();

    public RecipeAdapter (List<DbClasses.RecipeBasic> list) {
        this.unlistRecipe = list;
        this.listRecipe = list;
    }


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
        itemViewHolder.RecipeCategory.setText(recipeBasic.getFoodcategory());
        Glide.with(itemViewHolder.itemView.getContext()).load(recipeBasic.getImageurl()).into(itemViewHolder.img);
    }


    void addRecipe(DbClasses.RecipeBasic recipeBasic) {
        listRecipe.add(recipeBasic);
        notifyDataSetChanged();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView RecipeName;
        private TextView RecipeCategory;
        private ImageView img;
        private CheckBox BookMarkImg;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            RecipeName = itemView.findViewById(R.id.RcName);
            RecipeCategory = itemView.findViewById(R.id.RcCategory);
            img = itemView.findViewById(R.id.imageRecipe);
            BookMarkImg = itemView.findViewById(R.id.btn_selector);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(v.getContext(), Recipe_result.class);
                        String code = listRecipe.get(pos).getRecipecode();
                        intent.putExtra("레시피코드", code);
                        v.getContext().startActivity(intent);
                        }
                    }
                });
            BookMarkImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                    }
                }
            });
            }
        }

    @Override // 필터 사용 검색기능
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<DbClasses.RecipeBasic> filterdeList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0 ) {
                    listRecipe = unlistRecipe;
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (DbClasses.RecipeBasic recipeBasic : unlistRecipe) {
                        if (recipeBasic.getRecipename().toLowerCase().contains(filterPattern)) {
                            filterdeList.add(recipeBasic);
                        }
                    }
                    listRecipe = filterdeList;
                }
                FilterResults results = new FilterResults();
                results.values = listRecipe;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listRecipe = (ArrayList<DbClasses.RecipeBasic>)results.values;
                notifyDataSetChanged();
            }
        };
    }