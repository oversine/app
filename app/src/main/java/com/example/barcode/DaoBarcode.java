package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DaoBarcode {

    @Query("SELECT * FROM barcode")//전체검색
    List<Barcode> getAll();

    @Query("SELECT productname FROM Barcode WHERE barcode = :input")//바코드로 상품명 검색
    String search_barcode(String input);
    @Query("SELECT Barcode FROM Barcode WHERE barcode = :input")//바코드로 상품명 검색
    String search_barcode_ProductName(String input);

    @Query("SELECT EXISTS (SELECT * FROM Barcode WHERE barcode =:input)AS SUCCESS") //입력값과 같은 컬럼 찾기
    boolean exists_check(String input);

    @Query("INSERT INTO Barcode(barcode, productname) VALUES(:barcode_ipt, :productname_ipt)") //test
    void Insert(String barcode_ipt, String productname_ipt);
    @Query("INSERT INTO barcode(barcode, productname, Barcode) VALUES(:barcode_ipt, :productname_ipt, :property_ipt)")
    void Insert(String barcode_ipt, String productname_ipt, String property_ipt);

    // @Delete
    // void nukeTable(Barcode... barcode);//튜플삭제

}
