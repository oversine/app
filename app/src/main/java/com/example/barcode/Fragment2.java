package com.example.barcode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Fragment2 extends Fragment {

    private RecyclerView recyclerView;
    private MainAdapter mAdapter;
    private RecipeAdapter rAdapter;
    private List<SavePd> saveDate;
    private List<DbClasses.RecipeBasic> RcTable = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private Date listDate;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        PreferenceManager.setDefaultValues(getActivity(), R.xml.settings_preference, false);

        String[] res;
        Object[] arg = new Object[]{"버섯"};
        try {
            Object[] temp;
            temp = Product_Database.getInstance(getActivity()).daoSave().getPdName().toArray();
            if(temp.length > 0){
                arg = temp;
                System.out.println(temp[0]+"-test");
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(arg[0]);
        res = MainActivity.compute_sim(arg, getActivity());


        Button scanBtn = view.findViewById(R.id.barcode_scan);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), scan.class);
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView_main);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainAdapter();
        recyclerView.setAdapter(mAdapter);


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String result = pref.getString("userData_list", "");
        int userDate = Integer.parseInt(result); // 사용자 설정값 int 변환

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        cal.add(Calendar.DATE, userDate); // 현재 날짜 및 시간 + 설정값
        String datePlus = dateFormat.format(cal.getTime());
        try {
        listDate = dateFormat.parse(datePlus); // 더한 결과값 String 타입 -> Date 변환
        }catch (Exception e){e.printStackTrace();}

        saveDate = Product_Database.getInstance(getActivity()).daoSave().getDate(listDate);
        int size = saveDate.size();
        for(int i = 0; i < size; i++){
            mAdapter.addData(saveDate.get(i));
        }

        recyclerView = view.findViewById(R.id.recyclerView_main2);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rAdapter = new RecipeAdapter(RcTable);
        recyclerView.setAdapter(rAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));

        RcTable = DatabaseBuilder.RecipeB_DB.DaoRB().search_recommend(res[0], res[1], res[2], res[3], res[4], res[5], res[6], res[7], res[8], res[9]);
        int size1 = RcTable.size();
        for(int i = 0; i < size1; i++){
            rAdapter.addRecipe(RcTable.get(i));
        }
        return view;
    }
}
