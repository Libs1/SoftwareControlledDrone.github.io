/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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

                Animation jumpAnimation = AnimationUtils.loadAnimation(ControllerActivity.this, R.anim.drone_anim);
                bluedrone1.startAnimation(jumpAnimation);

            }
        });

        /*
        ImageView upButton = (ImageView)findViewById(R.id.upArrow);
        final ImageView bluedrone1 = (ImageView)findViewById(R.id.dronePicture2);

        upButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((ViewGroup.MarginLayoutParams)bluedrone1.getLayoutParams()).topMargin -= 5;
                bluedrone1.requestLayout();
            }

        });
        */
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
