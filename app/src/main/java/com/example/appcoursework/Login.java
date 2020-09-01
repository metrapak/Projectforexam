package com.example.appcoursework;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcoursework.data.DbHelper;

import static com.example.appcoursework.data.DbHelper.TABLE_USERINFO;
import static com.example.appcoursework.data.DbHelper.TABLE_USERINFORMATION;

public class Login extends AppCompatActivity {

    DbHelper dbHelper;

    boolean checking;
    double k;
    EditText edit_user,edit_age,edit_weight,edit_height;

    View view;
    Button btnInput;
    RadioButton Male,Female;

    RadioGroup radio1,radio2;
    RadioButton btnNo, btn1_2, btn3_4, btn5_6, btn2Day;
    String name,ages,weights,heights,maleFemale,physicalAct ,gender;

         public void log(View view)
       {
           if (!checkIfEmpty()){
               Intent intent = new Intent(this, MainActivity.class);
               startActivity(intent);
               this.finish();
           }


   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DbHelper(this);
            log(view) ;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input);


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
        btnInput= findViewById(R.id.button_login);
        if (Male.getText().toString()  == "Мужчина"   ) {
            maleFemale = "Мужчина";
        } else maleFemale = "Женщина";
        dbHelper = new DbHelper(this);
    }


    public boolean checkIfEmpty()
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = database.query(TABLE_USERINFORMATION, null, null, null, null, null, null);

        if (cursor != null)
        {
            try
            {
                //if it is empty, returns true.
                cursor.moveToFirst();
                if (cursor.getInt(0) == 0)
                    return true;
                else
                    return false;
            }

            //this error usually occurs when it is empty. So i return true as well. :)
            catch(CursorIndexOutOfBoundsException e)
            {
                return true;
            }

        }
        dbHelper.close();
        return false;

    }

    public void login(View v) {


        isChecked();
        if (checking) {
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
            if (name != null  && ages != null && weights != null && heights != null &&( Male.isChecked()||  Female.isChecked()))
            {
                Toast.makeText(this, "Успешный ввод данных", Toast.LENGTH_LONG).show();

            }
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbHelper.COLUMN_NAME, name);
            contentValues.put(DbHelper.COLUMN_AGE, ages);
            contentValues.put(DbHelper.COLUMN_WEIGHT, weights);
            contentValues.put(DbHelper.COLUMN_HEIGHT, heights);
            contentValues.put(DbHelper.COLUMN_GENDER, gender);
            contentValues.put(DbHelper.COLUMN_PHYSICAL, physicalAct);


            database.insert(TABLE_USERINFORMATION, null, contentValues);
            dbHelper.close();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


            finish();

        }
        else {
            Toast.makeText(this, "Проверьте ввод", Toast.LENGTH_LONG).show();
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




}
