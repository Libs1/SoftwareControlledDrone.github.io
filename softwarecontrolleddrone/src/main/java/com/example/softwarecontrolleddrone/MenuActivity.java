/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    TextView logout;
    Button controllerButton;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logout = (TextView)findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("MenuActivity");
                builder.setMessage(R.string.dialogMsg1);
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //If the user clicks yes, bring them back to the LoginActivity
                        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                        Toast.makeText(MenuActivity.this, R.string.logoutToast, Toast.LENGTH_SHORT)
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


        controllerButton = (Button)findViewById(R.id.controllerButton);
        controllerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(MenuActivity.this, ControllerActivity.class);
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
