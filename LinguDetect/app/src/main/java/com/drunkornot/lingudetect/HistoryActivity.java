package com.drunkornot.lingudetect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.drunkornot.lingudetect.lingu.AppSettings;
import com.drunkornot.lingudetect.lingu.HistoryAdapter;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView rvHistory;
    private RecyclerView.Adapter historyAdapter;
    private RecyclerView.LayoutManager historyLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setHasFixedSize(true);
        historyLayoutManager = new LinearLayoutManager(this);
        historyAdapter = new HistoryAdapter(AppSettings.Instance().GetCurrentUser().GetUsersHistory());

        rvHistory.setLayoutManager(historyLayoutManager);
        rvHistory.setAdapter(historyAdapter);
    }


}
