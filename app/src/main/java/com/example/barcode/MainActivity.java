package com.example.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    DatabaseBuilder builder;
    SqlConnect sqlConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Barcode);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // 밤에 실행시 다크 모드 관련 실행직후 검은화면 뜨는 문제 해결 필요
        ////////////////////////////////////// 앱 시작시 php서버에 접속하여 db를 불러와 room에 저장
        //builder = new DatabaseBuilder(this); //DB초기화
        sqlConnect = new SqlConnect();               //객체생성

        SqlConnect.Get_Barcode_php task = new SqlConnect.Get_Barcode_php();
        SqlConnect.Get_RecipeBasic_php rbtask = new SqlConnect.Get_RecipeBasic_php();
        SqlConnect.Get_RecipeMaterial_php rmtask = new SqlConnect.Get_RecipeMaterial_php();
        SqlConnect.Get_RecipeProcess_php rptask = new SqlConnect.Get_RecipeProcess_php();
        try {
            builder = new DatabaseBuilder(this); //DB초기화
            builder.addBCtuples(builder.getBCData(task.execute().get()), DatabaseBuilder.Barcode_DB);
            builder.addRBtuples(builder.getRBData(rbtask.execute().get()), DatabaseBuilder.RecipeB_DB);
            builder.addRMtuples(builder.getRMData(rmtask.execute().get()), DatabaseBuilder.RecipeM_DB);
            builder.addRPtuples(builder.getRPData(rptask.execute().get()), DatabaseBuilder.RecipeP_DB);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //////////////////////////////////////
        mBottomNV = findViewById(R.id.navigation);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());
                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.home);

        findViewById(R.id.menuSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_menu1){
                            Toast.makeText(MainActivity.this, "메뉴 1", Toast.LENGTH_SHORT).show();
                        }else if(item.getItemId() == R.id.action_menu2){
                            Intent intent = new Intent(MainActivity.this, SetActivity.class);
                            startActivity(intent);
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
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
        fragmentTransaction.commitNow();
    }
}
