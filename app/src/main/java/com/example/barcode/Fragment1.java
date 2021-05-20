package com.example.barcode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment1 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<SavePd> table;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frige, container, false);

        recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.addItemDecoration(new RecyclerViewDecoration(30));
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        table = Product_Database.getInstance(getActivity()).daoSave().getAll();
        int size = table.size();
        for(int i = 0; i < size; i++){
            adapter.addData(table.get(i));
        }
    }
}