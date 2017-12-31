package com.prayorganizer.rxdd.orthodox.settings;


import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import android.os.Bundle;

import com.prayorganizer.rxdd.orthodox.R;

public class Settings extends PreferenceActivity {
    int pref_font_size;

    public int getPref_font_size() {
        return pref_font_size;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Settings.this);
       pref_font_size = prefs.getInt("preference_size", 15);

    }
}
