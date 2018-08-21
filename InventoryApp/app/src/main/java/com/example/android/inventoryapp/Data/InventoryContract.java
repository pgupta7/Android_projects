package com.example.android.inventoryapp.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Prasoon Gupta on 1/20/2017.
 */

public class InventoryContract {
    public static final String CONTENT_AUTHORITY="com.example.android.inventoryapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String INVENTORY_PATH="inventory";
    public static final String INVENTORY_PATH2="inventory/#";

    public static final class InventoryEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, INVENTORY_PATH);
        public static final String TABLE_NAME="inventory";
        public static final String COLUMN_ID =BaseColumns._ID;
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_IMAGE="image_uri";
        public static final String COLUMN_PRICE="price";
        public static final String COLUMN_INSTOCK="In_Stock";
        public static final String COLUMN_SOLD="Sold";
        public static final String COLUMN_SUPPLIER="Supplier";
    }
}
