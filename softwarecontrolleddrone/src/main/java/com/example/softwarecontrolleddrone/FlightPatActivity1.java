package com.example.softwarecontrolleddrone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;


public class FlightPatActivity1 extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_pat1);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Button startButton = (Button)findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)

            {
                /*db = SQLiteDatabase.openDatabase("u940148205_dfp", null, Context.MODE_PRIVATE);

                c = db.rawQuery("SELECT flightpath FROM DroneFlight", null);

                String f_pattern = c.getString(1);

                Toast.makeText(FlightPatActivity1.this, f_pattern, Toast.LENGTH_SHORT)
                        .show();*/

                /*String result = "";

                InputStream isr = null;

                try{

                    HttpClient httpclient = new DefaultHttpClient();

                    HttpPost httppost = new HttpPost("http://ieeehiit.host22.com/myfile.php"); //YOUR PHP SCRIPT ADDRESS

// HttpPost httppost = new HttpPost("http://172.23.193.32/elift-test/myfile.php"); //YOUR PHP SCRIPT ADDRESS

                    HttpResponse response = httpclient.execute(httppost);

                    HttpEntity entity = response.getEntity();

                    isr = entity.getContent();

                }

                catch(Exception e){

                    Log.e("log_tag", "Error in http connection "+e.toString());

                    resultView.setText("Couldnt connect to database");

                }*/

                String login_url = "http://softwarecontrolleddrone.esy.es/p1.php";
                String result;


                try {
                    URL url = new URL(login_url);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    InputStream inputStream = httpURLConnection.getInputStream();
                    //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

//convert response to string

                    try {

                        //BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);

                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                        StringBuilder sb = new StringBuilder();

                        String line = null;

                        while ((line = bufferedReader.readLine()) != null) {

                            sb.append(line + "\n");

                        }

                        inputStream.close();

                        result = sb.toString();

                        JSONArray jArray = new JSONArray(result);
                        JSONObject json_data = null;

                        json_data = jArray.getJSONObject(0);

                        String patn = json_data.getString("flightpath");

                        Toast.makeText(FlightPatActivity1.this, patn, Toast.LENGTH_SHORT)
                                .show();

                    } catch (Exception e) {


                    }

                    ImageView bluedrone = (ImageView) findViewById(R.id.dronePicture2);

                    Animation jumpAnimation = AnimationUtils.loadAnimation(FlightPatActivity1.this, R.anim.drone_pat_1);

                    bluedrone.startAnimation(jumpAnimation);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            });


        Button stopButton = (Button)findViewById(R.id.stop);
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ImageView bluedrone = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(FlightPatActivity1.this, R.anim.drone_anim_end);

                bluedrone.startAnimation(jumpAnimation);

            }
        });
    }
    }

