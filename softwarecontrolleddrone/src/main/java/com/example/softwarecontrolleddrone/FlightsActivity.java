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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FlightsActivity extends AppCompatActivity {

    MySQLiteHelper mySQLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    ListDataAdapter listDataAdapter;
    ListView listView;
    Button deleteInformationButton;
    String date, flightduration;

    SharedPreferences accessPreference;
    SharedPreferences.Editor editor;


    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

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
            public void onClick(View v) {

                mySQLiteHelper = new MySQLiteHelper(context);
                sqLiteDatabase = mySQLiteHelper.getWritableDatabase();

                Intent intent = new Intent(FlightsActivity.this, MenuActivity.class);

                mySQLiteHelper.deleteInformation(sqLiteDatabase);
                mySQLiteHelper.close();

                Toast.makeText(FlightsActivity.this, R.string.infoDeletedToast, Toast.LENGTH_SHORT)
                        .show();

                editor.putBoolean("check", false);
                editor.commit();

                startActivity(intent);

            }
        });
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
