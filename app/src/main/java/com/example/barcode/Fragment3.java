package com.example.barcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment3 extends Fragment {

    private RecyclerView recyclerView;
    private RecipeAdapter rAdapter;
    private List<DbClasses.RecipeBasic> RcTable;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        recyclerView = view.findViewById(R.id.recyclerView2);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rAdapter = new RecipeAdapter();
        recyclerView.setAdapter(rAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));

        RcTable = RoomDBClasses.RecipeBasic_Database.getRecipe(getActivity()).DaoRB().getRecipe();
        int size = RcTable.size();
        for(int i = 0; i < size; i++){
            rAdapter.addRecipe(RcTable.get(i));
        }
        return view;
    }
}
