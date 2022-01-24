package com.example.BMICALCULATE;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class AboutActivity extends AppCompatActivity
        implements View.OnClickListener {
    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initializeViews();
        menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.menuBtn:
                i = new Intent(getApplicationContext(), MainActivity.class);
                break;
        }
        startActivity(i);
    }

    private void initializeViews() {
        menu = findViewById(R.id.menuBtn);

    }
}

