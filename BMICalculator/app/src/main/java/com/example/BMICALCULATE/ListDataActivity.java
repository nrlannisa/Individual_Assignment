package com.example.BMICALCULATE;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ListDataActivity extends AppCompatActivity
        implements View.OnClickListener {
    DatabaseManager dbManager;
    Button menu;
    ListView resView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_v2);
        initializeViews();
        menu.setOnClickListener(this);
        dbManager = new DatabaseManager(this);
        adapter = new Adapter(this, dbManager.getData());
        resView.setAdapter(adapter);
    }

    private void initializeViews() {
        menu = findViewById(R.id.smenuBtn2);
        resView = findViewById(R.id.resView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smenuBtn2:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
        }
    }
}
