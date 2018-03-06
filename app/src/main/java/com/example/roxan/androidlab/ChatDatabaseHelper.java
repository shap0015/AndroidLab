package com.example.roxan.androidlab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by roxan on 2018-02-28.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME = "Messages.db";
    static int VERSION_NUM = 1;
    static  String TABLE_NAME = "messages";
    final static String KEY_ID="id";
    final static String KEY_MESSAGE = "message";

    public ChatDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sqlCreate = "CREATE TABLE " + TABLE_NAME + "( "+ KEY_ID +" integer primary key autoincrement, "+ KEY_MESSAGE +" );";
        Log.i("SQLCREATE", sqlCreate);
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
