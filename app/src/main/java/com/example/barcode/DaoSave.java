package com.example.barcode;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoSave {
    @Query("SELECT * FROM SavePd ORDER BY MainDate")
    List<SavePd> getAll();

    @Query("SELECT * FROM SavePd WHERE MainDate <= :input ORDER BY MainDate")
    List<SavePd> getDate(Date input);

    @Insert
    void insert(SavePd savePd);

    @Update
    void update(SavePd savePd);

    @Delete
    void delete(SavePd savePd);
}
