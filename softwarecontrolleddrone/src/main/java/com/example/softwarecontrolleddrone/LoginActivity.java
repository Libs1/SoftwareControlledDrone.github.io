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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void changeToMenuActivity(View view)
    {
        final EditText editText1 = (EditText) findViewById(R.id.usernameField);
        final EditText editText2 = (EditText) findViewById(R.id.passwordField);

        boolean usernameTest = checkUsername(editText1.getText().toString());
        if(usernameTest == false){
            Toast.makeText(LoginActivity.this, R.string.errorName, Toast.LENGTH_SHORT)
                    .show();
        }

        boolean passwordTest = checkPassword(editText2.getText().toString());
        if(passwordTest == false)
        {
            Toast.makeText(LoginActivity.this, R.string.errorPassword, Toast.LENGTH_SHORT)
                    .show();
        }

        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

        if((usernameTest == true && passwordTest == true)) {

            startActivity(intent);
        }
    }



    public boolean checkUsername(String name) {
        if (!name.equals(getString(R.string.usernameField1)))
            return false;
        else
            return true;
    }

    public boolean checkPassword(String password) {
        if (password.equals(getString(R.string.passwordField1)))
            return true;
        else
            return false;
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
