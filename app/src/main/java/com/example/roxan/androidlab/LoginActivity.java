package com.example.roxan.androidlab;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;
import android.content.Intent;

public class LoginActivity extends Activity {
    SharedPreferences pref;
    EditText emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final String ACTIVITY_NAME = "LoginActivity";
        Log.i(ACTIVITY_NAME, "In onCteate()");
        pref = getSharedPreferences("com.example.roxan.androidlab_PrefernceFile",MODE_PRIVATE);


        Button login = (Button)findViewById(R.id.button2);
        final EditText emailAddress =(EditText)findViewById(R.id.textView3);
        emailAddress.setText(pref.getString("DefaultEmail","email@domain.com" ));


        String user = pref.getString("DefaultEmail”, ", "email@domain.com");
        Log.i(ACTIVITY_NAME,user);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              storeEmail();
              emailAddress.setText(pref.getString("DefaultEmail","email@domain.com" ));
                try {
                    Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                    startActivity(intent);
                } catch (NullPointerException npe)

                {


                }

            }

        });
    }
    protected void storeEmail(){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("DefaultEmail","email@.com");
        editor.commit();
        Log.i("LoginActivity","Email was stored succesfully!");

    }
    protected void onResume() {
        super.onResume();
        final String ACTIVITY_NAME = "LoginActivity";
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onStart() {
        super.onStart();
        final String ACTIVITY_NAME = "LoginActivity";
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onPause() {
        super.onPause();
        final String ACTIVITY_NAME = "LoginActivity";
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop() {
        super.onStop();
        final String ACTIVITY_NAME = "LoginActivity";
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        final String ACTIVITY_NAME = "LoginActivity";
        Log.i(ACTIVITY_NAME, "In onDestroy)");

    }

}
