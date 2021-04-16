package com.example.barcode;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Manual_Res extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    private Product_Database pd;

    String itemImg, itemName;
    Button save, addProduct;
    TextView tv;
    EditText itemText;
    Date date;
    List<Select_Item> select_Item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_res);

        pd = Product_Database.getInstance(this);


        recyclerView = findViewById(R.id.recyclerView_ingredients);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);

        recyclerView.setLayoutManager(gridLayoutManager);
        iAdapter = new ItemAdapter();
        recyclerView.setAdapter(iAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));

        select_Item = Select_Item_Database.getInstance(this).daoItem().getAll();
        int size = select_Item.size();
        for(int i = 0; i < size; i++){
            iAdapter.addItem(select_Item.get(i));
        }

        iAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View V, int position) {
                itemName = select_Item.get(position).getName();
                itemText.setText(itemName);
                itemImg = select_Item.get(position).getItemImg();
            }
        });

        itemText = findViewById(R.id.select_result);
        itemText.setText(itemName);

        // 날짜를 출력하는 텍스트뷰에 오늘 날짜 설정.
        tv = findViewById(R.id.date);
        Calendar cal = Calendar.getInstance();
        tv.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));


        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "데이터 추가 성공", Toast.LENGTH_SHORT).show();
                String nextDate = tv.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
                try {
                    date = simpleDateFormat.parse(nextDate); // String 날짜 Date로 변환하여 DB 저장
                }catch (Exception e){e.printStackTrace();}
                if(itemImg == null) {
                    pd.daoSave().insert(new SavePd(itemText.getText().toString(), tv.getText().toString(), "https://1.bp.blogspot.com/-LlZp3x13e4A/YHWGwb6TeVI/AAAAAAAAAwQ/NBOAFFDDe7EkqwMXOTlfx91m934q_aWGwCLcBGAsYHQ/s0/icons_buy.png", date));
                } else {
                    pd.daoSave().insert(new SavePd(itemText.getText().toString(), tv.getText().toString(), itemImg, date));
                }
                finish();
            }
        });

        addProduct = findViewById(R.id.addProduct);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "데이터 추가 성공", Toast.LENGTH_SHORT).show();
                String nextDate = tv.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
                try {
                    date = simpleDateFormat.parse(nextDate);
                }catch (Exception e){e.printStackTrace();}
                if(itemImg == null) {
                    pd.daoSave().insert(new SavePd(itemText.getText().toString(), tv.getText().toString(), "https://1.bp.blogspot.com/-LlZp3x13e4A/YHWGwb6TeVI/AAAAAAAAAwQ/NBOAFFDDe7EkqwMXOTlfx91m934q_aWGwCLcBGAsYHQ/s0/icons_buy.png", date));
                    Intent intent = new Intent(v.getContext(), scan.class);
                    startActivity(intent);
                } else {
                    pd.daoSave().insert(new SavePd(itemText.getText().toString(), tv.getText().toString(), itemImg, date));
                    Intent intent = new Intent(v.getContext(), scan.class);
                    startActivity(intent);
                }
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
                    tv.setText(String.format(Locale.getDefault(),"%d-%d-%d", yy,mm+1,dd));
                }
            };

    public void mOnClick_DatePick(View view){
        // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }
}
