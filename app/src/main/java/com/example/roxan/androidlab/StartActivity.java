package com.example.roxan.androidlab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

    protected static final String ACTIVITY_NAME = "StartActivity";
    Button button;
    Button startChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.i(ACTIVITY_NAME,"In onCreate()");
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent,50);
            }
        });
        startChatButton= (Button) findViewById(R.id.button3);
        startChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent intent = new Intent (StartActivity.this, ChatWindow.class);
                startActivity(intent);
            }
        });
        Log.i(ACTIVITY_NAME,"In onCreate()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==50){
            Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
        }
        if(resultCode == Activity.RESULT_OK) {
            Log.i(ACTIVITY_NAME, "Result is Ok");

        }

        String messagePassed = data.getStringExtra("Response");
        Toast toast = Toast.makeText(this, messagePassed, Toast.LENGTH_LONG);
        toast.show();

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart()");
    }
    @Override

    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause()");

    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop()");
    }
    @Override
    protected void 	onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy()");
    }



}

