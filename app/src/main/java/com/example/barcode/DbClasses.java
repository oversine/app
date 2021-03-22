package com.example.barcode;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class DbClasses {

    @Entity
    class Barcode{
        @PrimaryKey
        private String barcode;
        private String productname;

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

        @Override
        public String toString() {
            return "Barcode{" +
                    "barcode='" + barcode + '\'' +
                    ", product_name='" + productname + '\'' +
                    ", product_property='" + barcode + '\'' +
                    '}';
        }
    }
    @Entity
    class IngredientName{

        @PrimaryKey
        private String materialname;
        private String expirationdata, classfication;

        public void setClassfication(@NonNull String classfication) {
            this.classfication = classfication;
        }

        @Override
        public String toString() {
            return "IngredientName{" +
                    "materialname='" + materialname + '\'' +
                    ", expirationdata='" + expirationdata + '\'' +
                    ", classfication='" + classfication + '\'' +
                    '}';
        }

        public void setExpirationdata(@NonNull String expirationdata) {
            this.expirationdata = expirationdata;
        }

        public void setMaterialname(@NonNull String materialname) {
            this.materialname = materialname;
        }

        @NonNull
        public String getClassfication() {
            return classfication;
        }

        @NonNull
        public String getExpirationdata() {
            return expirationdata;
        }

        @NonNull
        public String getMaterialname() {
            return materialname;
        }
    }
    @Entity
    class RecipeBasic{
        @PrimaryKey
        private String recipecode, recipename, simpleinfo, typecategory, foodcategory, cookingtime, imageurl ;

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
    @Entity
    class RecipeMaterial{
        @PrimaryKey
        private String recipecode, meterialname, meterialcapacity;
        private String meterialtype;

        @Override
        public String toString() {
            return "RecipeMaterial{" +
                    "recipecode='" + recipecode + '\'' +
                    ", meterialname='" + meterialname + '\'' +
                    ", meterialcapacity='" + meterialcapacity + '\'' +
                    ", meterialtype='" + meterialtype + '\'' +
                    '}';
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
    @Entity
    class RecipeProcess{
        @PrimaryKey
        private String recipecode, outputsequence;
        private String cookingprocess, processimageurl;

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