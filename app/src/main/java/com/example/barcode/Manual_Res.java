package com.example.barcode;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Manual_Res extends AppCompatActivity {

    private Product_Database pd;
    public static Manual_Res manual_res;


    String itemImg;
    Button save, addProduct;
    TextView tv;
    EditText itemText;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_res);

        manual_res = Manual_Res.this;
        pd = Product_Database.getInstance(this);

        ViewPager pager = findViewById(R.id.viewPager);
        PagerAdapter adapter = new PageAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);


        Intent intent = getIntent();
        String code = intent.getStringExtra("식품명");
        itemImg = intent.getStringExtra("주소");
        int datePuls = intent.getIntExtra("유통기한", 0);

        itemText = findViewById(R.id.select_result);
        itemText.setText(code);

        // 날짜를 출력하는 텍스트뷰에 오늘 날짜 설정.
        tv = findViewById(R.id.date);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, datePuls);
        tv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));


        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemText.getText().toString().replace(" ", "").equals("")) {
                    Toast.makeText(v.getContext(), "식재료를 선택하거나 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "데이터 추가 성공", Toast.LENGTH_SHORT).show();
                    String nextDate = tv.getText().toString();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
                    try {
                        date = simpleDateFormat.parse(nextDate); // String 날짜 Date로 변환하여 DB 저장
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (itemImg == null) {
                        pd.daoSave().insert(new SavePd(itemText.getText().toString(), tv.getText().toString(), "https://1.bp.blogspot.com/-LlZp3x13e4A/YHWGwb6TeVI/AAAAAAAAAwQ/NBOAFFDDe7EkqwMXOTlfx91m934q_aWGwCLcBGAsYHQ/s0/icons_buy.png", date));
                    } else {
                        pd.daoSave().insert(new SavePd(itemText.getText().toString(), tv.getText().toString(), itemImg, date));
                    }
                    finish();
                }
            }
        });

        addProduct = findViewById(R.id.addProduct);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemText.getText().toString().replace(" ", "").equals("")) {
                    Toast.makeText(v.getContext(), "식재료를 선택하거나 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "데이터 추가 성공", Toast.LENGTH_SHORT).show();
                    String nextDate = tv.getText().toString();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
                    try {
                        date = simpleDateFormat.parse(nextDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (itemImg == null) {
                        pd.daoSave().insert(new SavePd(itemText.getText().toString(), tv.getText().toString(), "https://1.bp.blogspot.com/-LlZp3x13e4A/YHWGwb6TeVI/AAAAAAAAAwQ/NBOAFFDDe7EkqwMXOTlfx91m934q_aWGwCLcBGAsYHQ/s0/icons_buy.png", date));
                        Intent intent = new Intent(v.getContext(), Manual_Res.class);
                        startActivity(intent);
                    } else {
                        pd.daoSave().insert(new SavePd(itemText.getText().toString(), tv.getText().toString(), itemImg, date));
                        Intent intent = new Intent(v.getContext(), Manual_Res.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }
        });
    }

    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    // Date Picker에서 선택한 날짜를 TextView에 설정
                    TextView tv = findViewById(R.id.date);
                    tv.setText(String.format(Locale.getDefault(), "%d-%d-%d", yy, mm + 1, dd));
                }
            };

    public void mOnClick_DatePick(View view) {
        // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }
}
