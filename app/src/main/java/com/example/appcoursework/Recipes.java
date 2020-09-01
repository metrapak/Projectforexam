package com.example.appcoursework;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.example.appcoursework.data.SQLiteHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Recipes extends AppCompatActivity {

    EditText edtName, edtTimeCooking,edtDescription;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        init();

        sqLiteHelper = new SQLiteHelper(this, "Recipes.db", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS FOOD(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, timecooking VARCHAR,description VARCHAR)");



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            edtName.getText().toString().trim(),
                            edtTimeCooking.getText().toString().trim(),
                            edtDescription.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(), "Добавлено успешно", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtTimeCooking.setText("");
                    edtDescription.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
public void toList(View v)
{
    Intent intent = new Intent(this, RecipeList.class);
    startActivity(intent);
    finish();
}





    private void init(){
        edtName = (EditText) findViewById(R.id.edtName);
        edtTimeCooking = (EditText) findViewById(R.id.edtTimeCooking);
        edtDescription = findViewById(R.id.edtDescription);

        btnAdd =  findViewById(R.id.btnAdd);
        btnList =  findViewById(R.id.btnList);

    }
    public void returnFromRecipes(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
