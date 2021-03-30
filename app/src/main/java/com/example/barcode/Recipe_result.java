package com.example.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Recipe_result extends AppCompatActivity {

    TextView RecipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_result);

        Intent intent = getIntent();
        String code = intent.getStringExtra("레시피코드");

        String result_RcName = DatabaseBuilder.RecipeB_DB.DaoRB().search_RecipeName(code);

        RecipeName = findViewById(R.id.RecipeName);
        RecipeName.setText(result_RcName);

    }
}
