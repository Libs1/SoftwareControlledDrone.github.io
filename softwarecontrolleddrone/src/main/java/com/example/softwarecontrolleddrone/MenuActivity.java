/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {


    Button controllerButton, databaseButton, flightPathButton;
    ImageView image;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setContentView(R.layout.activity_controller);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        image = (ImageView) findViewById(R.id.dronePicture1);

        final int[] imageArray = { R.drawable.drone, R.drawable.drone_90,
                R.drawable.drone_180, R.drawable.drone_270,
               };

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                image.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 50);
            }
        };
        handler.postDelayed(runnable, 50);

        //image.startAnimation(
              //  AnimationUtils.loadAnimation(MenuActivity.this, R.anim.drone_rotation_anim) );

        controllerButton = (Button)findViewById(R.id.controllerButton);
        controllerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(MenuActivity.this, ControllerActivity.class);
                startActivity(intent);

            }
        });

        databaseButton = (Button)findViewById(R.id.databaseButton);
        databaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, FlightsActivity.class);
                startActivity(intent);

            }
        });
<<<<<<< HEAD
        
        flightPathButton = (Button)findViewById(R.id.flightPatternButton);
        databaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, FlightActivity.class);
                startActivity(intent);

            }
        });




=======
>>>>>>> refs/remotes/origin/kevin-scd
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
