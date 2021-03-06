package com.example.barcode;

import androidx.room.Database;
import androidx.room.RoomDatabase;
//db 생성하는 코드(한곳에 모아두기 위해 내부 클래스 사용)
public class RoomDBClasses {

    @Database(entities = {DbClasses.Barcode.class}, version = 1, exportSchema = false)
    public abstract static class Barcode_Database extends RoomDatabase {
        public abstract DaoBC DaoBC();

    }
    @Database(entities = {DbClasses.RecipeBasic.class}, version = 1, exportSchema = false)
    public abstract static class RecipeBasic_Database extends RoomDatabase{
        public abstract DaoRB DaoRB();

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
