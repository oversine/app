package com.example.barcode;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class SavePd {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String PdName; // 상품명
    private String PdDate; // 유통기한 날짜
    private String PdImg; // 식재료 아이콘 이미지 주소
    private Date MainDate; // 유통기한 날짜 Date 타입
    private String PdDay; // 메인화면 임박 식재료 년도 제외 날자 표기


    public SavePd(String PdName, String PdDate, String PdImg, Date MainDate, String PdDay) {
        this.PdName = PdName;
        this.PdDate = PdDate;
        this.PdImg = PdImg;
        this.MainDate = MainDate;
        this.PdDay = PdDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPdName() {
        return PdName;
    }

    public void setPdName(String pdName) {
        PdName = pdName;
    }

    public String getPdDate() {
        return PdDate;
    }

    public void setPdDate(String pdDate) {
        PdDate = pdDate;
    }

    public String getPdImg() {
        return PdImg;
    }

    public void setPdImg(String pdImg) {
        PdImg = pdImg;
    }

    public Date getMainDate() {
        return MainDate;
    }

    public void setMainDate(Date mainDate) {
        MainDate = mainDate;
    }

    public String getPdDay() {
        return PdDay;
    }

    public void setPdDay(String pdDay) {
        PdDay = pdDay;
    }
}

