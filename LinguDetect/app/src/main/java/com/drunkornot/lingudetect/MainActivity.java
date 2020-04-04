package com.drunkornot.lingudetect;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.drunkornot.lingudetect.lingu.AppSettings;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnGoToCameraActivity;
    Spinner spLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitApp();
        InitView();

        spLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectLang = spLang.getSelectedItem().toString();
                String langCode = AppSettings.Instance().GetLanguageCodeForName(selectLang);
                AppSettings.Instance().GetCurrentUser().SetUsersLearningLanguage(langCode);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGoToCameraActivity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, DetectorActivity.class));
                AppSettings.Instance().GetCurrentUser().SetUsersLearningLanguage("pl");
            }
        });
    }

    private void InitView() {
        btnGoToCameraActivity = findViewById(R.id.goToCameraActivity);
        spLang = findViewById(R.id.spLang);

        ArrayList<String> langs = AppSettings.Instance().GetAvailableLanguageNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, langs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLang.setAdapter(adapter);
    }

    private void InitApp() {
        AppSettings.Instance().GetCurrentUser().SetUsersNativeLanguage(Locale.getDefault().getLanguage());
    }
}
