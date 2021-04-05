package com.example.barcode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ItemViewHolder>{

    private ArrayList<DbClasses.RecipeMaterial> listResult = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_meterial, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listResult.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        DbClasses.RecipeMaterial recipeMaterial = listResult.get(i);
        itemViewHolder.Meterialname.setText(recipeMaterial.getMeterialname());
        itemViewHolder.Meterialcapacity.setText(recipeMaterial.getMeterialcapacity());
    }


    void addResult(DbClasses.RecipeMaterial recipeMaterial) {
        listResult.add(recipeMaterial);
        notifyDataSetChanged();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView Meterialname;
        private TextView Meterialcapacity;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            Meterialname = itemView.findViewById(R.id.MeterialName);
            Meterialcapacity = itemView.findViewById(R.id.MeterialCapacity);
        }
    }
}
