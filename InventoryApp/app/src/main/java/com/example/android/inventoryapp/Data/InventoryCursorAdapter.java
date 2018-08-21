package com.example.android.inventoryapp.Data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.inventoryapp.R;

/**
 * Created by Prasoon Gupta on 1/22/2017.
 */

public class InventoryCursorAdapter extends CursorAdapter {
    public InventoryCursorAdapter(Context context, Cursor cursor){
        super(context,cursor,0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView inventoryName=(TextView) view.findViewById(R.id.inventoryName);
        TextView inventoryStock=(TextView) view.findViewById(R.id.inventoryStock);
        TextView inventoryPrice=(TextView)view.findViewById(R.id.inventoryPrice);
        TextView inventorySold=(TextView)view.findViewById(R.id.inventorySold);
        if(!cursor.isClosed()){
            inventoryName.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME)));
            inventoryStock.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INSTOCK)));
            inventoryPrice.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE)));
            inventorySold.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SOLD)));
        }
    }
}
