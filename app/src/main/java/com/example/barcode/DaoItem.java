package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoItem {
    @Query("SELECT * FROM Select_Item")
    List<Select_Item> getAll();

    @Insert
    void insert(Select_Item select_item);

    @Update
    void update(Select_Item select_item);

    @Delete
    void delete(Select_Item select_item);
}
