package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class temp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        TextView textview;
        textview = findViewById(R.id.product_output); //textview

        Barcode_Database db;
        //db = Room.databaseBuilder(this, Barcode_Database.class, "Barcode-1.db").allowMainThreadQueries().build();// 기존 데이터베이스 빌더
        db = Room.databaseBuilder(this, Barcode_Database.class, "Barcode_1.0.db").createFromAsset("Barcode.db").build(); // 테스트용 Room AssetFromt사용
        //db = Room.databaseBuilder(this, Barcode_Database.class, "temp.db").createFromFile(new File("Barcode.db")).build(); // 테스트용 Room사용 Asset File사용


        findViewById(R.id.testbutton).setOnClickListener(view ->{
            String meaasge = db.daoBarcode().search_barcode_ProductName("카스맥주355ML").toString();
            textview.setText(meaasge);
        });
    }
}