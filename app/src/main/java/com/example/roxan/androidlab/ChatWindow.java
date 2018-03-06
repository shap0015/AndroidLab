package com.example.roxan.androidlab;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.database.Cursor;
;

import static com.example.roxan.androidlab.StartActivity.ACTIVITY_NAME;

public class ChatWindow extends Activity {

    ListView listView;
    EditText message;
    Button sendButton;
    ArrayList<String> list=new ArrayList<>();
    SQLiteDatabase sqlDB;
    ChatDatabaseHelper  dhHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView=findViewById(R.id.list_scroll);

        //new lab stuff
        dhHelper = new ChatDatabaseHelper(this);
        sqlDB = dhHelper.getWritableDatabase();

        Cursor resultsCursor = sqlDB.rawQuery("select * from "+ChatDatabaseHelper.TABLE_NAME, new String[]{});
        resultsCursor.moveToFirst();
        while(!resultsCursor.isAfterLast() ){
            int countColumns = resultsCursor.getColumnCount();
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + countColumns );
            for (int i = 0; i < countColumns; i++){
                Log.i(ACTIVITY_NAME, "Column " + i +": " + resultsCursor.getColumnName(i));
            }
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + resultsCursor.getString( resultsCursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE) ) );
            list.add(resultsCursor.getString( resultsCursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE) ));
            resultsCursor.moveToNext();
        }






        message=findViewById(R.id.message);
        final ChatAdapter messageAdapter =new ChatAdapter( this );
        listView.setAdapter (messageAdapter);

        sendButton = findViewById(R.id.button6);
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("MESSAGE", message.getText().toString());
                String msg =  message.getText().toString().trim();
                if(!msg.equals("")){
                    ContentValues contentValue = new ContentValues();
                    contentValue.put(ChatDatabaseHelper.KEY_MESSAGE, msg);

                    sqlDB.insert(ChatDatabaseHelper.TABLE_NAME, "", contentValue);
                    list.add(msg);
                    Log.i("MESSAGELIST", ""+list.size());
                    messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount() & getView()
                    message.setText("");
                }

            }

        });

    }

    private void onCreate(SQLiteDatabase sqLiteDatabase){




        // while(!cursor.isAfterLast() )
        // Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString( cursor.getColumnIndex( com.example.roxan.androidlab.ChatDatabaseHelper.KEY_MESSAGE))
            //);
        //Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount() );

    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context context) {
            super(context, 0);

        }

        //retutn a number of rows that will be in my ListView
        public int getCount() {
            return list.size();

        }

        //return the item to show in the list at the specified position

        public String getItem(int position) {
            return list.get(position);

        }



        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            TextView dialog;
            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
                dialog = (TextView) result.findViewById(R.id.textView4);

            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
                dialog = (TextView) result.findViewById(R.id.message_text);

                //TextView message = (TextView) result.findViewById(R.id.message_text);
            }
            Log.i("MESSAGEPOSITION", getItem(position));
            dialog.setText(getItem(position)); // get the string at position
            return result;
        }
    }

}

