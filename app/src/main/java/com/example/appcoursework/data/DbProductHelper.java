package com.example.appcoursework.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appcoursework.ProductContract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.appcoursework.ProductContract.Product.COLUMN_CARBOHYDRATES;
import static com.example.appcoursework.ProductContract.Product.COLUMN_ENERGY;
import static com.example.appcoursework.ProductContract.Product.COLUMN_FATS;
import static com.example.appcoursework.ProductContract.Product.COLUMN_NAMEPRODUCT;
import static com.example.appcoursework.ProductContract.Product.COLUMN_PROTEINS;
import static com.example.appcoursework.ProductContract.Product.ID;

public class DbProductHelper extends SQLiteOpenHelper {

    //создание базы данных для продуктов
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "products.Db";
    public static final String TABLE_PRODUCTS = "products";
    public DbProductHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
        @Override
        public void onCreate(SQLiteDatabase db){
             final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + "("
                    + ID + " integer primary key,"
                    +COLUMN_NAMEPRODUCT + " text,"
                    + COLUMN_ENERGY + " text,"
                    + COLUMN_PROTEINS + " text,"
                    + COLUMN_FATS + " text,"
                    + COLUMN_CARBOHYDRATES + " text" +")";
             db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ ProductContract.Product.TABLE_PRODUCTS);
    }
}


