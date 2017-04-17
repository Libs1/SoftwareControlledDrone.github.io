/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class FlightsActivity extends AppCompatActivity {

    MySQLiteHelper mySQLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    ListDataAdapter listDataAdapter;
    ListView listView;
    Button deleteInformationButton;
    String date, flightduration;

    /*SharedPreferences for accessing the FlightsActivity*/
    SharedPreferences accessPreference;
    SharedPreferences.Editor editor;

    /*SharedPreferences for receiving the username*/
    SharedPreferences uNamePreferences;
    SharedPreferences.Editor uNameEditor;

    String recvUsername;


    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        /*SharedPreferences for receiving the username passed from the MenuActivity*/
        uNamePreferences = getSharedPreferences("uNamePrefs", MODE_PRIVATE);
        uNameEditor = uNamePreferences.edit();

        recvUsername = uNamePreferences.getString("namePassToFlights", null);

        //recvUsername = getIntent().getStringExtra("usernamePassedFlights");


        /*SharedPreferences for accessing the flightActivity*/
        accessPreference = getSharedPreferences("accessPrefs", MODE_PRIVATE);
        editor = accessPreference.edit();

        if(getResources().getBoolean(R.bool.portrait_only)){
            setContentView(R.layout.activity_controller);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        listDataAdapter = new ListDataAdapter(context, R.layout.list_view_layout);
        listView = (ListView)findViewById(R.id.FlightInformationList);
        //set the listView to the adapter(listDataAdapter)
        listView.setAdapter(listDataAdapter);

        mySQLiteHelper = new MySQLiteHelper(context);
        sqLiteDatabase = mySQLiteHelper.getReadableDatabase();

        cursor = mySQLiteHelper.getInformation(sqLiteDatabase);

        if(cursor.moveToFirst())
        {
            do{
                date = cursor.getString(0);
                flightduration = cursor.getString(1);

                DataProvider dataProvider = new DataProvider(date, flightduration);

                listDataAdapter.add(dataProvider);

            }while(cursor.moveToNext());
        }

        deleteInformationButton = (Button)findViewById(R.id.deleteInfoButton);
        deleteInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(FlightsActivity.this, MenuActivity.class);

                BackgroundTask backgroundTask = new BackgroundTask();
                backgroundTask.execute(recvUsername);

                editor.putBoolean("check", false);
                editor.commit();

                startActivity(intent);
            }
        });
    }

    class BackgroundTask extends AsyncTask<String, Void, String>
    {
        String link;

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected String doInBackground(String... args)
        {
            link = "http://softwarecontrolleddrone.esy.es/DeleteFlightInfo.php";

            String username;

            username = args[0];

            try
            {
                URL url = new URL(link);

                //Opens connection to the URL
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //Encoded data to be written to the url
                String data_string = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String response = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null)
                {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            if(result != null && result.equalsIgnoreCase("Information Deleted Successfully"))
            {
                mySQLiteHelper = new MySQLiteHelper(context);
                sqLiteDatabase = mySQLiteHelper.getWritableDatabase();

                mySQLiteHelper.deleteInformation(sqLiteDatabase);
                mySQLiteHelper.close();

                Toast.makeText(FlightsActivity.this, result, Toast.LENGTH_SHORT)
                        .show();
            }
            else
            {
                Toast.makeText(FlightsActivity.this, result, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.help:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.ca/"));
                startActivity(intent);
                break;

            case R.id.scd:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.litehawk.ca/highroller/"));
                startActivity(intent);
                break;

            case R.id.drone:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dronestoronto.com/"));
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
