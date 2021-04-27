package com.example.barcode;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> arrayList = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();


    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        arrayList.add(new FragmentItem1());
        arrayList.add(new FragmentItem2());
        arrayList.add(new FragmentItem3());
        arrayList.add(new FragmentItem4());
        arrayList.add(new FragmentItem5());
        arrayList.add(new FragmentItem6());
        arrayList.add(new FragmentItem7());

        name.add("채소");
        name.add("양곡");
        name.add("견과");
        name.add("과일");
        name.add("육류");
        name.add("수산물");
        name.add("유제품");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}
