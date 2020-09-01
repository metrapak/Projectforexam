package com.example.appcoursework;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appcoursework.adapters.DataProductAdapter;
import com.example.appcoursework.data.DbProductHelper;
import com.example.appcoursework.data.SQLiteHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class  Products extends AppCompatActivity{




    EditText editProduct, editEnergy,editProteins,editFats,editCarbohydrates;
    Button  btnAdd, btnList;



    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        init();

        sqLiteHelper = new SQLiteHelper(this, "Products.sqlite", null, 1);

        sqLiteHelper.queryData
                ("CREATE TABLE IF NOT EXISTS PRODUCTS(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, energy VARCHAR,proteins VARCHAR, fats VARCHAR,carbohydrates VARCHAR)");



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insert(
                            editProduct.getText().toString().trim(),
                            editEnergy.getText().toString().trim(),
                            editProteins.getText().toString().trim(),
                            editFats.getText().toString().trim(),
                            editCarbohydrates.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    editProduct.setText("");
                    editEnergy.setText("");
                    editProteins.setText("");
                    editFats.setText("");
                    editCarbohydrates.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Products.this, ProductList.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        editProduct =  findViewById(R.id.editProduct);
        editEnergy =  findViewById(R.id.editEnergy);
        editProteins = findViewById(R.id.editProteins);
        editFats =  findViewById(R.id.editFats);
        editCarbohydrates = findViewById(R.id.editCarbohydrates);
        btnAdd =  findViewById(R.id.buttonAdd);
        btnList =  findViewById(R.id.buttonList);

    }

    public void returnFromProducts(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

//public class Products extends AppCompatActivity {
//
//    private SQLiteDatabase mDatabase;
//    private DataProductAdapter mAdapter;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_products);
//
//        DbProductHelper dbProductHelper = new DbProductHelper(this);
//        mDatabase = dbProductHelper.getWritableDatabase();
//
//
//        RecyclerView recyclerView = findViewById(R.id.product_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//         mAdapter = new DataProductAdapter(this, getAllItems());
//         recyclerView.setAdapter(mAdapter);
//    }
//
//
//    public Cursor getAllItems() {
//        return mDatabase.query(
//                ProductContract.Product.TABLE_PRODUCTS,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//    }
//    public void addProduct(View v)
//    {
//        Intent intent = new Intent(this, AddProductActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//}

