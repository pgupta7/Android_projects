package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.Data.InventoryContract;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String LOG_CQT=EditorActivity.class.getSimpleName();
    private static final int INVENTORY_LOADER=1;
    @BindView(R.id.inventory_name_editText) EditText inventoryName;
    @BindView(R.id.inventory_stock_editText) TextView inventoryStock;
    @BindView(R.id.inventory_sold_editText) TextView inventorySold;
    @BindView(R.id.inventory_supplier_editText) EditText inventorySupplier;
    @BindView(R.id.inventory_price_editText) EditText inventoryPrice;
    @BindView(R.id.up_button_for_stock_items) Button stockUpButton;
    @BindView(R.id.down_button_for_stock_items) Button stockDownButton;
    @BindView(R.id.up_button_for_sold_items) Button soldUpButton;
    @BindView(R.id.down_button_for_sold_items) Button soldDownButton;
    @BindView(R.id.inventory_image) ImageView inventoryImage;
    @BindView(R.id.add_image_button) Button addInventoryImageButton;
    private Uri imageUri;

    private Uri uri;
    private Boolean mInventoryHasChanged=false;
    private View.OnTouchListener mTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mInventoryHasChanged=true;
            return false;
        }
    };

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_IMAGE_REQUEST = 0;
    public String mCurrentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        ButterKnife.bind(this);


        inventoryName.setOnTouchListener(mTouchListener);
        inventorySupplier.setOnTouchListener(mTouchListener);
        inventoryPrice.setOnTouchListener(mTouchListener);
        stockUpButton.setOnTouchListener(mTouchListener);
        stockDownButton.setOnTouchListener(mTouchListener);
        soldUpButton.setOnTouchListener(mTouchListener);
        soldDownButton.setOnTouchListener(mTouchListener);
        addInventoryImageButton.setOnTouchListener(mTouchListener);



        uri=getIntent().getData();

        if(uri!=null){
            setTitle(R.string.update_inventory_title);
            addInventoryImageButton.setText(R.string.change_the_image_button);
            getLoaderManager().initLoader(INVENTORY_LOADER,null,EditorActivity.this).forceLoad();
        }else {
            setTitle(R.string.add_inventory);
            addInventoryImageButton.setText(R.string.add_an_image_button);
        }
    }


    public void openImageSelector() {
        Intent intent;

        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }

        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageUri=data.getData();
            displayImage(imageUri);
        }
    }

    public void displayImage(Uri uri){
        Bitmap bitmap;
        try{
            int targetW = inventoryImage.getWidth();
            int targetH = inventoryImage.getHeight();
            InputStream inputStream=getContentResolver().openInputStream(uri);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            //BitmapFactory.decodeStream(inputStream, null, bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bitmap= BitmapFactory.decodeStream(inputStream,null,bmOptions);

            inventoryImage.setImageBitmap(bitmap);
            inputStream.close();
        }catch (Exception e){
            Toast.makeText(this,"unable to get the input stream",Toast.LENGTH_SHORT).show();
        }
    }

    public void addInventoryImageButtonClick(View view){
        openImageSelector();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void stockUpButtonClick(View view){
        int itemStock=Integer.parseInt(inventoryStock.getText().toString().trim());
        itemStock++;
        inventoryStock.setText(Integer.toString(itemStock));
    }

    public void stockDownButtonClick(View view){
        int itemStock=Integer.parseInt(inventoryStock.getText().toString().trim());
        if(itemStock>0){
            itemStock--;
        }
        inventoryStock.setText(Integer.toString(itemStock));
    }

    public void soldUpButtonClick(View view){
        int itemSold=Integer.parseInt(inventorySold.getText().toString().trim());
        int itemStock=Integer.parseInt(inventoryStock.getText().toString().trim());
        if(itemStock>0){
            itemStock--;
            itemSold++;
            inventorySold.setText(Integer.toString(itemSold));
            inventoryStock.setText(Integer.toString(itemStock));
        }
    }

    public void soldDownButtonClick(View view){
        int itemSold=Integer.parseInt(inventorySold.getText().toString().trim());
        int itemStock=Integer.parseInt(inventoryStock.getText().toString().trim());
        if(itemSold>0){
            itemSold--;
            itemStock++;
        }
        inventoryStock.setText(Integer.toString(itemStock));
        inventorySold.setText(Integer.toString(itemSold));
    }



    @Override
    public void onBackPressed() {
        if(!mInventoryHasChanged){
            super.onBackPressed();
            return;
        }else {
            DialogInterface.OnClickListener dialogButtonClickListener=new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            };
            showSaveChangesDialogBox(dialogButtonClickListener);
        }
    }

    public void showSaveChangesDialogBox(DialogInterface.OnClickListener dialogButtonClickListener){
        AlertDialog.Builder builder=new AlertDialog.Builder(EditorActivity.this);
        builder.setMessage(R.string.discard_changes_message);
        builder.setNegativeButton(R.string.negative_button_message,dialogButtonClickListener);
        builder.setPositiveButton(R.string.positive_button_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog!=null){
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    public void insertdata(){
        String itemName=inventoryName.getText().toString().trim();
        String itemSupplier=inventorySupplier.getText().toString().trim();
        Uri mUri;
        String itemImageUri;

        if(itemName.isEmpty()){
            Toast.makeText(EditorActivity.this,R.string.enter_inventory_name,Toast.LENGTH_SHORT).show();
            return;
        }

        if(imageUri==null){
            Toast.makeText(EditorActivity.this,R.string.editor_activity_select_a_valid_image,Toast.LENGTH_SHORT).show();
            return;
        }else {
            itemImageUri=imageUri.toString();
            if(itemImageUri.isEmpty()){
                Toast.makeText(EditorActivity.this,R.string.editor_activity_select_a_valid_image,Toast.LENGTH_SHORT).show();
                return;
            }
        }

        double itemPrice;
        try{
            itemPrice=Double.parseDouble(inventoryPrice.getText().toString().trim());
        }catch (NumberFormatException e){
            Toast.makeText(EditorActivity.this,R.string.editor_activity_enter_a_valid_price,Toast.LENGTH_SHORT).show();
            return;
        }

        int itemStock;
        try{
            itemStock=Integer.parseInt(inventoryStock.getText().toString().trim());
        }catch (NumberFormatException e){
            Toast.makeText(EditorActivity.this,R.string.editor_activity_enter_a_valid_stock,Toast.LENGTH_SHORT).show();
            return;
        }

        int itemSold;
        try{
            itemSold=Integer.parseInt(inventorySold.getText().toString().trim());
        }catch (NumberFormatException e){
            Toast.makeText(EditorActivity.this,R.string.editor_activity_enter_a_valid_sold_number,Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues=new ContentValues();
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_NAME,itemName);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_IMAGE,itemImageUri);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_INSTOCK,itemStock);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_SOLD,itemSold);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_SUPPLIER,itemSupplier);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_PRICE,itemPrice);

        if(uri==null){
            mUri=getContentResolver().insert(InventoryContract.InventoryEntry.CONTENT_URI,contentValues);
            if(mUri==null){
                Toast.makeText(EditorActivity.this,R.string.insert_failed,Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EditorActivity.this,R.string.insert_successful,Toast.LENGTH_SHORT).show();
            }
        } else {
            int id=-1;
            id=getContentResolver().update(uri,contentValues,null,null);
            if(id==-1){
                Toast.makeText(EditorActivity.this,R.string.update_failed,Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EditorActivity.this,R.string.update_successful,Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    public void deleteData(){
        DialogInterface.OnClickListener dialogButtonClickListener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int numRowsDeleted=getContentResolver().delete(uri,null,null);
                if(numRowsDeleted!=0){
                    Toast.makeText(EditorActivity.this,R.string.delete_successful,Toast.LENGTH_SHORT).show();
                    Log.v(LOG_CQT,"Row has been deleted");
                }else {
                    Toast.makeText(EditorActivity.this,R.string.delete_failed,Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        };
        deleteConfirmationDialogBox(dialogButtonClickListener);
    }
    public void deleteConfirmationDialogBox(DialogInterface.OnClickListener dialogButtonClickListener){
        AlertDialog.Builder builder=new AlertDialog.Builder(EditorActivity.this);
        builder.setMessage(R.string.delete_inventory_message);
        builder.setNegativeButton(R.string.delete_inventory_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog!=null){
                    dialog.dismiss();
                }
            }
        });
        builder.setPositiveButton(R.string.delete_inventory_positive_button,dialogButtonClickListener);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor,menu);
        return true;
    }

    public void orderInventory(View view){
        String[] supplierAddress={inventorySupplier.getText().toString()};
        if((supplierAddress!=null)){
            Intent intent=new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL,supplierAddress);
            Log.v(LOG_CQT,"trying to email");
            view.getContext().startActivity(intent);
        }else {
            Toast.makeText(view.getContext(),R.string.invalid_email,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.editor_menu_save:
                insertdata();
                break;
            case R.id.editor_menu_delete:
                deleteData();
                break;
            case android.R.id.home:
                if(!mInventoryHasChanged){
                    finish();
                }else {
                    DialogInterface.OnClickListener dialogButtonClickListener=new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    };
                    showSaveChangesDialogBox(dialogButtonClickListener);
                }
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(EditorActivity.this,uri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor.moveToFirst()){
            Log.v(LOG_CQT,"cursor size is"+cursor.getColumnCount());
            String inventoryNameText=cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME));
            inventoryName.setText(inventoryNameText);

            String inventoryImageUri=cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_IMAGE));
            if((inventoryImageUri!=null)&&(!inventoryImageUri.isEmpty())){
                displayImage(Uri.parse(inventoryImageUri));
                imageUri=Uri.parse(inventoryImageUri);
            } else {
                addInventoryImageButton.setText(R.string.add_an_image_button);
            }

            String inventoryStockText=Integer.toString(cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INSTOCK)));
            inventoryStock.setText(inventoryStockText);
            inventorySold.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SOLD))));
            String inventorySupplierText=cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SUPPLIER));
            inventorySupplier.setText(inventorySupplierText);
            inventoryPrice.setText(Double.toString( cursor.getDouble(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE))));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        inventoryName.setText("");
        inventorySold.setText("");
        inventoryStock.setText("");
        inventorySupplier.setText("");
        inventoryPrice.setText("");
        inventoryImage.setImageBitmap(null);
    }
}
