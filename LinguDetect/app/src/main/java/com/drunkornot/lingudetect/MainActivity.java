package com.drunkornot.lingudetect;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.drunkornot.lingudetect.lingu.AppSettings;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnGoToCameraActivity;
    Spinner spLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitApp();
        InitView();

        AppSettings.Instance().GetCurrentUser().SetUsersNativeLanguage("pl");

        ArrayList<String> langs = AppSettings.Instance().GetAvailableLanguageNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, langs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLang.setAdapter(adapter);

        btnGoToCameraActivity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, DetectorActivity.class));
            }
        });
    }

    private void InitView() {
        btnGoToCameraActivity = findViewById(R.id.goToCameraActivity);
        spLang = findViewById(R.id.spLang);
    }

    private void InitApp() {
    }
}
