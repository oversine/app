package com.example.barcode;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SavePd {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String PdName;
    private String PdCategory;
    private String PdData;

    public SavePd(String PdName, String PdCategory, String PdData) {
        this.PdName = PdName;
        this.PdCategory = PdCategory;
        this.PdData = PdData;
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

    public String getPdCategory() {
        return PdCategory;
    }

    public void setPdCategory(String pdCategory) {
        PdCategory = pdCategory;
    }

    public String getPdData() {
        return PdData;
    }

    public void setPdData(String pdData) {
        PdData = pdData;
    }
}

