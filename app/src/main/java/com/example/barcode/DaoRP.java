package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DaoRP {

    @Query("INSERT INTO RecipeProcess VALUES(:rpcode, :outputseq, :cookingpro, :prourl)") //레시피 과정 insert
    void InsertRP(String rpcode, String outputseq, String cookingpro, String prourl);
}
