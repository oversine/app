package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoRB {
    @Query("INSERT INTO RecipeBasic VALUES(:rpcode, :rpname, :info, :type, :food, :time, :url, :test)") // 레시피 기본 insert
    void InsertRB(String rpcode, String rpname, String info, String type, String food, String time, String url, boolean test);

    @Query("SELECT * FROM RecipeBasic")
    List<DbClasses.RecipeBasic> getRecipe();

    @Query("SELECT * FROM RecipeBasic WHERE isSelected = 1") // 레시피 북마크, 체크박스 true 값만 리사이클러뷰 갱신
    List<DbClasses.RecipeBasic> getBookMark();

    @Query("SELECT recipename FROM RecipeBasic WHERE recipecode = :input")//바코드로 상품명 검색
    String search_RecipeName(String input);

    @Query("SELECT imageurl FROM RecipeBasic WHERE recipecode = :input")//코드로 레시피 이미지 검색
    String search_RecipeImg(String input);

    @Query("SELECT * FROM RecipeBasic WHERE recipecode = :input1 OR recipecode = :input2 OR recipecode = :input3 OR recipecode = :input4 OR recipecode = :input5 OR recipecode = :input6 ")
    List<DbClasses.RecipeBasic> search_RecipeName1(String input1, String input2, String input3, String input4, String input5, String input6);

    @Query("SELECT recipename FROM RecipeBasic")
    String search_RecipeName();

    @Query("SELECT recipecode FROM RecipeBasic")
    String search_RecipeCode();

    @Query("SELECT typecategory FROM RecipeBasic")
    String search_typecategory();

    @Update
    void update(DbClasses.RecipeBasic recipeBasic);

}
