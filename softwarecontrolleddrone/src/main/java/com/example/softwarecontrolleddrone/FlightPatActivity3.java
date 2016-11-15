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

public class FlightPatActivity3 extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_pat3);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button startButton = (Button)findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                db = SQLiteDatabase.openOrCreateDatabase("DroneRecall", /*Context.MODE_PRIVATE*/null, null);

                db.execSQL("CREATE TABLE RecallComm(" +
                        "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                        "recallcommand VARCHAR(50) NOT NULL" +
                        ")");

                db.execSQL("INSEERT INTO RecallComm (recallcommand)" +
                        "VALUES ('Drone Recalled')");

                c = db.rawQuery("SELECT recallcommand FROM RecallComm", null);

                c.moveToFirst();

                String f_pattern = c.getString(1);

                Toast.makeText(FlightPatActivity3.this, f_pattern, Toast.LENGTH_LONG)
                        .show();

                ImageView bluedrone = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(FlightPatActivity3.this, R.anim.drone_pat_1);

                bluedrone.startAnimation(jumpAnimation);

            }
        });

        Button stopButton = (Button)findViewById(R.id.stop);
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ImageView bluedrone = (ImageView)findViewById(R.id.dronePicture2);

                Animation jumpAnimation = AnimationUtils.loadAnimation(FlightPatActivity3.this, R.anim.drone_anim_end);

                bluedrone.startAnimation(jumpAnimation);

            }
        });
    }
}
