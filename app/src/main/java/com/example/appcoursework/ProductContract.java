package com.example.appcoursework;

import android.provider.BaseColumns;

public class ProductContract {

    private ProductContract() {
    }
    public static class Product implements BaseColumns {
        public final static String TABLE_PRODUCTS = "products";
        public final static String ID = BaseColumns._ID;
        public final static String COLUMN_NAMEPRODUCT = "name";
        public final static String COLUMN_ENERGY= "energy";
        public final static String COLUMN_PROTEINS = "proteins";
        public final static String COLUMN_FATS= "fats";
        public final static String COLUMN_CARBOHYDRATES = "carbohydrates";

    }
}