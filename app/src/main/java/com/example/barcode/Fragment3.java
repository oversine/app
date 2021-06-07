package com.example.barcode;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {

    private RecyclerView recyclerView;
    private RecipeAdapter rAdapter;
    private List<DbClasses.RecipeBasic> RcTable = new ArrayList<>();
    MenuItem mSearch;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        setHasOptionsMenu(true);

        Toolbar toolbar = view.findViewById(R.id.search_toolbar);
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        } // 기본적으로 앱에서 액션바를 false 해뒀으나 레시피 프래그먼트에서 액션바를 사용해 검색기능을 구현하기 위해 액션바를 해당 프래그먼트에 한정하여 활성화함

        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.addItemDecoration(new RecyclerViewDecoration(30));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.search_menu, menu);

        mSearch = menu.findItem(R.id.search);

        mSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return false;
            }
        });

        SearchView sv = (SearchView) mSearch.getActionView();
        sv.setSubmitButtonEnabled(true);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                rAdapter.getFilter().filter(newText); // 액션바에 구현된 검색 아이콘을 선택해 키워드를 입력했을 때 해당 키워드와 일치하는 값만 리스트에 추가
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rAdapter = new RecipeAdapter(RcTable);
        recyclerView.setAdapter(rAdapter);

        RcTable.clear();
        RcTable = DatabaseBuilder.RecipeB_DB.DaoRB().getRecipe();
        int size = RcTable.size();
        for(int i = 0; i < size; i++){
            rAdapter.addRecipe(RcTable.get(i));
        }
    }
}
