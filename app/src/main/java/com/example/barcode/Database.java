package com.example.barcode;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "Ingredients.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Ingredients_list";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "ProductName";
    private static final String COLUMN_CATEGORY = "ProductCategory";
    private static final String COLUMN_DATA = "ProductData";


        public Database(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            db = getWritableDatabase();
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME
                    + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_CATEGORY + " TEXT, "
                    + COLUMN_DATA + " TEXT); ";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }


        void addPN(String name, String category, String data) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_NAME, name);
            cv.put(COLUMN_CATEGORY, category);
            cv.put(COLUMN_DATA, data);
            long result = db.insert(TABLE_NAME, null, cv);
            if (result == -1) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "데이터 추가 성공", Toast.LENGTH_SHORT).show();
            }
        }

        public ArrayList<TableList> selectAll() {
            String sql = "SELECT * FROM " + TABLE_NAME;

            ArrayList<TableList> list = new ArrayList<>();

            Cursor results = db.rawQuery(sql, null);
            results.moveToFirst();

            while (!results.isAfterLast()) {
                TableList tableList = new TableList(results.getString(1), results.getString(2), results.getString(3));
                list.add(tableList);
                results.moveToNext();
            }
            results.close();
            return list;
        }
    }
