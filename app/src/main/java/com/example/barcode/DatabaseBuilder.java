package com.example.barcode;

import android.content.Context;

import androidx.room.Room;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseBuilder { // 데이터베이스 빌더

    //db객체 선언
    public static RoomDBClasses.Barcode_Database Barcode_DB;
    public static RoomDBClasses.RecipeBasic_Database RecipeB_DB;
    public static RoomDBClasses.RecipeMaterial_Database RecipeM_DB;
    public static RoomDBClasses.RecipeProcess_Database RecipeP_DB;

    //db초기화
    public DatabaseBuilder(Context context) {
        Barcode_DB = Room.databaseBuilder(context, RoomDBClasses.Barcode_Database.class, "Barcode_1.0.0.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createBCDb Success");
        RecipeB_DB = Room.databaseBuilder(context.getApplicationContext(), RoomDBClasses.RecipeBasic_Database.class, "RecipeBasic_1.0.0.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createRBDb Success");
        RecipeM_DB = Room.databaseBuilder(context, RoomDBClasses.RecipeMaterial_Database.class, "RecipeMaterial_1.0.0.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createRMDb Success");
        RecipeP_DB = Room.databaseBuilder(context, RoomDBClasses.RecipeProcess_Database.class, "RecipeProcess_1.0.0.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createRPDb Success");
    }

    //php에서 토큰 분리
    public ArrayList<String> getBCData(String input) {

        String JSON_TAG = "webnautes";
        String BARCODE_TAG = "barcode";
        String PRODUCTNAME_TAG = "productname";
        ArrayList<String> result = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(input);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_TAG);
            //json리스트에서 원하는 정보만 빼서 리스트에 저장
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                result.add(item.getString(BARCODE_TAG));
                result.add(item.getString(PRODUCTNAME_TAG));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Barcode tokenizing success");
        return result;
    }

    public ArrayList<String> getRMData(String input) {

        String JSON_TAG = "webnautes";
        String TAG_a = "recipecode";
        String TAG_b = "materialname";
        String TAG_c = "materialcapacity";
        String TAG_d = "materialtype";
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

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("RM tokenizing success");
        return result;
    }

    public ArrayList<String> getRBData(String input) {

        String JSON_TAG = "webnautes";
        String TAG_a ="recipecode";
        String TAG_b ="recipename";
        String TAG_c ="simpleinfo";
        String TAG_d ="typecategory";
        String TAG_e ="foodcategory";
        String TAG_f ="cookingtime";
        String TAG_g ="imageurl";
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

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("RB tokenizing success");
        return result;
    }

    public ArrayList<String> getRPData(String input) {

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
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("RP tokenizing success");
        return result;
    }
    //데이터베이스에 튜플 삽입
    public boolean addBCtuples(ArrayList<String> input, RoomDBClasses.Barcode_Database db) {
        String barcode ="";
        String pn ="";
        try {
            //리스트 수 만큼 반복하며 리스트 인덱스를 db속성값으로 나눈 나머지에 따라서 db에 삽입
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

    public boolean addRBtuples(ArrayList<String> input, RoomDBClasses.RecipeBasic_Database db) {
        String TAG_a ="";
        String TAG_b ="";
        String TAG_c ="";
        String TAG_d ="";
        String TAG_e ="";
        String TAG_f ="";
        String TAG_g ="";
        boolean TAG_h = false;
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
                    db.DaoRB().InsertRB(TAG_a, TAG_b, TAG_c, TAG_d, TAG_e, TAG_f, TAG_g, TAG_h);
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

    public boolean addRPtuples(ArrayList<String> input, RoomDBClasses.RecipeProcess_Database db) {
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

    public boolean addRMtuples(ArrayList<String> input, RoomDBClasses.RecipeMaterial_Database db) {
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
