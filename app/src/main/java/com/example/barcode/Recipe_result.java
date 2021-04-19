package com.example.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Recipe_result extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ResultAdapter vAdapter;
    private ProcessAdapter pAdapter;

    TextView RecipeName;
    ImageView RecipeImg;
    List<DbClasses.RecipeMaterial> result_Material;
    List<DbClasses.RecipeProcess> result_Process;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_result);

        Intent intent = getIntent();
        String code = intent.getStringExtra("레시피코드");

        String result_RcName = DatabaseBuilder.RecipeB_DB.DaoRB().search_RecipeName(code);

        RecipeName = findViewById(R.id.RecipeName);
        RecipeName.setText(result_RcName);

        String imgUrl = DatabaseBuilder.RecipeB_DB.DaoRB().search_RecipeImg(code);

        RecipeImg = findViewById(R.id.Recipe_profile);
        Glide.with(this).load(imgUrl).into(RecipeImg);

        recyclerView = findViewById(R.id.recyclerView3);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vAdapter = new ResultAdapter();
        recyclerView.setAdapter(vAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));

        result_Material = DatabaseBuilder.RecipeM_DB.DaoRM().getMaterial(code);
        int size = result_Material.size();
        for(int i = 0; i < size; i++){
            vAdapter.addResult(result_Material.get(i));
        }


        recyclerView = findViewById(R.id.recyclerView4);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pAdapter = new ProcessAdapter();
        recyclerView.setAdapter(pAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));

        result_Process = DatabaseBuilder.RecipeP_DB.DaoRP().getProcess(code);
        int size1 = result_Process.size();
        for(int i = 0; i < size1; i++){
            pAdapter.addProcess(result_Process.get(i));
        }

        Button delete_item = findViewById(R.id.Delete_item);
        delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent1 = new Intent(v.getContext(), MainActivity.class);
               intent1.putExtra("Frige", 1);
               finish();
               startActivity(intent1);
            }
        });
    }
}
