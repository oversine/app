package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DaoBarcode {

    /*
    @Query("SELECT * FROM barcode")//전체검색
    List<Barcode> getAll();

    @Query("SELECT productname FROM Barcode WHERE barcode = :input")//바코드로 상품명 검색
    String search_barcode(String input);
    @Query("SELECT Barcode FROM Barcode WHERE barcode = :input")//바코드로 상품명 검색
    String search_barcode_ProductName(String input);
    @Query("SELECT EXISTS (SELECT * FROM Barcode WHERE barcode =:input)AS SUCCESS") //입력값과 같은 컬럼 찾기
    boolean exists_check(String input);
    */


    @Query("INSERT INTO Barcode(barcode, productname) VALUES(:barcode, :productname)") //바코드 insert
    void Insert(String barcode, String productname);

    @Query("INSERT INTO RecipeBasic VALUES(:rpcode, :rpname, :info, :type, :food, :time, :url)") // 레시피 기본 insert
    void InsertRB(String rpcode, String rpname, String info, String type, String food, String time, String url);

    @Query("INSERT INTO RecipeMaterial VALUES(:rpcode, :mname, :mcap, :mtype)") // 레시피 재료 insert
    void InsertRM(String rpcode, String mname, String mcap, String mtype);

    @Query("INSERT INTO RecipeProcess VALUES(:rpcode, :outputseq, :cookingpro, :prourl)") //레시피 과정 insert
    void InsertRP(String rpcode, String outputseq, String cookingpro, String prourl);

    // @Delete
    // void nukeTable(Barcode... barcode);//튜플삭제

}
