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

import com.example.appcoursework.adapters.DataProductAdapter;
import com.example.appcoursework.adapters.DataRecipeAdapter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

        GridView gridView;
        ArrayList<Product> list;
        DataProductAdapter adapter = null;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.product_list_activity);

            gridView =  findViewById(R.id.gridViewProduct);
            list = new ArrayList<>();
            adapter = new DataProductAdapter(this, R.layout.product_item, list);
            gridView.setAdapter(adapter);

            // get all data from sqlite
            Cursor cursor = Products.sqLiteHelper.getData("SELECT * FROM PRODUCTS");
            list.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String energy = cursor.getString(2);
                String proteins = cursor.getString(3);
                String fats = cursor.getString(4);
                String carbohydrate = cursor.getString(5);

                list.add(new Product(name, energy,proteins , fats,carbohydrate, id));
            }
            adapter.notifyDataSetChanged();

            gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                    CharSequence[] items = {"Редактировать", "Удалить"};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(com.example.appcoursework.ProductList.this);

                    dialog.setTitle("Выберите действие");
                    dialog.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                // update
                                Cursor c = Products.sqLiteHelper.getData("SELECT id FROM PRODUCTS");
                                ArrayList<Integer> arrID = new ArrayList<Integer>();
                                while (c.moveToNext()) {
                                    arrID.add(c.getInt(0));
                                }
                                // show dialog update at here
                                showDialogUpdate(com.example.appcoursework.ProductList.this, arrID.get(position));

                            } else {
                                // delete
                                Cursor c = Products.sqLiteHelper.getData("SELECT id FROM PRODUCTS");
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



        private void showDialogUpdate(Activity activity, final int position) {

            final Dialog dialog = new Dialog(activity);
            dialog.setContentView(R.layout.update_product_activity);
            dialog.setTitle("Редактировать");
            final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
            final EditText edtEnergy = (EditText) dialog.findViewById(R.id.edtEnergy);
            final EditText edtProteins = dialog.findViewById(R.id.edtProteins);
            final EditText edtFats = dialog.findViewById(R.id.edtFats);
            final EditText edtCarbohydrates = dialog.findViewById(R.id.edtCarbohydrates);

            Button btnUpdate = (Button) dialog.findViewById(R.id.buttonUpdate);

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
                        Products.sqLiteHelper.update(
                                edtName.getText().toString().trim(),
                                edtEnergy.getText().toString().trim(),
                                edtProteins.getText().toString().trim(),
                                edtFats.getText().toString().trim(),
                                edtCarbohydrates.getText().toString().trim(),
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
            final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(com.example.appcoursework.ProductList.this);

            dialogDelete.setTitle("Внимание!!");
            dialogDelete.setMessage("Вы уверены , что действительно хотите удалит?");
            dialogDelete.setPositiveButton("Oк", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        Products.sqLiteHelper.delete(idFood);
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
            Cursor cursor = Products.sqLiteHelper.getData("SELECT * FROM PRODUCTS");
            list.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String energy = cursor.getString(2);
                String proteins = cursor.getString(3);
                String fats = cursor.getString(4);
                String carbohydrates = cursor.getString(5);

                list.add(new Product(name, energy, proteins, fats,carbohydrates, id));
            }
            adapter.notifyDataSetChanged();
        }


}
