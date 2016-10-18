/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ControllerActivity extends AppCompatActivity {

    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), PopActivity.class), 999);
            }
        });

        TextView exit = (TextView)findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ControllerActivity.this);
                builder.setTitle("Controller");
                builder.setMessage(R.string.dialogMsg2);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int id)
                    {
                        //If the user clicks yes, bring them back to the LoginActivity
                        Intent intent = new Intent(ControllerActivity.this, MenuActivity.class);
                        Toast.makeText(ControllerActivity.this, R.string.exitToast, Toast.LENGTH_SHORT)
                                .show();
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int id)
                    {
                        //If the user clicks no, just close the dialog box and do nothing
                        dialog.cancel();
                    }

                });

                //Creates the alert dialog
                AlertDialog alertDialog = builder.create();
                //Show the alert dialog
                alertDialog.show();
            }
        });


/*
Button does not work: starts here
 */
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
    }

    /*////////////////////////////////////////////////
    Error here with passing data
   ///////////////////////////////////////
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 999 & resultCode == RESULT_OK){
            b.setText(data.getStringExtra("switch"));
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
