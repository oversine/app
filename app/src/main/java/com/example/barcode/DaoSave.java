package com.example.barcode;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoSave {
    @Query("SELECT * FROM SavePd ORDER BY MainDate")
    List<SavePd> getAll();

    @Query("SELECT * FROM SavePd WHERE MainDate <= :input ORDER BY MainDate")
    List<SavePd> getDate(Date input); // 유통기한 값 낮은 별로 정렬하여 메인화면 유통기한 임박 식재료 리스트 출력

    @Query("SELECT MainDate FROM SavePd WHERE MainDate = :input")
    Date search_date(Date input);

    @Query("SELECT PdName FROM SavePD")
    List<String> getPdName();

    @Insert
    void insert(SavePd savePd);

    @Update
    void update(SavePd savePd);

    @Delete
    void delete(SavePd savePd);
}
