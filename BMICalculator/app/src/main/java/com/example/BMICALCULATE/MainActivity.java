package com.example.BMICALCULATE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    Button calculate, viewSaved,about, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        calculate.setOnClickListener(this);
        viewSaved.setOnClickListener(this);
        exit.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.abtBtn:
                i = new Intent(getApplicationContext(), AboutActivity.class);
                break;
            case R.id.calculateBtn:
                i = new Intent(getApplicationContext(), CalculateActivity.class);
                break;
            case R.id.savedBtn:
                i = new Intent(getApplicationContext(), ListDataActivity.class);
                break;
            case R.id.exitBtn:
                finish();
                moveTaskToBack(true);
                break;
        }
        startActivity(i);
    }

    private void initializeViews() {
        calculate = findViewById(R.id.calculateBtn);
        viewSaved = findViewById(R.id.savedBtn);
        exit = findViewById(R.id.exitBtn);
        about = findViewById(R.id.abtBtn);

    }
}
