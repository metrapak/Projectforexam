package com.example.appcoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appcoursework.data.DbHelper;

public class PulseActivity extends AppCompatActivity {
    String PulseSentence;
    TextView txtInfo;
    DbHelper dbHelper;
    String age;
    int ages;
    int min;
    int max;
    int critical;
    int f200=220;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);
        txtInfo = findViewById(R.id.txtInfoPulse);
        getInformation();
        countPulse();
        ket();
    }
    public void getInformation() {

        dbHelper = new DbHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(dbHelper.TABLE_USERINFORMATION, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int ageIndex = cursor.getColumnIndex(dbHelper.COLUMN_AGE);
            while (cursor.moveToNext()) {
                age = cursor.getString(ageIndex);
            }

        }
        cursor.close();
        dbHelper.close();
        ages = Integer.parseInt(age);


    }

    public void countPulse(){
        critical = f200 - ages;
        min = (int)(critical*0.65);
        max = (int)(critical*0.85);
    }
    public void ket() {
        PulseSentence = "Ваш критический  пульс : " + critical + "\nНижняя граница пульса  для сжигания жира: " + min +
                "\nВерхняя граница для сжигания жира:" + max  ;
        txtInfo.setText(PulseSentence);

    }
    public void returning(View w)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}
