package com.example.barcode;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Barcode.class}, version = 2, exportSchema = false)
public abstract class Barcode_Database extends RoomDatabase{
    public abstract DaoBarcode daoBarcode();

}

