package com.example.roxan.androidlab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class ChatWindow extends Activity {
    ListView listView;
    EditText message;
    Button sendButton;
    ArrayList<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView=findViewById(R.id.list);
        message=findViewById(R.id.message);

        final ChatAdapter messageAdapter =new ChatAdapter( this );
        listView.setAdapter (messageAdapter);


        sendButton=findViewById(R.id.button6);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(message.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount() & getView()
                message.setText("");
            }
        });
    }
    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter( Context context) {
            super(context, 0);
        }
        //retutn a number of rows that will be in my ListView
        public int getCount(){
            return list.size();
        }
        //return the item to show in the list at the specified position
        public String getItem(int position){
            return list.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  ); // get the string at position
            return result;


        }
        public long getId(int position){
            return position;


        }
    }
}
