/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public static boolean checkUsername(String name) {
        if (!name.matches("^[a-zA-Z0-9]+"))
            return false;
        else
            return true;
    }

    public static boolean checkPassword(String password) {
        if (password.matches("^[a-zA-Z0-9]+"))
            return true;
        else
            return false;
    }
}
