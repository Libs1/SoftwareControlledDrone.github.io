/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);


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
