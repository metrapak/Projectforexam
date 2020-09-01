package com.example.appcoursework;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcoursework.data.DbHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BMIActivity extends AppCompatActivity {

    String name, age, weight, height, gender, physical;
    String results, recommendation, BmiSentence, REC;
    int ages, weights, heights;
    DbHelper dbHelper;
    boolean sex;
    double ideal, counterIMT, newCounter;
    TextView txtInfo, txtRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        txtInfo = findViewById(R.id.txtBmiResults);
        txtRec = findViewById(R.id.txtRecommendation);
        getInformation();
        checkingGender();
        countIMT();
        ideal();
        ket();
    }

    public void getInformation() {

        dbHelper = new DbHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(dbHelper.TABLE_USERINFORMATION, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            int nameIndex = cursor.getColumnIndex(dbHelper.COLUMN_NAME);
            int ageIndex = cursor.getColumnIndex(dbHelper.COLUMN_AGE);
            int weightIndex = cursor.getColumnIndex(dbHelper.COLUMN_WEIGHT);
            int heightIndex = cursor.getColumnIndex(dbHelper.COLUMN_HEIGHT);
            int genderIndex = cursor.getColumnIndex(dbHelper.COLUMN_GENDER);
            int physicalIndex = cursor.getColumnIndex(dbHelper.COLUMN_PHYSICAL);
            while (cursor.moveToNext()) {

                name = cursor.getString(nameIndex);
                age = cursor.getString(ageIndex);
                weight = cursor.getString(weightIndex);
                height = cursor.getString(heightIndex);
                gender = cursor.getString(genderIndex);
                physical = cursor.getString(physicalIndex);

            }

        }
        cursor.close();
        dbHelper.close();
        ages = Integer.parseInt(age);
        heights = Integer.parseInt(height);
        weights = Integer.parseInt(weight);

    }

    public void checkingGender() {

        if (gender == "Мужчина") {
            sex = true;
        } else sex = false;
    }


    public void countIMT() {
        double ban = ((double) heights / 100) * ((double) heights / 100);
        counterIMT = ((double) weights / ban);
        newCounter = new BigDecimal(counterIMT).setScale(2, RoundingMode.UP).doubleValue();

        if (counterIMT < 17 && sex) {
            results = " немного худоваты";
            recommendation = "увеличить потребление калорийной пищи";
        } else if (counterIMT < 24 && sex) {
            results = "отличная форма";
            recommendation = "достаточно регулярно заниматься спортом и поддерживать здоровье";
        } else if (counterIMT < 28 && sex) {
            results = " немного полноваты";
            recommendation = "регулярно заниматься спортом и ограничить потребление калорий";
        } else if (counterIMT > 27 && sex) {
            results = "ожирение";
            recommendation = "пройти программу по снижению веса и усердно заниматься физическими упражнениями";
        }

        if (counterIMT < 18 && !sex) {
            results = "немного худоваты";
            recommendation = "увеличить потребление калорийной пищи";
        } else if (counterIMT < 26 && !sex) {
            results = "отличная форма";
            recommendation = "достаточно регулярно заниматься спортом и поддерживать здоровье";
        } else if (counterIMT < 28 && !sex) {
            results = "немного полноваты";
            recommendation = "регулярно заниматься спортом и ограничить потребление калорий";
        } else if (counterIMT > 27 && !sex) {
            results = "ожирение";
            recommendation = "пройти программу по снижению веса и усердно заниматься физическими упражнениями";
        }
    }

    public void ideal() {
        if (sex) {
            ideal = (heights - 100) - ((double) 10 / 100 * (heights - 100));
        } else {
            ideal = (heights - 100) - ((double) 15 / 100 * (heights - 100));
        }
    }


    public void ket() {
        BmiSentence = "Ваш пол : " + gender + "\nВес тела: " + weight + " kg \nРост: " + height + " cm";

        REC = "\nВаш индекс массы тела (ИМТ) " + newCounter + " классифицируется как " + results + " , рекомендуется  " + recommendation + "." +
                "\nИдеальная масса тела, рекомендуемая для вас " + ideal + " kg";

        txtInfo.setText(BmiSentence);
        txtRec.setText(REC);
    }

    public void returning(View w)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
//
//    public void button(View view) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:" + email));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Результаты вашего индекса массы тела " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, BmiSentence);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
//}
