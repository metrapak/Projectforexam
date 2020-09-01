package com.example.appcoursework;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.appcoursework.adapters.DataRecipeAdapter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class RecipeList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Recipe> list;
    DataRecipeAdapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new DataRecipeAdapter(this, R.layout.food_item, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = Recipes.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String timeCooking = cursor.getString(2);

            String description = cursor.getString(3);

            list.add(new Recipe(name, timeCooking, description, id));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Обновить", "Удалить"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(RecipeList.this);

                dialog.setTitle("Выберите действие:");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = Recipes.sqLiteHelper.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(RecipeList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = Recipes.sqLiteHelper.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    ImageView imageViewFood;

    private void showDialogUpdate(Activity activity, final int position) {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_recipe_activity);
        dialog.setTitle("Обновить");


        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtTimeCooking = (EditText) dialog.findViewById(R.id.edtTimeCooking);
        final EditText edtDescription = dialog.findViewById(R.id.edtDescription);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Recipes.sqLiteHelper.updateData(
                            edtName.getText().toString().trim(),
                            edtTimeCooking.getText().toString().trim(),
                            edtDescription.getText().toString().trim(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Обновлено успешно!!!", Toast.LENGTH_SHORT).show();
                } catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateFoodList();
            }
        });
    }

    private void showDialogDelete(final int idFood) {
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(RecipeList.this);

        dialogDelete.setTitle("Внимание");
        dialogDelete.setMessage("Вы уверены, что хотите удалить?");
        dialogDelete.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Recipes.sqLiteHelper.deleteData(idFood);
                    Toast.makeText(getApplicationContext(), "Удалено успешно!!!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList() {
        // get all data from sqlite
        Cursor cursor = Recipes.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String timeCooking = cursor.getString(2);
            String description = cursor.getString(3);

            list.add(new Recipe(name, timeCooking, description, id));
        }
        adapter.notifyDataSetChanged();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            //Обрабатываем нажатие кнопки "Назад":
            case KeyEvent.KEYCODE_BACK:
                //Устанавливаем совместимость более нового метода onBackPressed() с ранними версиями SDK:
                if (android.os.Build.VERSION.SDK_INT
                        < android.os.Build.VERSION_CODES.ECLAIR
                        && event.getRepeatCount() == 0) {
                    onBackPressed();
                }
        }
        return super.onKeyDown(keyCode, event);
    }

    //Обрабатываем нажатие клавиши "Назад" методом специальным методом onBackPressed:
    public void onBackPressed() {
        Toast.makeText(this, "Нажата кнопка Назад", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Recipes.class);
        startActivity(intent);
        finish();

    }
}