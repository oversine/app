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
import java.util.List;
import java.util.Locale;

public class barcode_res extends AppCompatActivity {

    Button save, addProduct;
    TextView textView1, textView6, tv;
    private Product_Database pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_res);

        pd = Product_Database.getInstance(this);


        Intent intent = getIntent();
        String data = intent.getStringExtra("바코드값");

        String result_pp = DatabaseBuilder.Barcode_DB.DaoBC().search_barcode_ProductName(data); //바코드번호
        String result_pd = DatabaseBuilder.Barcode_DB.DaoBC().search_barcode(data); //상품명

        String result = "상품명:\t"+result_pd +"\n\r"+ "카테고리:\t"+result_pp;

        if(result == null){
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
            else {
                textView1 = findViewById(R.id.textView2);
                textView6 = findViewById(R.id.textView6);
                textView1.setText(result_pd);
                if(result_pp.equals("8809570010141")){
                    textView6.setText("닭가슴살");
                }
                else if(result_pp.equals("8801052053042")){
                    textView6.setText("소시지");
                }
                else {
                    textView6.setText(result_pp);
                }
        }

            // 날짜를 출력하는 텍스트뷰에 오늘 날짜 설정.
            tv = findViewById(R.id.date);
             Calendar cal = Calendar.getInstance();
             tv.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));


            save = findViewById(R.id.save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "데이터 추가 성공", Toast.LENGTH_SHORT).show();
                    pd.daoSave().insert(new SavePd(textView1.getText().toString(), textView6.getText().toString(), tv.getText().toString()));
                    finish();
                }
            });

            addProduct = findViewById(R.id.addProduct);
            addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "데이터 추가 성공", Toast.LENGTH_SHORT).show();
                pd.daoSave().insert(new SavePd(textView1.getText().toString(), textView6.getText().toString(), tv.getText().toString()));
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
                        tv.setText(String.format("%d-%d-%d", yy,mm+1,dd));
                    }
                };

        public void mOnClick_DatePick(View view){
            // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
            Calendar cal = Calendar.getInstance();
            new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
        }
    }