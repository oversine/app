package com.example.barcode;

import android.provider.BaseColumns;

import java.io.Serializable;

public class TableList {

    public String ProductName;
    public String ProductCategory;
    public String ProductData;

    public TableList(String ProductName, String ProductCategory, String ProductData){
        this.ProductName = ProductName;
        this.ProductCategory = ProductCategory;
        this.ProductData = ProductData;
    }
    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        this.ProductCategory = productCategory;
    }

    public String getProductData() {
        return ProductData;
    }

    public void setProductData(String productData) {
        this.ProductData = productData;
    }
}
