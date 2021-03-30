package com.example.barcode;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class RoomDBClasses {

    @Database(entities = {DbClasses.Barcode.class}, version = 1, exportSchema = false)
    public abstract static class Barcode_Database extends RoomDatabase {
        public abstract DaoBC DaoBC();

    }
    @Database(entities = {DbClasses.RecipeBasic.class}, version = 1, exportSchema = false)
    public abstract static class RecipeBasic_Database extends RoomDatabase{
        public abstract DaoRB DaoRB();

        private static  RecipeBasic_Database instance = null;

        public static synchronized RecipeBasic_Database getRecipe(Context context){
            if(instance == null){
                instance = Room.databaseBuilder(context.getApplicationContext(), RecipeBasic_Database.class, "RecipeBasic-db")
                        .allowMainThreadQueries()
                        .build();
            }
            return instance;
        }

    }
    @Database(entities = {DbClasses.RecipeProcess.class}, version = 1, exportSchema = false)
    public abstract static class RecipeProcess_Database extends RoomDatabase{
        public abstract DaoRP DaoRP();

    }
    @Database(entities = {DbClasses.RecipeMaterial.class}, version = 1, exportSchema = false)
    public abstract static class RecipeMaterial_Database extends RoomDatabase{
        public abstract DaoRM DaoRM();

    }

}
