package com.example.BMICALCULATE;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "bmi_results.db";
    private static final String TABLE_NAME = "bmi_results_table";
    private static final String id_col = "ID";
    private static final String weight_col = "Weight";
    private static final String height_col = "Height";
    private static final String result_col = "Result";

    public DatabaseManager(Context context){
        super(context, DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + id_col + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                weight_col + " REAL NOT NULL," + height_col + " REAL NOT NULL," +
                result_col + " REAL NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String where="ID=?";
        int numberOFEntriesDeleted =
                db.delete(TABLE_NAME, where, new String[]{id});
        if(numberOFEntriesDeleted != 0) return true;
        else return false;
    }

    public boolean addData(BMIResult r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(weight_col, r.weight);
        values.put(height_col, r.height);
        values.put(result_col, r.index);
        long insert = db.insert(TABLE_NAME,
                null, values);
        db.close();
        return (insert == 0) ? false : true;
    }

    public ArrayList<BMIResult> getData(){
        ArrayList<BMIResult> list = new ArrayList<>();
        BMIResult item;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID DESC";
        Cursor data = db.rawQuery(query, null);
        while(data.moveToNext()){
            item = new BMIResult();
            item.setID(data.getInt(data.getColumnIndex(id_col)));
            item.setWeight(data.getDouble(data.getColumnIndex(weight_col)));
            item.setHeight(data.getDouble(data.getColumnIndex(height_col)));
            item.setIndex(data.getDouble(data.getColumnIndex(result_col)));
            item.setIndexValue(item.getValue(item.index));
            list.add(item);
        }

        return list;
    }
    /*public ArrayList<HashMap<String, String>> getResults(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> recordList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> record = new HashMap<>();
            record.put("weight",cursor.getString(cursor.getColumnIndex(weight_col)));
            record.put("height",cursor.getString(cursor.getColumnIndex(height_col)));
            record.put("result",cursor.getString(cursor.getColumnIndex(result_col)));
            recordList.add(record);
        }
        return  recordList;
    }*/
}
