package com.example.barcode;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Select_Item {
    @PrimaryKey
    private String Name; // 상품명

    private String ItemImg; // 유통기한 날짜

    public Select_Item(String Name, String ItemImg) {
        this.Name = Name;
        this.ItemImg = ItemImg;
    }

    @NonNull
    public String getName() {
        return Name;
    }

    public void setName(@NonNull String name) {
        Name = name;
    }

    @NonNull
    public String getItemImg() {
        return ItemImg;
    }

    public void setItemImg(@NonNull String itemImg) {
        ItemImg = itemImg;
    }
}

