/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerActivity extends AppCompatActivity {

    MySQLiteHelper mySQLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;
<<<<<<< HEAD
=======
    TextView textStart,textStop;
>>>>>>> refs/remotes/origin/denis-scd

    public static final String PREFS = "sharedPreferences";
    public static final String BRIGHTNESS = "brightness";

<<<<<<< HEAD
    Button b;
=======
    public static Button b;
>>>>>>> refs/remotes/origin/denis-scd
    boolean switch1;

    Chronometer chronometer;
    Switch timeSwitch;
    TextView timeText, dateText;
    String formattedDate;
    private long timeWhenStopped = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

<<<<<<< HEAD
=======
        textStart = (TextView)findViewById(R.id.textStart);
        textStop = (TextView) findViewById(R.id.textStop);
        textStart.setVisibility(View.INVISIBLE);
>>>>>>> refs/remotes/origin/denis-scd


        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), PopActivity.class), 999);

                //This should be on click in another acitivty
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS, 0);
                switch1 = sharedPreferences.getBoolean("switch", false);
                if(switch1) {
                    b.setBackgroundResource(R.color.white);
                    b.setText(sharedPreferences.getString("status", "On"));
                } else {
                    b.setText(R.string.led_off);
                }
            }
        });

        ImageView upButton = (ImageView)findViewById(R.id.upArrow);
        upButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ImageView bluedrone1 = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim_up);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView downButton = (ImageView)findViewById(R.id.downArrow);
        downButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                ImageView bluedrone1 = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim_down);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView leftButton = (ImageView)findViewById(R.id.leftArrow);
        leftButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ImageView bluedrone1 = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim_left);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView rightButton = (ImageView)findViewById(R.id.rightArrow);
        rightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ImageView bluedrone1 = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim_right);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView leftRotate = (ImageView)findViewById(R.id.rotateLeft);
        leftRotate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ImageView bluedrone1 = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_rotate_left);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView rightRotate = (ImageView)findViewById(R.id.rotateRight);
        rightRotate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ImageView bluedrone1 = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_rotate_right);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });
<<<<<<< HEAD

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        timeText = (TextView)findViewById(R.id.timeDisplayed);

        timeSwitch = (Switch)findViewById(R.id.timeSwitch);
        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    timeText.setVisibility(View.INVISIBLE);
                    timeText.setText(R.string.zeroseconds);
                    chronometer.start();
                }
                else{
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    int seconds = (int) timeWhenStopped / 1000;
                    timeText.setVisibility(View.VISIBLE);
                    timeText.setText(Math.abs(seconds) + " Second(s)");

                    chronometer.stop();

                    String putFlightDuration = timeText.getText().toString();

                    mySQLiteHelper = new MySQLiteHelper(context);
                    sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
                    mySQLiteHelper.putInformation(sqLiteDatabase, formattedDate, putFlightDuration);
                    mySQLiteHelper.close();
=======
>>>>>>> refs/remotes/origin/denis-scd

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        timeText = (TextView)findViewById(R.id.timeDisplayed);

        timeSwitch = (Switch)findViewById(R.id.timeSwitch);
        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    timeText.setVisibility(View.INVISIBLE);
                    timeText.setText(R.string.zeroseconds);
                    chronometer.start();
                    textStart.setVisibility(View.VISIBLE);
                    textStop.setVisibility(View.INVISIBLE);
                }
                else{
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    int seconds = (int) timeWhenStopped / 1000;
                    timeText.setVisibility(View.VISIBLE);
                    timeText.setText(Math.abs(seconds) + " Second(s)");

                    chronometer.stop();

                    String putFlightDuration = timeText.getText().toString();

                    mySQLiteHelper = new MySQLiteHelper(context);
                    sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
                    mySQLiteHelper.putInformation(sqLiteDatabase, formattedDate, putFlightDuration);
                    mySQLiteHelper.close();

                    textStart.setVisibility(View.INVISIBLE);
                    textStop.setVisibility(View.VISIBLE);

                }

<<<<<<< HEAD
                }

=======
>>>>>>> refs/remotes/origin/denis-scd
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.dialogMsg)
                .setMessage(R.string.dialogMsg2)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
