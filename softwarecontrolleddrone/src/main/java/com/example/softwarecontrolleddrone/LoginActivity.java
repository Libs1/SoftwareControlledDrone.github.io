/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText1;
    private EditText editText2;
    private Button loginButton;

    private SharedPreferences loginPreferences;
    SharedPreferences.Editor editor;
    private CheckBox checkBox;
    boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editText1 = (EditText) findViewById(R.id.usernameField);
        editText2 = (EditText) findViewById(R.id.passwordField);
        loginButton = (Button) findViewById(R.id.loginButton);
        checkBox = (CheckBox)findViewById(R.id.rememberMeCheckBox);

        loginButton.setOnClickListener(LoginActivity.this);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        editor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if(saveLogin == true)
        {
            editText1.setText(loginPreferences.getString("username", ""));
        }
    }
    public void onClick(View view)
    {
        if (checkBox.isChecked()) {
            editor.putBoolean("saveLogin", true);
            editor.putString("username", editText1.getText().toString());
            editor.commit();
        } else {
            editor.clear();
            editor.commit();
        }

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

        if((usernameTest == true && passwordTest == true))
        {
            startActivity(intent);
        }
    }
/*
    public void changeToMenuActivity(View view)
    {
        //editText1 = (EditText) findViewById(R.id.usernameField);
        //editText2 = (EditText) findViewById(R.id.passwordField);

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

        if((usernameTest == true && passwordTest == true))
        {
            startActivity(intent);
        }
    }

*/

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
