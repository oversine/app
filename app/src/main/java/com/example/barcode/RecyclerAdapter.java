package com.example.barcode;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

    private ArrayList<SavePd> listdata = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        SavePd savePd = listdata.get(i);
        itemViewHolder.ProductName.setText(savePd.getPdName());
        itemViewHolder.ProductCategory.setText(savePd.getPdCategory());
        itemViewHolder.ProductData.setText(savePd.getPdData());
        itemViewHolder.img.setBackgroundColor(Color.LTGRAY);
    }


    void addData(SavePd savePd) {
        listdata.add(savePd);
        notifyDataSetChanged();
    }

    void removeItem(int position){
        listdata.remove(position);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView ProductName;
        private TextView ProductCategory;
        private TextView ProductData;
        private ImageView img;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            ProductName = itemView.findViewById(R.id.PdName);
            ProductCategory = itemView.findViewById(R.id.PdCategory);
            ProductData = itemView.findViewById(R.id.PdData);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
