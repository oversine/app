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
    @Query("SELECT * FROM Select_Item WHERE Category = '채소'")
    List<Select_Item> getAll();

    @Query("SELECT * FROM Select_Item WHERE Category = '양곡'")
    List<Select_Item> getAll2();

    @Query("SELECT * FROM Select_Item WHERE Category = '견과'")
    List<Select_Item> getAll3();

    @Query("SELECT * FROM Select_Item WHERE Category = '과일'")
    List<Select_Item> getAll4();

    @Query("SELECT * FROM Select_Item WHERE Category = '육류'")
    List<Select_Item> getAll5();

    @Query("SELECT * FROM Select_Item WHERE Category = '수산물'")
    List<Select_Item> getAll6();

    @Query("SELECT * FROM Select_Item WHERE Category = '유제품'")
    List<Select_Item> getAll7();

    @Insert
    void insert(Select_Item select_item);

    @Update
    void update(Select_Item select_item);

    @Delete
    void delete(Select_Item select_item);
}
