package com.example.barcode;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoRM {

    @Query("INSERT INTO RecipeMaterial VALUES(:rpcode, :mname, :mcap, :mtype)") // 레시피 재료 insert
    void InsertRM(String rpcode, String mname, String mcap, String mtype);

    @Query("SELECT * FROM RecipeMaterial WHERE recipecode = :input")//코드로 레시피 재료 검색
    List<DbClasses.RecipeMaterial> getMaterial(String input);
}
