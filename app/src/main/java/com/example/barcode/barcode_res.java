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

import java.util.Calendar;

public class barcode_res extends AppCompatActivity {

    Button save;
    Button addProduct;
    TextView textView1, textView6, tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//////
        Barcode_Database db = Room.databaseBuilder(this, Barcode_Database.class, "Barcode-1.2.1.db").allowMainThreadQueries().addMigrations().build();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_res);

        if(db.daoBarcode().exists_check("8809570010141")){}
        else {
            db.daoBarcode().Insert("8809570010141", "굽네 훈데 닭가슴살 오리지널", "닭가슴살"); //임의로 넣은 값 (테스트용
            db.daoBarcode().Insert("8801052053042", "청정원 리치부어스트", "소시지"); //임의로 넣은 값 (테스트용
            db.daoBarcode().Insert("8801019312021", "에이스", "맛있다!"); //임의로 넣은 값 (테스트용
        }
///////
        Intent intent = getIntent();
        String data = intent.getStringExtra("바코드값");

        String result_pp = db.daoBarcode().search_barcode_ProductName(data);
        String result_pd = db.daoBarcode().search_barcode(data);

        String result = "상품명:\t"+result_pd +"\n\r"+ "카테고리:\t"+result_pp;

        if(result == null){
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
            else {
                textView1 = findViewById(R.id.textView2);
                textView1.setText(result_pd);
                textView6 = findViewById(R.id.textView6);
                textView6.setText(result_pp);
        }

            // 날짜를 출력하는 텍스트뷰에 오늘 날짜 설정.
            tv = findViewById(R.id.date);
            Calendar cal = Calendar.getInstance();
            tv.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));


            save = findViewById(R.id.save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Database db = new Database(barcode_res.this);
                    db.addPN(textView1.getText().toString().trim(), textView6.getText().toString().trim(), tv.getText().toString().trim());
                    finish();
                }
            });

            addProduct = findViewById(R.id.addProduct);
            addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(barcode_res.this);
                db.addPN(textView1.getText().toString().trim(), textView6.getText().toString().trim(), tv.getText().toString().trim());
                Intent intent = new Intent(v.getContext(), scan.class);
                startActivity(intent);
            }
        });

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