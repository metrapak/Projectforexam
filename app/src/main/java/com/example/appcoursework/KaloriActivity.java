package com.example.appcoursework;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcoursework.data.DbHelper;

public class KaloriActivity extends AppCompatActivity {
    String name, age, weight, height, gender, physical,results;
    String  caloriSentence;
    int ages, weights, heights;
    DbHelper dbHelper1;
    boolean sex;
    int result;
    double k = 1.5, temp;
    TextView txtResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori);
        txtResults = findViewById(R.id.txtResults);
        getInfo();
        CountCalori();
        OutputCaloruResults();
    }
    public void getInfo() {
        dbHelper1 = new DbHelper(this);
    SQLiteDatabase database = dbHelper1.getWritableDatabase();
    Cursor cursor = database.query(dbHelper1.TABLE_USERINFORMATION, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
        int nameIndex = cursor.getColumnIndex(dbHelper1.COLUMN_NAME);
        int ageIndex = cursor.getColumnIndex(dbHelper1.COLUMN_AGE);
        int weightIndex = cursor.getColumnIndex(dbHelper1.COLUMN_WEIGHT);
        int heightIndex = cursor.getColumnIndex(dbHelper1.COLUMN_HEIGHT);
        int genderIndex = cursor.getColumnIndex(dbHelper1.COLUMN_GENDER);
        int physicalIndex = cursor.getColumnIndex(dbHelper1.COLUMN_PHYSICAL);
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
        dbHelper1.close();
        ages = Integer.parseInt(age);
        heights = Integer.parseInt(height);
        weights = Integer.parseInt(weight);
    }


    public void CountCalori()

    {
        if (physical == "Не тренируюсь вообще") {
            k = 1.2;
        }
        else if (physical == "Упражнения 1-2 раза в неделю ") {
            k = 1.375;
        } else if (physical == "Упражнения 3-4 раза в неделю ") {
            k = 1.55;
        } else if (physical == "Упражнения 5-6 раза в неделю ") {
            k = 1.725;
        } else if (physical == "Упражнения два раза в день ") {
            k = 1.9;
        }


        if (gender == "Мужчина")
        {
            sex = true;
        }
        else sex = false;


        if (sex) {
          temp = 66.5 + (13.8 * (double)weights) + (5 * (double)heights) / (6.8 * (double)ages);
          result =  (int)(temp * k);
       } else {
             temp = 655.1 + (9.6 * weights) + (1.9 * heights) / (4.7 * ages);
            result = (int) (temp * k);
        }
        results=Integer.toString(result);

    }
    public void OutputCaloruResults() {
        caloriSentence = "Имя " + name + age+ ", данных, которые были введены:\nПол: " + gender + "\nВес: " + weight + " kg\nРост : " + height + " cm"
                + "\n Ваша активность"+ physical+
                "\nТогда общее количество калорий, необходимое вам в день, равно " +  result + " ккал или эквивалент "
                + result   ;
        txtResults.setText(caloriSentence);
    }
    public void returning(View w)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
