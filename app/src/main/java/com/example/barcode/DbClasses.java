package com.example.barcode;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//db클래스(db관련 클래스를 한곳에 모아두기 위해 내부 클래스 사용)
public class DbClasses {
    //barcode_db 클래스
    @Entity(tableName = "Barcode")
    public class Barcode{
        //속성 정의
        @PrimaryKey
        @NonNull
        public String barcode;
        public String productname;
        //getter, setter
        public String getProductname() { return productname; }

        public void setProductname(String productname) { this.productname = productname; }

        public String getBarcode() {
            return barcode;
        }

        public String getProductName() {
            return productname;
        }

        public void setBarcode(@NonNull String barcode) {
            this.barcode = barcode;
        }

        public void setProductName(String productName) {
            productname = productName;
        }
        //string 메소드 override
        @Override
        public String toString() {
            return "Barcode{" +
                    "barcode='" + barcode + '\'' +
                    ", product_name='" + productname + '\'' +
                    ", product_property='" + barcode + '\'' +
                    '}';
        }
    }

    @Entity(tableName = "RecipeBasic")
    public static class RecipeBasic{
        @PrimaryKey
        @NonNull
        public String recipecode;
        @NonNull
        public String recipename, simpleinfo, typecategory, foodcategory, cookingtime, imageurl ;

        boolean isSelected;

        public boolean getSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        @NonNull
        public String getRecipecode() {
            return recipecode;
        }

        @NonNull
        public String getRecipename() {
            return recipename;
        }

        @NonNull
        public String getSimpleinfo() {
            return simpleinfo;
        }

        @NonNull
        public String getTypecategory() {
            return typecategory;
        }

        @NonNull
        public String getFoodcategory() {
            return foodcategory;
        }

        @NonNull
        public String getCookingtime() {
            return cookingtime;
        }

        @NonNull
        public String getImageurl() {
            return imageurl;
        }

        public void setRecipecode(@NonNull String recipecode) {
            this.recipecode = recipecode;
        }

        public void setRecipename(@NonNull String recipename) {
            this.recipename = recipename;
        }

        public void setSimpleinfo(@NonNull String simpleinfo) {
            this.simpleinfo = simpleinfo;
        }

        public void setTypecategory(@NonNull String typecategory) {
            this.typecategory = typecategory;
        }

        public void setFoodcategory(@NonNull String foodcategory) {
            this.foodcategory = foodcategory;
        }

        public void setCookingtime(@NonNull String cookingtime) {
            this.cookingtime = cookingtime;
        }

        public void setImageurl(@NonNull String imageurl) {
            this.imageurl = imageurl;
        }

        @Override
        public String toString() {
            return "RecipeBasic{" +
                    "recipecode='" + recipecode + '\'' +
                    ", recipename='" + recipename + '\'' +
                    ", simpleinfo='" + simpleinfo + '\'' +
                    ", typecategory='" + typecategory + '\'' +
                    ", foodcategory='" + foodcategory + '\'' +
                    ", cookingtime='" + cookingtime + '\'' +
                    ", imageurl='" + imageurl + '\'' +
                    '}';
        }
    }

    @Entity(tableName = "RecipeMaterial", primaryKeys = {"recipecode", "meterialname", "meterialcapacity"})
    public static class RecipeMaterial{
        @NonNull
        public String recipecode, meterialname, meterialcapacity;
        public String meterialtype;

        @Override
        public String toString() {
            return String.format("재료 : " + meterialname + "\r\r\r" + "양 : " + meterialcapacity + "\n");

        }

        public void setRecipecode(@NonNull String recipecode) {
            this.recipecode = recipecode;
        }

        public void setMeterialname(@NonNull String meterialname) {
            this.meterialname = meterialname;
        }

        public void setMeterialcapacity(@NonNull String meterialcapacity) {
            this.meterialcapacity = meterialcapacity;
        }

        public void setMeterialtype(@NonNull String meterialtype) {
            this.meterialtype = meterialtype;
        }

        @NonNull
        public String getRecipecode() {
            return recipecode;
        }

        @NonNull
        public String getMeterialname() {
            return meterialname;
        }

        @NonNull
        public String getMeterialcapacity() {
            return meterialcapacity;
        }

        @NonNull
        public String getMeterialtype() {
            return meterialtype;
        }
    }

    @Entity(tableName = "RecipeProcess", primaryKeys  = {"recipecode", "outputsequence", "cookingprocess"})
    public static class RecipeProcess{
        @NonNull
        public String recipecode, outputsequence, cookingprocess;
        public String processimageurl;

        @Override
        public String toString() {
            return "RecipeProcess{" +
                    "recipecode='" + recipecode + '\'' +
                    ", outputsequence='" + outputsequence + '\'' +
                    ", cookingprocess='" + cookingprocess + '\'' +
                    ", processimageurl='" + processimageurl + '\'' +
                    '}';
        }

        @NonNull
        public String getRecipecode() {
            return recipecode;
        }

        public void setRecipecode(@NonNull String recipecode) {
            this.recipecode = recipecode;
        }

        @NonNull
        public String getOutputsequence() {
            return outputsequence;
        }

        public void setOutputsequence(@NonNull String outputsequence) {
            this.outputsequence = outputsequence;
        }

        @NonNull
        public String getCookingprocess() {
            return cookingprocess;
        }

        public void setCookingprocess(@NonNull String cookingprocess) {
            this.cookingprocess = cookingprocess;
        }

        @NonNull
        public String getProcessimageurl() {
            return processimageurl;
        }

        public void setProcessimageurl(@NonNull String processimageurl) {
            this.processimageurl = processimageurl;
        }
    }
}
