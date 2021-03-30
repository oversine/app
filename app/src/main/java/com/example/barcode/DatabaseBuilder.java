package com.example.barcode;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseBuilder { // 데이터베이스 빌더

    //public static Barcode_Database db;
    public static RoomDBClasses.Barcode_Database Barcode_DB;
    public static RoomDBClasses.RecipeBasic_Database RecipeB_DB;
    public static RoomDBClasses.RecipeMaterial_Database RecipeM_DB;
    public static RoomDBClasses.RecipeProcess_Database RecipeP_DB;

    public DatabaseBuilder(Context context) {
        Barcode_DB = Room.databaseBuilder(context, RoomDBClasses.Barcode_Database.class, "Barcode_1.0.0.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createBCDb Success");
        RecipeB_DB = Room.databaseBuilder(context, RoomDBClasses.RecipeBasic_Database.class, "RecipeBasic_1.0.0.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createRBDb Success");
        RecipeM_DB = Room.databaseBuilder(context, RoomDBClasses.RecipeMaterial_Database.class, "RecipeBasic_1.0.0.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createRMDb Success");
        RecipeP_DB = Room.databaseBuilder(context, RoomDBClasses.RecipeProcess_Database.class, "RecipeBasic_1.0.0.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createRPDb Success");
    }


    public ArrayList<String> getBCData(String input) { //php에서 토큰 분리

        String JSON_TAG = "webnautes";
        String BARCODE_TAG = "barcode";
        String PRODUCTNAME_TAG = "productname";
        ArrayList<String> result = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(input);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_TAG);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                result.add(item.getString(BARCODE_TAG));
                //System.out.println(item.getString(BARCODE_TAG)); //test
                result.add(item.getString(PRODUCTNAME_TAG));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Barcode tokenizing success");
        return result;
    }

    public ArrayList<String> getRMData(String input) { //php에서 토큰 분리

        String JSON_TAG = "webnautes";
        String TAG_a = "recipecode";
        String TAG_b = "meterialname";
        String TAG_c = "meterialcapacity";
        String TAG_d = "meterialtype";
        ArrayList<String> result = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(input);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_TAG);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                result.add(item.getString(TAG_a));
                result.add(item.getString(TAG_b));
                result.add(item.getString(TAG_c));
                result.add(item.getString(TAG_d));
                //System.out.println(item.getString(TAG_a)); //test
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("RM tokenizing success");
        return result;
    }

    public ArrayList<String> getRBData(String input) { //php에서 토큰 분리

        String JSON_TAG = "webnautes";
        String TAG_a ="recipecode";
        String TAG_b ="recupename";
        String TAG_c ="simpleinfo";
        String TAG_d ="typecategory";
        String TAG_e ="foodcategory";
        String TAG_f ="cookingtime";
        String TAG_g ="inageurl";
        ArrayList<String> result = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(input);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_TAG);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                result.add(item.getString(TAG_a));
                result.add(item.getString(TAG_b));
                result.add(item.getString(TAG_c));
                result.add(item.getString(TAG_d));
                result.add(item.getString(TAG_e));
                result.add(item.getString(TAG_f));
                result.add(item.getString(TAG_g));
                //System.out.println(item.getString(TAG_a)); //test
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("RB tokenizing success");
        return result;
    }

    public ArrayList<String> getRPData(String input) { //php에서 토큰 분리

        String JSON_TAG = "webnautes";
        String TAG_a ="recipecode";
        String TAG_b ="outputsequence";
        String TAG_c ="cookingprocess";
        String TAG_d ="processimageurl";

        ArrayList<String> result = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(input);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_TAG);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                result.add(item.getString(TAG_a));
                result.add(item.getString(TAG_b));
                result.add(item.getString(TAG_c));
                result.add(item.getString(TAG_d));
                //System.out.println(item.getString(TAG_a)); //test
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("RP tokenizing success");
        return result;
    }

    public boolean addBCtuples(ArrayList<String> input, RoomDBClasses.Barcode_Database db) { //데이터베이스에 튜플 삽입
        String barcode ="";
        String pn ="";
        try {
            for (int i = 0; i < input.size(); i++) {
                if (i % 2 == 0) {
                    barcode = (String) input.get(i);
                } else {
                    pn = (String) input.get(i);
                }
                if (barcode != "" && pn != "") {
                    db.DaoBC().Insert(barcode, pn);
                    barcode = ""; pn ="";
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("adding BC tuples success");
        return true;
    }

    public boolean addRBtuples(ArrayList<String> input, RoomDBClasses.RecipeBasic_Database db) { //데이터베이스에 튜플 삽입
        String TAG_a ="";
        String TAG_b ="";
        String TAG_c ="";
        String TAG_d ="";
        String TAG_e ="";
        String TAG_f ="";
        String TAG_g ="";
        try {
            for (int i = 0; i < input.size(); i++) {

                if(i%7 == 0){
                    TAG_a = (String) input.get(i);
                }
                else if (i%7 == 1){
                    TAG_b = (String) input.get(i);
                }
                else if(i%7 == 2){
                    TAG_c = (String) input.get(i);
                }
                else if(i%7 == 3){
                    TAG_d = (String) input.get(i);
                }
                else if(i%7 == 4){
                    TAG_e = (String) input.get(i);
                }
                else if(i%7 == 5){
                    TAG_f = (String) input.get(i);
                }
                else{
                    TAG_g = (String) input.get(i);
                }

                if (TAG_a !="" && TAG_b !="" &&TAG_c !="" &&TAG_d !="" &&TAG_e !="" &&TAG_f !="" && TAG_g !="") {
                    db.DaoRB().InsertRB(TAG_a, TAG_b, TAG_c, TAG_d, TAG_e, TAG_f, TAG_g);
                    TAG_a ="";
                    TAG_b ="";
                    TAG_c ="";
                    TAG_d ="";
                    TAG_e ="";
                    TAG_f ="";
                    TAG_g ="";
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("adding RB tuples success");
        return true;
    }

    public boolean addRPtuples(ArrayList<String> input, RoomDBClasses.RecipeProcess_Database db) { //데이터베이스에 튜플 삽입
        String TAG_a ="";
        String TAG_b ="";
        String TAG_c ="";
        String TAG_d ="";

        try {
            for (int i = 0; i < input.size(); i++) {

                if(i%4 == 0){
                    TAG_a = (String) input.get(i);
                }
                else if (i%4== 1){
                    TAG_b = (String) input.get(i);
                }
                else if(i%4 == 2){
                    TAG_c = (String) input.get(i);
                }
                else{
                    TAG_d = (String) input.get(i);
                }

                if (TAG_a !="" && TAG_b !="" &&TAG_c !="" &&TAG_d !="") {
                    db.DaoRP().InsertRP(TAG_a, TAG_b, TAG_c, TAG_d);
                    TAG_a ="";
                    TAG_b ="";
                    TAG_c ="";
                    TAG_d ="";
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("adding RP tuples success");
        return true;
    }

    public boolean addRMtuples(ArrayList<String> input, RoomDBClasses.RecipeMaterial_Database db) { //데이터베이스에 튜플 삽입
        String TAG_a ="";
        String TAG_b ="";
        String TAG_c ="";
        String TAG_d ="";

        try {
            for (int i = 0; i < input.size(); i++) {

                if(i%4 == 0){
                    TAG_a = (String) input.get(i);
                }
                else if (i%74== 1){
                    TAG_b = (String) input.get(i);
                }
                else if(i%4 == 2){
                    TAG_c = (String) input.get(i);
                }
                else{
                    TAG_d = (String) input.get(i);
                }

                if (TAG_a !="" && TAG_b !="" &&TAG_c !="" &&TAG_d !="") {
                    db.DaoRM().InsertRM(TAG_a, TAG_b, TAG_c, TAG_d);
                    TAG_a ="";
                    TAG_b ="";
                    TAG_c ="";
                    TAG_d ="";
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("adding RM tuples success");
        return true;
    }

}
