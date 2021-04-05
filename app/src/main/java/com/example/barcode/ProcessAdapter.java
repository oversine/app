package com.example.barcode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ItemViewHolder>{

    private ArrayList<DbClasses.RecipeProcess> listProcess = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_process, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listProcess.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        DbClasses.RecipeProcess recipeProcess = listProcess.get(i);
        itemViewHolder.Outputsequence.setText(recipeProcess.getOutputsequence());
        itemViewHolder.Cookingprocess.setText(recipeProcess.getCookingprocess());
    }


    void addProcess(DbClasses.RecipeProcess recipeProcess) {
        listProcess.add(recipeProcess);
        notifyDataSetChanged();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView Outputsequence;
        private TextView Cookingprocess;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            Outputsequence = itemView.findViewById(R.id.ViewName);
            Cookingprocess = itemView.findViewById(R.id.ViewCapacity);
        }
    }
}
