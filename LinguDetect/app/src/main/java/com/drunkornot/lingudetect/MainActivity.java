package com.drunkornot.lingudetect;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnGoToCameraActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitApp();
        InitView();

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
    }

    private void InitApp() {
    }
}
