package com.example.barcode;

import android.content.Context;

import androidx.room.Room;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseBuilder { // 데이터베이스 빌더
    public static Barcode_Database db;
    public DatabaseBuilder(Context context) {
        db = Room.databaseBuilder(context, Barcode_Database.class, "Barcode_1.0.1.db").allowMainThreadQueries().addMigrations().build();
        System.out.println("createDb Success");
    }
    public Barcode_Database db_getter(){
        return db;
    }

    public ArrayList getData(String input) { //php에서 토큰 분리
        String JSON_TAG = "webnautes";
        String BARCODE_TAG = "barcode";
        String PRODUCTNAME_TAG = "productname";
        ArrayList result = new ArrayList();

        try {
            JSONObject jsonObject = new JSONObject(input);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_TAG);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                result.add(item.getString(BARCODE_TAG));
                System.out.println(item.getString(BARCODE_TAG)); //test
                result.add(item.getString(PRODUCTNAME_TAG));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("tokenizing success");
        return result;
    }

    public boolean addtuples(ArrayList input, Barcode_Database db) { //데이터베이스에 튜플 삽입
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
                    db.daoBarcode().Insert(barcode, pn);
                    barcode = ""; pn ="";
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("adding tuples success");
        return true;
    }
}
