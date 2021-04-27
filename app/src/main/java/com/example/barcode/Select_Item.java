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
    @NonNull
    private String Category;
    @NonNull
    private int Date;

    public Select_Item(@NonNull String Name, @NonNull String ItemImg, @NonNull String Category, @NonNull int Date) {
        this.Name = Name;
        this.ItemImg = ItemImg;
        this.Category = Category;
        this.Date = Date;
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

    @NonNull
    public String getCategory() {
        return Category;
    }

    public void setCategory(@NonNull String category) {
        Category = category;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }
}

