package com.example.appcoursework.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, String timeCooking,String description){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, timeCooking);
        statement.bindString(3,description);


        statement.executeInsert();
    }
    public void insert(String name, String energy, String proteins, String fats, String carbohydrates){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO PRODUCTS VALUES (NULL, ?, ?, ?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, energy);
        statement.bindString(3, proteins);
        statement.bindString(4, fats);
        statement.bindString(5, carbohydrates);


        statement.executeInsert();
    }

    public void updateData(String name, String timeCooking,  String description, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE FOOD SET name = ?, timeCooking = ?,  description = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, timeCooking);

        statement.bindString(3,description);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }
    public void update(String name, String energy, String proteins, String fats,String carbohydrates, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE PRODUCTS SET name = ?, energy = ?, proteins = ?, fats = ?, carbohydrates = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, energy);
        statement.bindString(3, proteins);
        statement.bindString(4, fats);
        statement.bindString(5, carbohydrates);
        statement.bindDouble(6, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM FOOD WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close()    ;
    }
    public  void delete(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM PRODUCTS WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close()    ;
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}