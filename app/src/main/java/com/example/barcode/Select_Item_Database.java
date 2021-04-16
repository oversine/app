package com.example.barcode;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Select_Item.class}, version = 1, exportSchema = false)
public abstract class Select_Item_Database extends RoomDatabase {
    public abstract DaoItem daoItem();
    private static Select_Item_Database instance = null;

    public static synchronized Select_Item_Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), Select_Item_Database.class, "Ingredient_item.db")
                    .createFromAsset("Ingredients.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
