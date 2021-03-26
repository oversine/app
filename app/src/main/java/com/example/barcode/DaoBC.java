package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DaoBC {

    @Query("INSERT INTO Barcode(barcode, productname) VALUES(:barcode, :productname)") //바코드 insert
    void Insert(String barcode, String productname);
    @Query("SELECT productname FROM Barcode WHERE barcode = :input")//바코드로 상품명 검색
    String search_barcode(String input);
    @Query("SELECT Barcode FROM Barcode WHERE barcode = :input")//바코드로 상품명 검색
    String search_barcode_ProductName(String input);
    @Query("SELECT EXISTS (SELECT * FROM Barcode WHERE barcode =:input)AS SUCCESS") //입력값과 같은 컬럼 찾기
    boolean exists_check(String input);
}
