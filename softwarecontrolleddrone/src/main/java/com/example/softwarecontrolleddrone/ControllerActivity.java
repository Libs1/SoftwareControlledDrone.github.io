/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.icu.util.Output;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
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
import java.text.SimpleDateFormat;
import java.util.List;
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
    TextView timeText;
    String putFlightDuration;
    String formattedDate;
    private long timeWhenStopped = 0;
    SharedPreferences accessPreference;
    SharedPreferences.Editor editor;
    boolean check2;

    /*Variables for the Joysticks*/
    RelativeLayout layout_joystick1, layout_joystick2;
    TextView xValueLeft, yValueLeft, xValueRight, yValueRight;
    JoyStickClass leftJoystick, rightJoystick;
    int xleftDefault = 0;
    int yleftDefault = 0;
    int xrightDefault = 0;
    int yrightDefault = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        accessPreference = getSharedPreferences("accessPrefs", MODE_PRIVATE);
        editor = accessPreference.edit();

        if(getResources().getBoolean(R.bool.portrait_only)){
            setContentView(R.layout.activity_controller);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        //drone_pic = (ImageView) findViewById(R.id.dronePicture2);

        java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        textStart = (TextView) findViewById(R.id.textStart);
        textStop = (TextView) findViewById(R.id.textStop);
        textStart.setVisibility(View.INVISIBLE);


        //LED Button
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ControllerActivity.this, PopActivity.class));
            }
        });


        /*TextViews for the X and Y values for both joysticks*/
        xValueLeft = (TextView)findViewById(R.id.xValueLeft);
        yValueLeft = (TextView)findViewById(R.id.yValueLeft);
        xValueRight = (TextView)findViewById(R.id.xValueRight);
        yValueRight = (TextView)findViewById(R.id.yValueRight);

        layout_joystick1 = (RelativeLayout)findViewById(R.id.layout_joystick1);
        layout_joystick2 = (RelativeLayout)findViewById(R.id.layout_joystick2);

        leftJoystick = new JoyStickClass(getApplicationContext(), layout_joystick1, R.drawable.image_button);
        leftJoystick.setStickSize(80, 80);
        leftJoystick.setLayoutAlpha(150);
        leftJoystick.setStickAlpha(100);
        leftJoystick.setOffset(90);
        leftJoystick.setMinimumDistance(50);

        layout_joystick1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                leftJoystick.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN || arg1.getAction() == MotionEvent.ACTION_MOVE)
                {
                    xValueLeft.setText("X: " + String.valueOf(leftJoystick.getX()));
                    yValueLeft.setText("Y: " + String.valueOf(leftJoystick.getY()));
                }
                else if(arg1.getAction() == MotionEvent.ACTION_UP)
                {
                    xValueLeft.setText("X: " + 0);
                    yValueLeft.setText("Y: " + 0);
                }
                return true;
            }
        });


        rightJoystick = new JoyStickClass(getApplicationContext(), layout_joystick2, R.drawable.image_button);
        rightJoystick.setStickSize(80, 80);
        rightJoystick.setLayoutAlpha(150);
        rightJoystick.setStickAlpha(100);
        rightJoystick.setOffset(90);
        rightJoystick.setMinimumDistance(50);

        layout_joystick2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                rightJoystick.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN || arg1.getAction() == MotionEvent.ACTION_MOVE)
                {
                    xValueRight.setText("X:" + String.valueOf(rightJoystick.getX()));
                    yValueRight.setText("Y:" + String.valueOf(rightJoystick.getY()));
                }
                else if(arg1.getAction() == MotionEvent.ACTION_UP)
                {
                    xValueRight.setText("X:" + 0);
                    yValueLeft.setText("Y:" + 0);
                }

                return true;
            }
        });




/*
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
     */

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        timeText = (TextView) findViewById(R.id.timeDisplayed);
        chronometer.setVisibility(View.INVISIBLE);

        timeSwitch = (Switch) findViewById(R.id.timeSwitch);
        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    editor.putBoolean("check", true);
                    editor.commit();

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
                    /*Runnable runnable = new Runnable() {
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
                    };*/
                    //handler.postDelayed(runnable, 50);
                }
                else
                {
                    running = false;
                    //drone_pic.setImageResource(R.drawable.drone);
                    //getbase returns the base time
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    int seconds = (int) timeWhenStopped / 1000;
                    timeText.setVisibility(View.VISIBLE);
                    chronometer.setVisibility(View.INVISIBLE);
                    //math.abs returns the absolute value of seconds
                    timeText.setText(Math.abs(seconds) + " Second(s)");

                    chronometer.stop();

                    putFlightDuration = timeText.getText().toString();

                    mySQLiteHelper = new MySQLiteHelper(context);
                    sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
                    mySQLiteHelper.putInformation(sqLiteDatabase, formattedDate, putFlightDuration);
                    mySQLiteHelper.close();

                    textStart.setVisibility(View.INVISIBLE);
                    textStop.setVisibility(View.VISIBLE);

                    BackgroundTask backgroundTask = new BackgroundTask();
                    backgroundTask.execute(formattedDate, putFlightDuration);

                }
            }
        });
    }

    class BackgroundTask extends AsyncTask<String, Void, String>
    {
        String link;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args)
        {
            link = "http://softwarecontrolleddrone.esy.es/FlightInfo.php";

            String date, flightduration;

            date = args[0];
            flightduration = args[1];

            try{

                URL url = new URL(link);

                //Opens connection to url
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //Encoded data to be written to the URL
                String data_string = URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" +
                        URLEncoder.encode("flightduration", "UTF-8") + "=" + URLEncoder.encode(flightduration, "UTF-8");

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
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