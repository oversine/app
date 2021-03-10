package com.example.barcode;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Calendar;

public class barcode_res extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Barcode_Database db = Room.databaseBuilder(this, Barcode_Database.class, "Barcode-1.2.1.db").allowMainThreadQueries().build(); //sqlite 직접입력
        //Barcode_Database db = Room.databaseBuilder(this, Barcode_Database.class,"Barcode-1.2.db" ).createFromAsset("Barcode.db").build(); // sqlite csv import


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_res);

        db.daoBarcode().Insert("9791162242971","test1", "test"); //임의로 넣은 값 (테스트용
        db.daoBarcode().Insert("9791156642428","test2", "test"); //임의로 넣은 값 (테스트용
        Intent intent = getIntent();
        String data = intent.getStringExtra("바코드값");

        String result_pp = db.daoBarcode().search_barcode_ProductName(data);
        String result_pd = db.daoBarcode().search_barcode(data);

        String result = "상품명:\t"+result_pd +"\n\r"+ "카테고리:\t"+result_pp;

        if(result == null){
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
            else {
                TextView textView1 = (TextView) findViewById(R.id.textView2) ;
                textView1.setText(result);
        }

            // 날짜를 출력하는 텍스트뷰에 오늘 날짜 설정.
            TextView tv = findViewById(R.id.date);
            Calendar cal = Calendar.getInstance();
            tv.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));
        }

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                        // Date Picker에서 선택한 날짜를 TextView에 설정
                        TextView tv = findViewById(R.id.date);
                        tv.setText(String.format("%d-%d-%d", yy,mm+1,dd));
                    }
                };

        public void mOnClick_DatePick(View view){
            // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
            Calendar cal = Calendar.getInstance();
            new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
        }
    }