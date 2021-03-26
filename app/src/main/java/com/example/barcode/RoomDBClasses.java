package com.example.barcode;

import androidx.room.Database;
import androidx.room.RoomDatabase;

public class RoomDBClasses {

    @Database(entities = {DbClasses.Barcode.class}, version = 1, exportSchema = false)
    public abstract class Barcode_Database extends RoomDatabase {
        public abstract DaoBarcode daoBarcode();

    }
    @Database(entities = {DbClasses.RecipeBasic.class}, version = 1, exportSchema = false)
    public abstract class RecipeBasic_Database extends RoomDatabase{
        public abstract DaoBarcode daoBarcode();

    }
    @Database(entities = {DbClasses.RecipeProcess.class}, version = 1, exportSchema = false)
    public abstract class RecipeProcess_Database extends RoomDatabase{
        public abstract DaoBarcode daoBarcode();

    }
    @Database(entities = {DbClasses.RecipeMaterial.class}, version = 1, exportSchema = false)
    public abstract class RecipeMaterial_Database extends RoomDatabase{
        public abstract DaoBarcode daoBarcode();

    }

}
