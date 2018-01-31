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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final String ACTIVITY_NAME = "LoginActivity";
        Log.i(ACTIVITY_NAME, "In onCteate()");


        Button login = (Button)findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {

            SharedPreferences pref=getSharedPreferences("com.example.roxan.androidlab.loginActivity",MODE_PRIVATE);
            Editor editor=pref.edit();
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                    startActivity(intent);
                }
                catch (NullPointerException npe)

                {


                }

            }

        });
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
