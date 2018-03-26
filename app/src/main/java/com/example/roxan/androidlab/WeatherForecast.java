package com.example.roxan.androidlab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class WeatherForecast extends Activity {


    ProgressBar progressBar;
    ImageView WeatherImageView;
    TextView CurrentTempView;
    TextView MinTempView;
    TextView MaxTempView;
    TextView WindSpeedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        WeatherImageView = (ImageView) findViewById(R.id.imageView3);
        CurrentTempView = (TextView) findViewById(R.id.currentTemperature);
        MinTempView = (TextView) findViewById(R.id.minTemperature);
        MaxTempView = (TextView) findViewById(R.id.maxTemperature);
        WindSpeedView = (TextView) findViewById(R.id.windSpeed);

        new ForecastQuery().execute("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
    }

    public class ForecastQuery extends AsyncTask<String, Integer, String> {

        String CurrentTemperature;
        String MaxTemperature;
        String MinTemperature;
        String speed;
        Bitmap WeatherImage;

        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {
            InputStream input = null;

            try {
                String urlString = strings[0];
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                input = conn.getInputStream();


                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(input, "UTF-8");
                int eventType = parser.getEventType();


                while (parser.next() != XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType()  != XmlPullParser.START_TAG){
                        continue;
                    }

                    if (parser.getName().equals("temperature")) {
                        CurrentTemperature = parser.getAttributeValue(null, "value");
                        publishProgress(25);
                        Log.i("temperature", CurrentTemperature);
                        MaxTemperature = parser.getAttributeValue(null, "max");
                        publishProgress(50);
                        Log.i("max Temperature",MaxTemperature );
                        MinTemperature = parser.getAttributeValue(null, "min");
                        publishProgress(75);
                        Log.i("min Temperature",MinTemperature );


                    }

                    if (parser.getName().equals("speed")) {
                        speed = parser.getAttributeValue(null, "value");
                        Log.i("wind speed",speed );

                    }
                    if (parser.getName().equals("weather")) {

                        String iconName = parser.getAttributeValue(null, "icon");
                        String imagefile = iconName + ".png";
                        Log.i("Looking for image file", "File Name:" + imagefile);
                        if (fileExistance(imagefile)) {
                            Log.i("File exsits", imagefile);
                            FileInputStream fis = null;
                            try {
                                fis = new FileInputStream(getBaseContext().getFileStreamPath(imagefile));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            WeatherImage = BitmapFactory.decodeStream(fis);
                        } else {
                            Log.i("File doesn't exist", imagefile);
                            URL imageUrl = new URL("http://openweathermap.org/img/w/" + iconName + ".png");
                            WeatherImage = getImage(imageUrl);
                            FileOutputStream outputStream = openFileOutput(iconName + ".png", MODE_PRIVATE);
                            WeatherImage.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                            outputStream.flush();
                            outputStream.close();
                        }
                        publishProgress(100);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } finally {
                if(input!=null)
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return null;
        }

        public Bitmap getImage(URL url) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    return BitmapFactory.decodeStream(connection.getInputStream());
                } else
                    return null;
            } catch (Exception e) {
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        public boolean fileExistance(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            if(file.exists()){
                Log.i("File Path:",file.getAbsolutePath());
            }
            return file.exists();   }

        @Override
        protected void onProgressUpdate(Integer ... value){
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(value[0]);
        }


        @Override
        protected void onPostExecute(String s) {
            CurrentTempView.setText(CurrentTempView.getText()+ CurrentTemperature + "C");
            MaxTempView.setText(" " +MaxTempView.getText() + MaxTemperature +  "C" );
            MinTempView.setText(" " +MinTempView.getText() +  MinTemperature + "C");
            WindSpeedView.setText(" " +WindSpeedView.getText() + speed );
            WeatherImageView.setImageBitmap(WeatherImage);
            progressBar.setVisibility(View.INVISIBLE);

        }

    }


}


