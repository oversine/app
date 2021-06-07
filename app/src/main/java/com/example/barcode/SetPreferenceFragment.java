package com.example.barcode;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.ListPreference;

public class SetPreferenceFragment extends PreferenceFragmentCompat {
    SharedPreferences prefs;
    ListPreference dataPreference;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preference);
        dataPreference = (ListPreference)findPreference("userData_list");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("userData_list", "").equals("")){
            dataPreference.setSummary(prefs.getString("userData_list","7")); // 유통기한 알림 기본 설정 값 7일 설정
        }

        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("userData_list")){
                dataPreference.setSummary(prefs.getString("userData_list","7"));
            }
        }
    };
}
