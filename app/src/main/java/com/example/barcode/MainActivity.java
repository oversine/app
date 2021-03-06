package com.example.barcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.openkoreantext.processor.KoreanPosJava;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

import scala.collection.Seq;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    DatabaseBuilder builder;
    SqlConnect sqlConnect;
    public static Context context;
    private Date mainDate, pushDate;

    File fileb; File filep; File fileba; File filem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;
        setTheme(R.style.Theme_Barcode);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // 앱 다크모드 off


        ////////////////////////////////////// 앱 시작시 php서버에 접속하여 db를 불러와 room에 저장
        String base_path = "/data/data/com.example.barcode/databases/";
        String bc = base_path + "Barcode_1.0.0.db";
        String rb = base_path + "RecipeBasic_1.0.0.db";
        String rm = base_path + "RecipeMaterial_1.0.0.db";
        String rp = base_path + "RecipeProcess_1.0.0.db";

        fileb = new File(bc); filep = new File(rp); fileba = new File(rb); filem = new File(rm);
        builder = new DatabaseBuilder(this);
        sqlConnect = new SqlConnect();

        if(!fileb.exists() || !filep.exists() || !fileba.exists() || !filem.exists()) {
            SqlConnect.Get_Barcode_php task = new SqlConnect.Get_Barcode_php();
            SqlConnect.Get_RecipeBasic_php rbtask = new SqlConnect.Get_RecipeBasic_php();
            SqlConnect.Get_RecipeMaterial_php rmtask = new SqlConnect.Get_RecipeMaterial_php();
            SqlConnect.Get_RecipeProcess_php rptask = new SqlConnect.Get_RecipeProcess_php();
            try {
                builder.addBCtuples(builder.getBCData(task.execute().get()), DatabaseBuilder.Barcode_DB);
                builder.addRBtuples(builder.getRBData(rbtask.execute().get()), DatabaseBuilder.RecipeB_DB);
                builder.addRMtuples(builder.getRMData(rmtask.execute().get()), DatabaseBuilder.RecipeM_DB);
                builder.addRPtuples(builder.getRPData(rptask.execute().get()), DatabaseBuilder.RecipeP_DB);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        MainActivity.init_python(this); //init_python
        mBottomNV = findViewById(R.id.navigation);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { // 하단 네비게이션 바 선택 시
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());
                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.home); // 네비게이션 바 기본 선택 값

        findViewById(R.id.menuSet).setOnClickListener(new View.OnClickListener() { // 북마크, 수동 등록, 설정 팝업메뉴
            @Override
            public void onClick(final View v) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.action_menu1){
                                Intent intent = new Intent(MainActivity.this, Manual_Res.class); // 네비게이션 바 팝업 메뉴 수동 등록
                                startActivity(intent);
                                return true;
                            }else if(item.getItemId() == R.id.action_menu2){
                                Intent intent1 = new Intent(MainActivity.this, Bookmarks.class); // 네비게이션 바 팝업 메뉴 북마크
                                startActivity(intent1);
                                return true;
                            }else {
                                Intent intent2 = new Intent(MainActivity.this, SetActivity.class); // 네비게이션 바 팝업 메뉴 설정
                                startActivity(intent2);
                            }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
      }
    ////////////////python execution
    public static void init_python(Context context) {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("myscript");
        PyObject import_embedding_model = pyobj.callAttr("import_embedding_model");
        PyObject import_recipe_vector = pyobj.callAttr("import_recipe_vector");
    }

    public static String[] compute_sim(Object[] pn_arr, Context context){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        String[] temp_rse;
        PyObject pyobj = py.getModule("myscript");
        Object temp = pyobj.callAttr("compute_similarity", pn_arr);
        temp = temp.toString();
        temp_rse = temp.toString().split("/");
        return temp_rse;
    }
    ///////////////
    public void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.frige) {
                fragment = new Fragment1();

            } else if (id == R.id.home){

                fragment = new Fragment2();
            }else {
                fragment = new Fragment3();
            }
            fragmentTransaction.replace(R.id.frameLayout, fragment, tag);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }
    public static Object[] get_nouns(String input_text){ //명사 추출기
        Object[] array_res;

        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(input_text);
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
        List<KoreanTokenJava> res = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);
        ArrayList<Object> arr = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).getPos() == KoreanPosJava.Noun) {
                arr.add(res.get(i).getText());
            }
        }
        array_res = arr.toArray();
        return array_res;
    }

    public static Object[] get_nouns(Object[] input_text){ //명사 추출기

        String[] text = Arrays.copyOf(input_text, input_text.length, String[].class);//object[] -> string[]
        Object[] array_res;
        Object[] res = new Object[input_text.length];
        HashSet<Object> arr = new HashSet<>();

        for (String s : text) {//형태소 분석
            CharSequence normalized = OpenKoreanTextProcessorJava.normalize(s);
            Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
            List<KoreanTokenJava> res_nouns = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);

            for (int f = 0; f < res_nouns.size(); f++) { //상품명 제거
                if (res_nouns.get(f).getPos() == KoreanPosJava.Noun) {
                    if (res_nouns.size() == 1) {
                        arr.add(res_nouns.get(f).getText());
                    } else if (res_nouns.size() > 1 && f > 0) {
                        arr.add(res_nouns.get(f).getText());
                    }
                    //arr.add(res_nouns.get(f).getText());
                    System.out.println("res_nouns.getText() = " + f + res_nouns.get(f).getText());
                }
            }
            res = arr.toArray();
            System.out.println("asd" + Arrays.toString(arr.toArray()));
        }

        array_res = res;
        return array_res;
    }
}
