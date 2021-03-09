package com.example.barcode;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Barcode {

    @PrimaryKey
    @NonNull
    private String barcode;
    @NonNull
    @ColumnInfo(name = "ProductName")
    private String ProductName;

    public String getBarcode() {
        return barcode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setBarcode(@NonNull String barcode) {
        this.barcode = barcode;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    @Override
    public String toString() {
        return "Barcode{" +
                "barcode='" + barcode + '\'' +
                ", product_name='" + ProductName + '\'' +
                ", product_property='" + barcode + '\'' +
                '}';
    }
}

