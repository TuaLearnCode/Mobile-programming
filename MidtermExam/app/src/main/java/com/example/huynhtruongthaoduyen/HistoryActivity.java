package com.example.huynhtruongthaoduyen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ListView lvHistory;
    private Button btnBackHome;
    private TicketAdapter adapter;
    private ArrayList<Ticket> list;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvHistory = findViewById(R.id.lvHistory);
        btnBackHome = findViewById(R.id.btnBackHome);
        db = new DatabaseHelper(this);

        list = db.getAllTickets();
        adapter = new TicketAdapter(this, list);
        lvHistory.setAdapter(adapter);

        btnBackHome.setOnClickListener(v -> finish());
    }
}
