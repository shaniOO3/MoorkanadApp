package com.mjpcproject.moorkanadapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class LoginPage extends AppCompatActivity {

    Locale locale;
    Button generateotp;
    TextView changelanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_login_page);

        generateotp = findViewById(R.id.button2);
        changelanguage = findViewById(R.id.changelang);

        if (locale.getLanguage().equals("en")){

            changelanguage.setText("English");

        }
        else if (locale.getLanguage().equals("ml")){

            changelanguage.setText("മലയാളം");

        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        changelanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage();
            }
        });

    }

    private void ChangeLanguage(){

        if (locale.getLanguage().equals("en")){

            changelanguage.setText("മലയാളം");
            setLocale("ml");
            recreate();

        }
        else if (locale.getLanguage().equals("ml")){

            changelanguage.setText("English");
            setLocale("en");
            recreate();

        }

    }

    private void setLocale(String lang) {

        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }

    public void loadLocale(){

        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);

    }

}