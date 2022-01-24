package com.example.BMICALCULATE;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<BMIResult> {
    private ArrayList<BMIResult> results;
    private Context context;


    public Adapter(Context context, ArrayList<BMIResult> results) {
        super(context, R.layout.activity_saved_v2, results);
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.textviews, parent, false);

        TextView tvWeight = view.findViewById(R.id.sWeight);
        String w = String.valueOf(results.get(position).getWeight()).replace(".0", "");
        tvWeight.setText("Weight: " + w + " kg");

        TextView tvHeight = view.findViewById(R.id.sHeight);
        String h = String.valueOf(results.get(position).getHeight()*100).replace(".0", "");
        tvHeight.setText("Height: " + h + " cm");

        TextView tvIndex = view.findViewById(R.id.sIndex);
        tvIndex.setText("BMI: " + String.format("%.2f", results.get(position).getIndex()));

        TextView tvIndexValue = view.findViewById(R.id.sIndexValue);
        tvIndexValue.setText(results.get(position).getIndexValue());

        Button delete = view.findViewById(R.id.deleteBtn);

        final DatabaseManager db = new DatabaseManager(context);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(db.deleteData(String.valueOf(results.get(position).getID()))){
                    results.remove(results.get(position));
                    notifyDataSetChanged();
                    db.close();
                }
            }
        });
        return view;
    }
}