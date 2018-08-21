package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.inventoryapp.Data.InventoryContract;
import com.example.android.inventoryapp.Data.InventoryCursorAdapter;
import com.example.android.inventoryapp.Data.InventoryDbHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.android.inventoryapp.Data.InventoryProvider.inventoryDbHelper;

public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String LOG_CAT=CatalogActivity.class.getSimpleName();
    private ListView listView;
    private static final int INVENTORY_LOADER=1;
    private static InventoryCursorAdapter inventoryCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        setTitle(R.string.category_activity_title);
        listView=(ListView)findViewById(R.id.list);
        View emptyView=findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);

        inventoryCursorAdapter=new InventoryCursorAdapter(this,null);
        listView.setAdapter(inventoryCursorAdapter);

        getLoaderManager().initLoader(INVENTORY_LOADER,null,this).forceLoad();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri= ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI,id);
                Intent intent=new Intent(CatalogActivity.this,EditorActivity.class);
                intent.setData(uri);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.catalog_insert:
                Intent intent=new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri uri= InventoryContract.InventoryEntry.CONTENT_URI;
        String[] projection={
                InventoryContract.InventoryEntry.COLUMN_ID,
                InventoryContract.InventoryEntry.COLUMN_NAME,
                InventoryContract.InventoryEntry.COLUMN_INSTOCK,
                InventoryContract.InventoryEntry.COLUMN_SOLD,
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER,
                InventoryContract.InventoryEntry.COLUMN_PRICE
        };
        return new CursorLoader(this,uri,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor data) {
        //data.moveToFirst();
        inventoryCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        inventoryCursorAdapter.swapCursor(null);
    }
}
