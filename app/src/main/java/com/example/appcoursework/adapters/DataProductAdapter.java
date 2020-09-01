package com.example.appcoursework.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appcoursework.Product;
import com.example.appcoursework.R;

import java.util.ArrayList;


public class DataProductAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Product> RecipeList;
    public DataProductAdapter(Context context, int layout, ArrayList<Product> foodsList) {
        this.context = context;
        this.layout = layout;
        this.RecipeList = foodsList;
    }
    @Override
    public int getCount() {
        return RecipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return RecipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{

        TextView txtName, txtEnergy,txtProteins,txtFats,txtCarbohydrates;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.name);
            holder.txtEnergy = (TextView) row.findViewById(R.id.energy);
            holder.txtProteins =  row.findViewById(R.id.proteins);
            holder.txtFats = row.findViewById(R.id.fats);
            holder.txtCarbohydrates = row.findViewById(R.id.carbohydrates);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }
        Product food = RecipeList.get(position);

        holder.txtName.setText(food.getName());
        holder.txtEnergy.setText(food.getEnergy());
        holder.txtProteins.setText(food.getProteins());
        holder.txtFats.setText(food.getFats());
        holder.txtCarbohydrates.setText(food.getCarbohydrates());

        return row;
    }
}


