package com.example.barcode;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters({Converters.class})
@Database(entities = {SavePd.class}, version = 1, exportSchema = false)
public abstract class Product_Database extends RoomDatabase {
    public abstract DaoSave daoSave();
    private static Product_Database instance = null;

    public static synchronized Product_Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), Product_Database.class, "Product.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
