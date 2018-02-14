package com.example.roxan.androidlab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static junit.runner.BaseTestRunner.savePreferences;
public class LoginActivity extends Activity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    SharedPreferences pref;
    EditText emailAddress;
    Button button2;
    protected static final String EMAIL = "emailKey";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        emailAddress = (EditText)findViewById(R.id.textView3) ;

        pref = getSharedPreferences("Name_File", Context.MODE_PRIVATE);
        emailAddress.setText(pref.getString(EMAIL, "email@domain.com"));


       button2 = findViewById(R.id.button2);
   button2.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View View){
               storeEmail();
               Intent intent = new Intent(LoginActivity.this, StartActivity.class);
               startActivity(intent);
           }
     } );

    }

    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart()");
    }
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume()");
    }



    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause()");
    }

    protected void onStop(){
        super.onStop();

        Log.i(ACTIVITY_NAME,"In onStop()");

    }



    protected void 	onDestroy(){

        super.onDestroy();

        Log.i(ACTIVITY_NAME,"In onDestroy()");

    }


    protected void storeEmail() {
        Log.i(ACTIVITY_NAME,"In savePreferences");
        String saveUserName = emailAddress.getText().toString();
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(EMAIL,saveUserName);
        editor.commit();

    }










}