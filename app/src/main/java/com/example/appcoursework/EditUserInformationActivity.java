package com.example.appcoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.appcoursework.data.DbHelper;

public class EditUserInformationActivity extends AppCompatActivity {

    DbHelper dbHelper;

    EditText edit_user,edit_age,edit_weight,edit_height;

    boolean checking;
    Button btnInput;
    double k;
    RadioButton Male,Female;
    String id;
    RadioGroup radio1,radio2;
    RadioButton btnNo, btn1_2, btn3_4, btn5_6, btn2Day;
    String name,ages,weights,heights,maleFemale,physicalAct ,gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);


        edit_user =  findViewById(R.id.edit_user);
        edit_age =  findViewById(R.id.edit_age);
        edit_weight= findViewById(R.id.edit_weight);
        edit_height= findViewById(R.id.edit_height);
        Male =  findViewById(R.id.checkMale);
        Female = findViewById(R.id.checkFemale);
        btnNo = findViewById(R.id.btnNo);
        btn1_2 = findViewById(R.id.btn1_2);
        btn3_4 = findViewById(R.id.btn3_4);
        btn5_6 = findViewById(R.id.btn5_6);
        btn2Day = findViewById(R.id.btn2Day);

        radio1 = findViewById(R.id.radio);
        radio2 = findViewById(R.id.radio2);

        if (Male.getText().toString()  == "Мужчина"   ) {
            maleFemale = "Мужчина";
        } else maleFemale = "Женщина";
        dbHelper = new DbHelper(this);
    }


    public void Edit(View v)
    {
        isChecked();
        if(checking) {
        name = edit_user.getText().toString();
        ages = edit_age.getText().toString();
        weights = edit_weight.getText().toString();
        heights = edit_height.getText().toString();

        if (Male.isChecked()) {
            gender = "Мужчина";
        }
        if (Female.isChecked()) {
            gender = "Женщина";
        }

        if (btnNo.isChecked()) {
            k = 1.2;
            physicalAct = "Не тренируюсь вообще";
        } else if (btn1_2.isChecked()) {
            k = 1.375;
            physicalAct = "Упражнения 1-2 раза в неделю";
        } else if (btn3_4.isChecked()) {
            k = 1.55;
            physicalAct = "Упражнения 3-4 раза в неделю";
        } else if (btn5_6.isChecked()) {
            k = 1.725;
            physicalAct = "Упражнения 5-6 раза в неделю";
        } else if (btn2Day.isChecked()) {
            k = 1.9;
            physicalAct = "Упражнения два раза в день";
        }
        name = edit_user.getText().toString();
        ages = edit_age.getText().toString();
        weights = edit_weight.getText().toString();
        heights = edit_height.getText().toString();
        setContentView(R.layout.activity_edit_information);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(dbHelper.TABLE_USERINFORMATION, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(dbHelper._ID);
                do {
                    id =  cursor.getString(idIndex);
                } while (cursor.moveToNext());
            }

        cursor.close();
        dbHelper.close();

        SQLiteDatabase mdatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper._ID,id);
        contentValues.put(DbHelper.COLUMN_NAME, name);
        contentValues.put(DbHelper.COLUMN_AGE, ages);
        contentValues.put(DbHelper.COLUMN_WEIGHT, weights);
        contentValues.put(DbHelper.COLUMN_HEIGHT, heights);
        contentValues.put(DbHelper.COLUMN_GENDER, gender);
        contentValues.put(DbHelper.COLUMN_PHYSICAL, physicalAct);
        mdatabase.update(DbHelper.TABLE_USERINFORMATION, contentValues, "_ID= ?", new String[]{id});
        dbHelper.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    }
    public void isChecked() {
        checking = true;
        if (edit_user.getText().length() == 0) {
            checking = false;
            edit_user.setError("Заполните колонку имени");
        } else if (edit_age.getText().length() == 0) {
            checking = false;
            edit_age.setError("Заполните колонку возраст");
        } else if (edit_weight.getText().length() == 0) {
            checking = false;
            edit_weight.setError("Заполните колонку вес");
        } else if (edit_height.getText().length() == 0) {
            checking = false;
            edit_height.setError("Заполните колонку рост");
        } else if (!Female.isChecked() && !Male.isChecked()) {
            checking = false;
            Male.setError("Выберите пол");

        }
    }
    public void Back(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
