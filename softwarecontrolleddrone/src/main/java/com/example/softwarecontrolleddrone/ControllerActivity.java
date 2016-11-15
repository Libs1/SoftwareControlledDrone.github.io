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
import android.location.Address;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ControllerActivity extends AppCompatActivity {

    MySQLiteHelper mySQLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    TextView textStart, textStop;
    ImageView drone_pic;
    Boolean running;

    public static final String PREFS = "sharedPreferences";
    public static final String BRIGHTNESS = "brightness";

    public static Button b;
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


        drone_pic = (ImageView) findViewById(R.id.dronePicture2);


        java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        textStart = (TextView) findViewById(R.id.textStart);
        textStop = (TextView) findViewById(R.id.textStop);
        textStart.setVisibility(View.INVISIBLE);


        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ControllerActivity.this, PopActivity.class));
            }
        });


        ImageView upButton = (ImageView) findViewById(R.id.upArrow);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView bluedrone1 = (ImageView) findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim_up);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView downButton = (ImageView) findViewById(R.id.downArrow);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView bluedrone1 = (ImageView) findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim_down);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView leftButton = (ImageView) findViewById(R.id.leftArrow);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView bluedrone1 = (ImageView) findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim_left);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView rightButton = (ImageView) findViewById(R.id.rightArrow);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView bluedrone1 = (ImageView) findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim_right);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView leftRotate = (ImageView) findViewById(R.id.rotateLeft);
        leftRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView bluedrone1 = (ImageView) findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_rotate_left);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        ImageView rightRotate = (ImageView) findViewById(R.id.rotateRight);
        rightRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView bluedrone1 = (ImageView) findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_rotate_right);
                bluedrone1.startAnimation(jumpAnimation);
            }
        });

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        timeText = (TextView) findViewById(R.id.timeDisplayed);
        chronometer.setVisibility(View.INVISIBLE);

        timeSwitch = (Switch) findViewById(R.id.timeSwitch);
        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    running = true;
                    chronometer.setVisibility(View.VISIBLE);
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    timeText.setVisibility(View.INVISIBLE);
                    timeText.setText(R.string.zeroseconds);
                    chronometer.start();
                    textStart.setVisibility(View.VISIBLE);
                    textStop.setVisibility(View.INVISIBLE);


                    //Animation setup with handler
                    final int[] imageArray = {R.drawable.drone, R.drawable.drone_90,
                            R.drawable.drone_180, R.drawable.drone_270,
                    };

                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        int i = 0;

                        public void run() {
                            if (running) {
                                drone_pic.setImageResource(imageArray[i]);
                                i++;
                                if (i > imageArray.length - 1) {
                                    i = 0;
                                }
                                handler.postDelayed(this, 50);
                            }
                        }
                    };
                    handler.postDelayed(runnable, 50);
                } else {
                    running = false;
                    drone_pic.setImageResource(R.drawable.drone);
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    int seconds = (int) timeWhenStopped / 1000;
                    timeText.setVisibility(View.VISIBLE);
                    chronometer.setVisibility(View.INVISIBLE);
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
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
