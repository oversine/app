package com.example.barcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ItemViewHolder> implements Filterable {

    private List<DbClasses.RecipeBasic> listRecipe;
    private List<DbClasses.RecipeBasic> unlistRecipe;

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
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public int getItemCount() {
        return listRecipe.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        final DbClasses.RecipeBasic recipeBasic = listRecipe.get(i);
        itemViewHolder.BookMarkImg.setChecked(recipeBasic.getSelected());
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
                    int pos = getBindingAdapterPosition();
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
                    int pos = getBindingAdapterPosition();
                    DbClasses.RecipeBasic recipeBasic = listRecipe.get(pos); // 클릭한 특정 아이템 위치
                    CheckBox checkBox = (CheckBox) v;

                    if (!recipeBasic.getSelected() && checkBox.isChecked()){
                        recipeBasic.setSelected(true);
                        DatabaseBuilder.RecipeB_DB.DaoRB().update(recipeBasic); // 체크박스를 클릭했을 때, 체크박스가 false인 경우 true로 바꿔주고 DB에 업데이트
                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(),"북마크 등록이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    }else if(recipeBasic.getSelected()){
                        recipeBasic.setSelected(false);
                        DatabaseBuilder.RecipeB_DB.DaoRB().update(recipeBasic);
                        Toast.makeText(v.getContext(), "북마크가 해제되었습니다.", Toast.LENGTH_LONG).show();
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