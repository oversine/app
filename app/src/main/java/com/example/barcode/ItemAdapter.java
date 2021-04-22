package com.example.barcode;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private ArrayList<Select_Item> listitem = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ingredients, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listitem.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Select_Item select_item = listitem.get(i);
        itemViewHolder.Name.setText(select_item.getName());
        Glide.with(itemViewHolder.itemView.getContext()).load(select_item.getItemImg()).into(itemViewHolder.ItemImg);
    }


    void addItem(Select_Item select_item) {
        listitem.add(select_item);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View V, int position);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView Name;
        private ImageView ItemImg;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            Name = itemView.findViewById(R.id.ingredients_name);
            ItemImg = itemView.findViewById(R.id.ingredients_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }
}