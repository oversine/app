package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoRB {
    @Query("INSERT INTO RecipeBasic VALUES(:rpcode, :rpname, :info, :type, :food, :time, :url)") // 레시피 기본 insert
    void InsertRB(String rpcode, String rpname, String info, String type, String food, String time, String url);

    @Query("SELECT * FROM RecipeBasic")
    List<DbClasses.RecipeBasic> getRecipe();

    @Query("SELECT recipename FROM RecipeBasic WHERE recipecode = :input")//바코드로 상품명 검색
    String search_RecipeName(String input);

    @Query("SELECT imageurl FROM RecipeBasic WHERE recipecode = :input")//코드로 레시피 이미지 검색
    String search_RecipeImg(String input);


    @Query("SELECT recipename FROM RecipeBasic")
    String search_RecipeName();

    @Query("SELECT recipecode FROM RecipeBasic")
    String search_RecipeCode();

    @Query("SELECT typecategory FROM RecipeBasic")
    String search_typecategory();

}
