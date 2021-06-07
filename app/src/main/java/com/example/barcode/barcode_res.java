package com.example.barcode;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class barcode_res extends AppCompatActivity {

    Button save, addProduct;
    TextView textView1, tv;
    Date date;
    private Product_Database pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_res);

        pd = Product_Database.getInstance(this);


        Intent intent = getIntent();
        String data = intent.getStringExtra("바코드값");
        String result_pd = DatabaseBuilder.Barcode_DB.DaoBC().search_barcode(data); //상품명

        String result = "상품명:\t"+result_pd +"\n\r";

        if(result_pd == null){
            Toast.makeText(this, "다시 인식해주세요.", Toast.LENGTH_LONG).show();
            Intent restart = new Intent(this, scan.class);
            startActivity(restart);
            finish();
        }
            else {
                textView1 = findViewById(R.id.textView2);
                textView1.setText(result_pd);
        }

            // 날짜를 출력하는 텍스트뷰에 오늘 날짜 설정.
            tv = findViewById(R.id.date);
             Calendar cal = Calendar.getInstance();
             int year = cal.get(Calendar.YEAR);
             int month = cal.get(Calendar.MONTH) + 1;
             int day = cal.get(Calendar.DATE);
              tv.setText(String.format("%02d-%02d-%02d", year,month,day));

            save = findViewById(R.id.save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "상품을 저장했습니다.", Toast.LENGTH_SHORT).show();
                    String nextDate = tv.getText().toString();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
                    try {
                        date = simpleDateFormat.parse(nextDate); // String 날짜 Date로 변환하여 DB 저장
                    }catch (Exception e){e.printStackTrace();}
                    nextDate = nextDate.substring(nextDate.indexOf("-")+1);
                    pd.daoSave().insert(new SavePd(textView1.getText().toString(), tv.getText().toString(), "https://1.bp.blogspot.com/-LlZp3x13e4A/YHWGwb6TeVI/AAAAAAAAAwQ/NBOAFFDDe7EkqwMXOTlfx91m934q_aWGwCLcBGAsYHQ/s0/icons_buy.png", date, nextDate));
                    finish();
                }
            });

            addProduct = findViewById(R.id.addProduct);
            addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "상품을 저장했습니다.", Toast.LENGTH_SHORT).show();
                String nextDate = tv.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
                try {
                    date = simpleDateFormat.parse(nextDate);
                }catch (Exception e){e.printStackTrace();}
                nextDate = nextDate.substring(nextDate.indexOf("-")+1);
                pd.daoSave().insert(new SavePd(textView1.getText().toString(), tv.getText().toString(), "https://1.bp.blogspot.com/-LlZp3x13e4A/YHWGwb6TeVI/AAAAAAAAAwQ/NBOAFFDDe7EkqwMXOTlfx91m934q_aWGwCLcBGAsYHQ/s0/icons_buy.png", date, nextDate));
                Intent intent = new Intent(v.getContext(), scan.class);
                startActivity(intent);
                finish();
            }
        });

    }
        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                        // Date Picker에서 선택한 날짜를 TextView에 설정
                        TextView tv = findViewById(R.id.date);
                        tv.setText(String.format(Locale.getDefault(),"%d-%02d-%02d", yy,mm+1,dd));
                    }
                };

        public void mOnClick_DatePick(View view){
            // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
            Calendar cal = Calendar.getInstance();
            new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
        }
    }