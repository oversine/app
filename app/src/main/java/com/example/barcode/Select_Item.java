package com.example.barcode;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Select_Item {
    @PrimaryKey
    @NonNull
    private String Name;
    @NonNull
    private String ItemImg;

    public Select_Item(@NonNull String Name, @NonNull String ItemImg) {
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

