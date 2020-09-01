package com.example.appcoursework.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcoursework.R;
import com.example.appcoursework.Recipe;

import java.util.ArrayList;

public class DataRecipeAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Recipe> foodsList;
    public DataRecipeAdapter(Context context, int layout, ArrayList<Recipe> foodsList) {
        this.context = context;
        this.layout = layout;
        this.foodsList = foodsList;
    }
    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{

        TextView txtName, txtTimeCooking,txtDescription;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName =  row.findViewById(R.id.txtName);
            holder.txtTimeCooking = (TextView) row.findViewById(R.id.txtTimeCooking);

            holder.txtDescription = row.findViewById(R.id.txtDescription);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }
        Recipe food = foodsList.get(position);

        holder.txtName.setText(food.getName());
        holder.txtTimeCooking.setText(food.getTimeCooking());
        holder.txtDescription.setText(food.getDescription());
        return row;
    }
}
