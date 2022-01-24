package com.example.BMICALCULATE;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputLayout;

public class CalculateActivity extends AppCompatActivity
        implements View.OnClickListener {
    TextInputLayout weightInput, heightInput;
    Button calculate, goBack, save;
    TextView result;
    Double w, h, index;
    DatabaseManager dbManager;
    BMIResult res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        initializeViews();
        dbManager = new DatabaseManager(this);
        calculate.setOnClickListener(this);
        goBack.setOnClickListener(this);
        save.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calcBtn:
                    String wInput = weightInput.getEditText().getText().toString();
                    String hInput = heightInput.getEditText().getText().toString();
                    if(validateInput(wInput, hInput)){
                        this.h /= 100;
                        index = w / Math.pow(h, 2);
                        res = new BMIResult(w, h, index);
                        result.setText("BMI: " + String.format("%.2f", res.index) + "\n" + res.indexValue);
                    }
                    else
                        result.setText("");
                InputMethodManager im = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.backBtn:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
            case R.id.saveBtn:
                if(result.getText().toString().isEmpty())
                    toastMessage("You have to make a calculation first.");
                else{
                    boolean insertData = dbManager.addData(res);
                    if(insertData == true)
                        toastMessage("saved");
                    else
                        toastMessage("Data was not inserted!");
                }
                break;
        }
    }
    private boolean validateInput(String w, String h){
        boolean valid = false;
        if(w.isEmpty() || h.isEmpty()){
            toastMessage("BLANK space");
        }
        else{
            valid = true;
            try {
                this.w = Double.parseDouble(w);
                this.h = Double.parseDouble(h);
            } catch(Exception e){
                valid = false;
                toastMessage("Invalid input.");
            }
        }
        return valid;
    }

    private void initializeViews() {
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculate = findViewById(R.id.calcBtn);
        result = findViewById(R.id.result);
        save = findViewById(R.id.saveBtn);
        goBack = findViewById(R.id.backBtn);
    }
    private void toastMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}
