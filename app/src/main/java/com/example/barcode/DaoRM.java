package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DaoRM {

    @Query("INSERT INTO RecipeMaterial VALUES(:rpcode, :mname, :mcap, :mtype)") // 레시피 재료 insert
    void InsertRM(String rpcode, String mname, String mcap, String mtype);
}
