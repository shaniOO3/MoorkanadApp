package com.mjpcproject.moorkanadapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class LoginPage extends AppCompatActivity {

    Locale locale;
    Button generateotp;
    TextView changelanguage;
    EditText PhoneNo;
    ProgressBar gbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_login_page);

        generateotp = findViewById(R.id.generateb);
        changelanguage = findViewById(R.id.changelang);
        PhoneNo = findViewById(R.id.phone);
        gbar = findViewById(R.id.progressBar1);

        if (locale.getLanguage().equals("en")){

            changelanguage.setText("Malayalam");

        }
        else if (locale.getLanguage().equals("ml")){

            changelanguage.setText("English");

        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        generateotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneno = PhoneNo.getText().toString();

                if (phoneno.isEmpty()){
                    PhoneNo.setError("Please enter your number");
                    PhoneNo.requestFocus();
                }
                else if (phoneno.length() != 10){
                    PhoneNo.setError("Please enter a valid number");
                    PhoneNo.requestFocus();
                }
                else {
                    gbar.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getApplicationContext(), OtpPage.class);
                    intent.putExtra("PhoneNo", phoneno);
                    startActivity(intent);
                }

            }
        });

        changelanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage();
            }
        });

    }

    private void ChangeLanguage(){

        if (locale.getLanguage().equals("en")){

            setLocale("ml");
            recreate();

        }
        else if (locale.getLanguage().equals("ml")){

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