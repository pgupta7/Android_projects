package com.example.android.inventoryapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Prasoon Gupta on 1/20/2017.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {
    private static final String LOG_CAT=InventoryDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME="inventory.db";
    private static final int DATABASE_VERSION=1;
    public InventoryDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_TABLE="CREATE TABLE "+ InventoryContract.InventoryEntry.TABLE_NAME + " ("+ InventoryContract.InventoryEntry.COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ InventoryContract.InventoryEntry.COLUMN_NAME
                + " TEXT NOT NULL, "+ InventoryContract.InventoryEntry.COLUMN_IMAGE+" TEXT, "+ InventoryContract.InventoryEntry.COLUMN_INSTOCK+" INTEGER NOT NULL DEFAULT 0, "+ InventoryContract.InventoryEntry.COLUMN_SOLD
                + " INTEGER NOT NULL DEFAULT 0, " + InventoryContract.InventoryEntry.COLUMN_SUPPLIER + " TEXT, "+
                InventoryContract.InventoryEntry.COLUMN_PRICE + " REAL NOT NULL"+")";

        Log.v(LOG_CAT,SQL_CREATE_INVENTORY_TABLE);
        Log.v(LOG_CAT,"igiohjjkljuihjkknihkjnkjnnm");
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required
    }
}
