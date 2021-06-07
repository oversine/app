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
                    int pos = getBindingAdapterPosition(); // 수동등록의 특정 식재료 아이콘 선택 시 선택 아이템 위치를 찾아내어 해당 아이템의 식품명, 아이콘 이미지 주소, 기본 유통기한 값을 수동 등록 액티비티로 넘겨준다.
                    Manual_Res manual_res = Manual_Res.manual_res;
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(v.getContext(), Manual_Res.class);
                        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        String code = listitem.get(pos).getName();
                        String url = listitem.get(pos).getItemImg();
                        int date = listitem.get(pos).getDate();
                        intent.putExtra("식품명", code);
                        intent.putExtra("주소", url);
                        intent.putExtra("유통기한", date);
                        v.getContext().startActivity(intent);
                        manual_res.finish();
                        manual_res.overridePendingTransition(0,0);
                    }
                }
            });
        }
    }
}