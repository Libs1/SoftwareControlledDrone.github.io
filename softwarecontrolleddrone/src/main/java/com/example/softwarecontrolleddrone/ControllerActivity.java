/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.icu.util.Calendar;
import android.icu.util.Output;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.Tag;
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
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import com.google.android.gms.fitness.data.MapValue;
import org.w3c.dom.Text;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ControllerActivity extends AppCompatActivity {

    /*Variables for internal DB*/
    MySQLiteHelper mySQLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;

    /*Variables for LED button*/
    public static final String PREFS = "sharedPreferences";
    public static final String BRIGHTNESS = "brightness";
    public static Button b;
    boolean switch1;

    Chronometer chronometer;
    TextView timeText;
    String putFlightDuration;
    String formattedDate;
    private long timeWhenStopped = 0;

    /*SharedPreferences for accessing Flight Activity*/
    SharedPreferences accessPreference;
    SharedPreferences.Editor editor;
    boolean check2;

    String recvUsername;

    /*Variables for joysticks*/
    TextView txtX1, txtY1, txtX2, txtY2, textView;
    Button startButton, stopButton;
    DualJoystickView joystick;
    byte input[] = {23, 0, 0x7f, 0x7f, 0x7f};//default input
    //byte input[] = {23, 0, ((byte) 0x208), ((byte) 0x208), ((byte) 0x208)};

    public final String ACTION_USB_PERMISSION = "com.example.softwarecontrolleddrone.USB_PERMISSION";
    UsbManager usbManager;
    UsbDevice device;
    UsbSerialDevice serialPort;
    UsbDeviceConnection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        txtX1 = (TextView)findViewById(R.id.TextViewX1);
        txtY1 = (TextView)findViewById(R.id.TextViewY1);

        txtX2 = (TextView)findViewById(R.id.TextViewX2);
        txtY2 = (TextView)findViewById(R.id.TextViewY2);

        textView = (TextView)findViewById(R.id.textView);

        joystick = (DualJoystickView)findViewById(R.id.dualjoystickView);
        joystick.setOnJostickMovedListener(_listenerLeft, _listenerRight);

        usbManager = (UsbManager) getSystemService(this.USB_SERVICE);
        startButton = (Button) findViewById(R.id.buttonStart);
        stopButton = (Button) findViewById(R.id.buttonStop);

        setUiEnabled(false);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(broadcastReceiver, filter);

        /*Retreiving the username that will be put into the database*/
        recvUsername = getIntent().getStringExtra("usernamePassedContr");

        /*SharedPreferences for accessing the flight activity*/
        accessPreference = getSharedPreferences("accessPrefs", MODE_PRIVATE);
        editor = accessPreference.edit();

        if(getResources().getBoolean(R.bool.portrait_only)){
            setContentView(R.layout.activity_controller);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        /*Retreiving the current date*/
        java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        //LED Button
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ControllerActivity.this, PopActivity.class));
            }
        });

        /*Chronometer and the TextView to display the time*/
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        timeText = (TextView) findViewById(R.id.timeDisplayed);
        chronometer.setVisibility(View.INVISIBLE);

    }

    UsbSerialInterface.UsbReadCallback mCallback = new UsbSerialInterface.UsbReadCallback() { //Defining a Callback which triggers whenever data is read.
        @Override
        public void onReceivedData(byte[] arg0)
        {
        }
    };

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { //Broadcast Receiver to automatically start and stop the Serial connection.
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().equals(ACTION_USB_PERMISSION)) {
                    boolean granted = intent.getExtras().getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED);
                    if (granted) {
                        connection = usbManager.openDevice(device);
                        serialPort = UsbSerialDevice.createUsbSerialDevice(device, connection);
                        if (serialPort != null) {
                            if (serialPort.open()) { //Set Serial Connection Parameters.
                                setUiEnabled(true);
                                serialPort.setBaudRate(115200);
                                serialPort.setDataBits(UsbSerialInterface.DATA_BITS_8);
                                serialPort.setStopBits(UsbSerialInterface.STOP_BITS_1);
                                serialPort.setParity(UsbSerialInterface.PARITY_NONE);
                                serialPort.setFlowControl(UsbSerialInterface.FLOW_CONTROL_OFF);
                                serialPort.read(mCallback);

                                chronometer.setVisibility(View.VISIBLE);

                                tvAppend(textView, "Serial Connection Opened!\n");

                            } else {
                                Log.d("SERIAL", "PORT NOT OPEN");
                            }
                        } else {
                            Log.d("SERIAL", "PORT IS NULL");
                        }
                    } else {
                        Log.d("SERIAL", "PERM NOT GRANTED");
                    }
                } else if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_ATTACHED)) {
                    onClickStart(startButton);
                } else if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_DETACHED)) {
                    onClickStop(stopButton);
                }
            }catch(Exception e){}
        };
    };

    public void onClickStart(View view)
    {
        //byte a[] = {1};
        //serialPort.write(a);

        editor.putBoolean("check", true);
        editor.commit();

        //chronometer.setVisibility(View.VISIBLE);
        chronometer.setBase(SystemClock.elapsedRealtime());
        timeText.setVisibility(View.INVISIBLE);
        timeText.setText(R.string.zeroseconds);
        chronometer.start();

        try {
            HashMap<String, UsbDevice> usbDevices = usbManager.getDeviceList();
            if (!usbDevices.isEmpty()) {
                boolean keep = true;
                for (Map.Entry<String, UsbDevice> entry : usbDevices.entrySet()) {
                    device = entry.getValue();
                    int deviceVID = device.getVendorId();
                    if (deviceVID == 0x2A03)//Arduino Vendor ID
                    {
                        PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
                        usbManager.requestPermission(device, pi);
                        keep = false;
                    } else {
                        connection = null;
                        device = null;
                    }

                    if (!keep)
                        break;
                }
            }
        }catch(Exception e){
            //Toast.makeText(MainActivity.this, "The Arduino Is Not Connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickStop(View view)
    {
        byte input[] = {23, 0, 0x7f, 0x7f, 0x7f};
        //byte input[] = {23, 0, ((byte) 0x208), ((byte) 0x208), ((byte) 0x208)};
        serialPort.write(input);
        setUiEnabled(false);
        serialPort.close();
        tvAppend(textView, "\nSerial Connection Closed! \n");

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

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(formattedDate, putFlightDuration, recvUsername);
    }

    public void setUiEnabled(boolean bool)
    {
        startButton.setEnabled(!bool);
        stopButton.setEnabled(bool);
        textView.setEnabled(bool);
    }

    private void tvAppend(TextView tv, CharSequence text) {
        final TextView ftv = tv;
        final CharSequence ftext = text;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ftv.setText(ftext);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /*Joystick Listener for the left joystick*/
    private JoystickMovedListener _listenerLeft = new JoystickMovedListener() {

        @Override
        public void OnMoved(int yaw, int throttle) {
            try {
                throttle = throttle + 128;
                yaw = yaw + 128;
                if(throttle >= 256)
                    throttle = 255;
                if(yaw >= 256)
                    yaw = 255;
                if(yaw >= 108 && yaw <= 148)
                    yaw = 127;

                txtX1.setText(Integer.toString(yaw));//rudder
                txtY1.setText(Integer.toString(throttle));//throttle
                input[1] = (byte) throttle;//throttle
                input[2] = (byte) yaw;//rudder
                serialPort.write(input);

            }catch(Exception e){
                //Toast.makeText(FlightController.this, "The Arduino Is Not Connected", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void OnReleased() {
            try{

            }catch(Exception e){
            }
        }

        public void OnReturnedToCenter() {
            try{

            }catch(Exception e){
            }
        };
    };

    /*Joystick listener for the right joystick*/
    private JoystickMovedListener _listenerRight = new JoystickMovedListener() {

        @Override
        public void OnMoved(int roll, int pitch) {
            try{
                pitch = pitch + 128;
                roll = roll + 128;
                if(pitch >= 256)
                    pitch = 255;
                if(pitch == 128)
                    pitch = 127;
                if(roll >= 256)
                    roll = 255;
                if(roll == 128)
                    roll = 127;

                txtX2.setText(Integer.toString(roll));//aileron
                txtY2.setText(Integer.toString(pitch));//elevator

                input[3] = (byte) pitch;//forward or backward
                input[4] = (byte) roll;//left or right

                serialPort.write(input);
            }catch(Exception e){
                //Toast.makeText(FlightController.this, "The Arduino Is Not Connected", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void OnReleased() {
            try{

            }catch(Exception e){
            }
        }

        public void OnReturnedToCenter() {
            try{

            }catch(Exception e){
            }
        };
    };

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
            link = "http://softwarecontrolleddrone.esy.es/FlightInfo2.php";

            String date, flightduration, username;

            date = args[0];
            flightduration = args[1];
            username = args[2];

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
                        URLEncoder.encode("flightduration", "UTF-8") + "=" + URLEncoder.encode(flightduration, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

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