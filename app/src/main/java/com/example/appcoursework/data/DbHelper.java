package com.example.appcoursework.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

public class DbHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "user.Db";
    public static final String TABLE_USERINFO = "userInfo";
    public static final String TABLE_USERINFORMATION = "userInformation";
    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_AGE= "age";
    public final static String COLUMN_WEIGHT = "weight";
    public final static String COLUMN_HEIGHT= "height";
    public final static String COLUMN_GENDER = "gender";
    public final static String COLUMN_PHYSICAL = "physical";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_USERINFORMATION + "("
                + _ID + " integer primary key,"
                + COLUMN_NAME + " text,"
                + COLUMN_AGE + " text,"
                + COLUMN_WEIGHT + " text,"
                + COLUMN_HEIGHT + " text,"
                + COLUMN_GENDER + " text,"
                + COLUMN_PHYSICAL + " text " + ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USERINFORMATION);
    }
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM TABLE_USERINFORMATION";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.execute();
        database.close()    ;
    }
}