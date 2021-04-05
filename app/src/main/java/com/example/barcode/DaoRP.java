package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoRP {

    @Query("INSERT INTO RecipeProcess VALUES(:rpcode, :outputseq, :cookingpro, :prourl)") //레시피 과정 insert
    void InsertRP(String rpcode, String outputseq, String cookingpro, String prourl);

    @Query("SELECT * FROM RecipeProcess WHERE recipecode = :input")//코드로 레시피 재료 검색
    List<DbClasses.RecipeProcess> getProcess(String input);
}
