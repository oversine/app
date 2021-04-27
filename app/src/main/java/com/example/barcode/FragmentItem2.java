package com.example.barcode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FragmentItem2 extends Fragment {

    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    List<Select_Item> select_Item;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_ingredients);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);

        recyclerView.setLayoutManager(gridLayoutManager);
        iAdapter = new ItemAdapter();
        recyclerView.setAdapter(iAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));

        select_Item = Select_Item_Database.getInstance(getActivity()).daoItem().getAll2();
        int size = select_Item.size();
        for (int i = 0; i < size; i++) {
            iAdapter.addItem(select_Item.get(i));
        }
        return view;
    }
}