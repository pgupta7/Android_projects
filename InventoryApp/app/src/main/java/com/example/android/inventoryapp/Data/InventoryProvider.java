package com.example.android.inventoryapp.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.android.inventoryapp.R;

/**
 * Created by Prasoon Gupta on 1/21/2017.
 */

public class InventoryProvider extends ContentProvider{
    private static final String LOG_CAT=InventoryProvider.class.getSimpleName();
    public static InventoryDbHelper inventoryDbHelper;
    private static final int INVENTORY_ID1=1;
    private static final int INVENTORY_ID2=2;
    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    private Resources resources;

    static {
        /*
         * The calls to addURI() go here, for all of the content URI patterns that the provider
         * should recognize. For this snippet, only the calls for table 3 are shown.
         */

        /*
         * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
         * in the path
         */
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.INVENTORY_PATH, INVENTORY_ID1);

        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/table3/3" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         */
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.INVENTORY_PATH2, INVENTORY_ID2);
    }

    @Override
    public boolean onCreate() {
        Log.v(LOG_CAT,"inside the oncreate method");
        inventoryDbHelper=new InventoryDbHelper(getContext());
        resources=getContext().getResources();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sqLiteDatabase=inventoryDbHelper.getReadableDatabase();
        Cursor cursor;
        int match=sUriMatcher.match(uri);
        switch (match){
            case INVENTORY_ID1:
                cursor=sqLiteDatabase.query(InventoryContract.InventoryEntry.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case INVENTORY_ID2:
                selection= InventoryContract.InventoryEntry.COLUMN_ID + "=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=sqLiteDatabase.query(InventoryContract.InventoryEntry.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException(resources.getString(R.string.unknown_uri_query)+ uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        Log.v(LOG_CAT,uri.toString());
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }



    //@Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.v(LOG_CAT,"inside the insert method");
        int match=sUriMatcher.match(uri);
        switch (match){
            case INVENTORY_ID1:
                return insertInvetory(uri,values);
            default:
                throw new IllegalArgumentException("");
        }
    }

    private Uri insertInvetory(Uri uri, ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase=inventoryDbHelper.getWritableDatabase();
        Log.v(LOG_CAT,"inside insertinventory method");
        if(contentValues.get(InventoryContract.InventoryEntry.COLUMN_NAME)==null){
            throw new IllegalArgumentException(resources.getString(R.string.inventory_requires_a_name));
        }
        if(contentValues.get(InventoryContract.InventoryEntry.COLUMN_IMAGE)==null){
            throw new IllegalArgumentException(resources.getString(R.string.editor_activity_select_a_valid_image));
        }
        if(contentValues.get(InventoryContract.InventoryEntry.COLUMN_SOLD)==null){
            throw new IllegalArgumentException(resources.getString(R.string.inventory_sold_item_requires_a_value));
        }
        if(contentValues.get(InventoryContract.InventoryEntry.COLUMN_INSTOCK)==null){
            throw new IllegalArgumentException(resources.getString(R.string.inventory_stock_requires_a_value));
        }
        if(contentValues.get(InventoryContract.InventoryEntry.COLUMN_SUPPLIER)==null){
            throw new IllegalArgumentException(resources.getString(R.string.inventory_supplier_requires_a_value));
        }
        long id=sqLiteDatabase.insert(InventoryContract.InventoryEntry.TABLE_NAME,null,contentValues);

        if(id==-1){
            sqLiteDatabase.close();
            throw new IllegalArgumentException(resources.getString(R.string.insert_operation_was_not_successful));
        }

        getContext().getContentResolver().notifyChange(uri,null);
        sqLiteDatabase.close();

        return ContentUris.withAppendedId(uri,id);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase=inventoryDbHelper.getWritableDatabase();
        int match=sUriMatcher.match(uri);
        int numRowsUpdated=0;
        switch (match){
            case INVENTORY_ID1:
                numRowsUpdated=sqLiteDatabase.update(InventoryContract.InventoryEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            case INVENTORY_ID2:
                selection= InventoryContract.InventoryEntry.COLUMN_ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                numRowsUpdated=sqLiteDatabase.update(InventoryContract.InventoryEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(resources.getString(R.string.update_not_spoorted)+"\t"+uri.toString());
        }

        if(numRowsUpdated!=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        sqLiteDatabase.close();
        return numRowsUpdated;
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v(LOG_CAT,"inside delete method");
        SQLiteDatabase sqLiteDatabase=inventoryDbHelper.getWritableDatabase();
        int match=sUriMatcher.match(uri);
        int numRowsDeleted=0;
        switch (match){
            case INVENTORY_ID1:
                numRowsDeleted=sqLiteDatabase.delete(InventoryContract.InventoryEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case INVENTORY_ID2:
                selection= InventoryContract.InventoryEntry.COLUMN_ID+"=?";
                selectionArgs=new String []{String.valueOf(ContentUris.parseId(uri))};
                Log.v(LOG_CAT,"under right case");
                numRowsDeleted=sqLiteDatabase.delete(InventoryContract.InventoryEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(resources.getString(R.string.delete_not_supported)+"\t"+uri.toString());
        }
        if(numRowsDeleted!=0){
            getContext().getContentResolver().notifyChange(uri,null);
            Log.v(LOG_CAT,"notifying the cursor"+ numRowsDeleted);
        }
        sqLiteDatabase.close();
        return numRowsDeleted;
    }
}
