package com.example.roxan.androidlab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ListItemsActivity extends Activity {
    final String ACTIVITY_NAME = "ListItemstActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton image = (ImageButton) findViewById(R.id.imageButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        Log.i(ACTIVITY_NAME, "In onCreate()");

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                }
            }
        });
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
Bundle extras = data.getExtras();
Bitmap imageBitmap = (Bitmap) extras.get("data");
image.setImageBitmap(imageBitmap);
 }
        }
    public void setOnCheckedChanged() {
        CharSequence text = "Switch is On";// "Switch is Off"
        int duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Off

        Toast toast = Toast.makeText(this, text, duration); //this is the ListActivity
        toast.show(); //display your message box


    }
    public void onCheckChanges(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
                .show();

    }

    protected void onResume(){
        super.onResume();

        Log.i(ACTIVITY_NAME, "In onResume()");

    }
    protected void onStart(){
        super.onStart();

        Log.i(ACTIVITY_NAME, "In onStart()");


    }
    protected void onPause(){
        super.onPause();

        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop(){
        super.onStop();

        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();

        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}

