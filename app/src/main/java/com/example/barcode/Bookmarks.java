package com.example.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Bookmarks extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter rAdapter;
    private List<DbClasses.RecipeBasic> BookMarkTable = new ArrayList<>();
    MenuItem mSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_bookmark);

        Toolbar toolbar = findViewById(R.id.search_bookmark);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        recyclerView = findViewById(R.id.recyclerView_bookmark);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rAdapter = new RecipeAdapter(BookMarkTable);
        recyclerView.setAdapter(rAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDecoration(30));

        BookMarkTable = DatabaseBuilder.RecipeB_DB.DaoRB().getBookMark();
        int size = BookMarkTable.size();
        for(int i = 0; i < size; i++){
            rAdapter.addRecipe(BookMarkTable.get(i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

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
                rAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}