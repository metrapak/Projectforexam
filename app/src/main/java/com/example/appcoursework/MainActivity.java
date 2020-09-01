package com.example.appcoursework;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.appcoursework.data.DbHelper;

public class MainActivity extends AppCompatActivity {

    String information,hi;
    TextView txtInformation,txthi;

    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbHelper = new DbHelper(this);
        txtInformation = findViewById(R.id.textInformation);
        txthi=findViewById(R.id.txthi);
        showInformation();
    }


    public void showInformation() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(dbHelper.TABLE_USERINFORMATION, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(dbHelper._ID);
            int nameIndex = cursor.getColumnIndex(dbHelper.COLUMN_NAME);
            int ageIndex = cursor.getColumnIndex(dbHelper.COLUMN_AGE);
            int weightIndex = cursor.getColumnIndex(dbHelper.COLUMN_WEIGHT);
            int heightIndex = cursor.getColumnIndex(dbHelper.COLUMN_HEIGHT);
            int genderIndex = cursor.getColumnIndex(dbHelper.COLUMN_GENDER);
            int physicalIndex = cursor.getColumnIndex(dbHelper.COLUMN_PHYSICAL);
            do {
                hi =  "Привет,"+cursor.getString(nameIndex)+",ваши данные:";
                information =
                        " Возраст:" + cursor.getString(ageIndex) +" лет"+
                        " \nВес:" + cursor.getString(weightIndex) +" кг"+
                        " \nРост:" + cursor.getString(heightIndex) +" см"+
                        " \nПол: " + cursor.getString(genderIndex) +
                        " \nФизическая активность:" + cursor.getString(physicalIndex);
                txthi.setText(hi);
                txtInformation.setText(information);

            } while (cursor.moveToNext());

        } else
            txtInformation.setText("Error");
        cursor.close();
        dbHelper.close();

    }
     public void countBMI(View v)
     {
         Intent intent = new Intent(this, BMIActivity.class);
         startActivity(intent);
         finish();
     }
     public void countCalori(View v)
     {
         Intent intent = new Intent(this, KaloriActivity.class);
         startActivity(intent);
         finish();
     }
    public void countPulse(View v)
    {
        Intent intent = new Intent(this, PulseActivity.class);
        startActivity(intent);
        finish();
    }

     public void editInformation(View v)
     {
         Intent intent = new Intent(this, EditUserInformationActivity.class);
         startActivity(intent);
         finish();
     }
    public void showProducts(View v)
    {
        Intent intent = new Intent(this, Products.class);
        startActivity(intent);
        finish();
    }
   public void showRecipes(View v)
    {
        Intent intent = new Intent(this, Recipes.class);
        startActivity(intent);
        finish();
    }
}









