package com.example.barcode;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoSave {
    @Query("SELECT * FROM SavePd")
    List<SavePd> getAll();

    @Insert
    void insert(SavePd savePd);

    @Update
    void update(SavePd savePd);

    @Delete
    void delete(SavePd savePd);
}
