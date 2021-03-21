package com.example.barcode;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

public class SetPreferenceFragment extends PreferenceFragment {
    SharedPreferences prefs;

    ListPreference dataPreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_preference);
        dataPreference = (ListPreference)findPreference("userData_list");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("userData_list", "").equals("")){
            dataPreference.setSummary(prefs.getString("userData_list","7"));
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
