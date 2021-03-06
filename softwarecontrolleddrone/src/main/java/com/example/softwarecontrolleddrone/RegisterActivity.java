/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/


package com.example.softwarecontrolleddrone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

public class RegisterActivity extends AppCompatActivity {

    Context context = this;

    EditText registerFirstName;
    EditText registerLastName;
    EditText registerUsername;
    EditText registerPassword;
    Button registerButton2;
    String firstName, lastName, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFirstName = (EditText)findViewById(R.id.registerFirstName);
        registerLastName = (EditText)findViewById(R.id.registerLastName);
        registerUsername = (EditText)findViewById(R.id.registerUsername);
        registerPassword = (EditText)findViewById(R.id.registerPassword);

        registerButton2 = (Button)findViewById(R.id.registerButton2);
        registerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = registerFirstName.getText().toString();
                lastName = registerLastName.getText().toString();
                username = registerUsername.getText().toString();
                password = registerPassword.getText().toString();

                BackgroundTask backgroundTask = new BackgroundTask();


                boolean firstnameTest = checkFirstNameRegister(firstName);
                if(firstnameTest == false)
                {
                    Toast.makeText(RegisterActivity.this, R.string.errorInput, Toast.LENGTH_SHORT)
                            .show();
                }

                boolean lastnameTest = checkLastNameRegister(lastName);
                if(lastnameTest == false)
                {
                    Toast.makeText(RegisterActivity.this, R.string.errorInput, Toast.LENGTH_SHORT)
                            .show();
                }

                boolean usernameTest = checkUsernameRegister(username);
                if(usernameTest == false)
                {
                    Toast.makeText(RegisterActivity.this, R.string.errorName, Toast.LENGTH_SHORT)
                            .show();
                }

                boolean passwordTest = checkPasswordRegister(password);
                if(passwordTest == false)
                {
                    Toast.makeText(RegisterActivity.this, R.string.errorPassword, Toast.LENGTH_SHORT)
                            .show();
                }

                if(firstnameTest && lastnameTest && usernameTest && passwordTest)
                {
                    backgroundTask.execute(firstName, lastName, username, password);
                }



            }
        });
    }

    public boolean checkFirstNameRegister(String registeredFirstName)
    {
        if(registeredFirstName.length() >= 2 && registeredFirstName.matches("[a-zA-Z]+(\\\\-[a-zA-Z]+)?"))
            return true;
        else
            return false;
    }

    public boolean checkLastNameRegister(String registeredLastName)
    {
        if(registeredLastName.length() >= 2 && registeredLastName.matches("[a-zA-Z]+(\\\\-[a-zA-Z]+)?"))
            return true;
        else
            return false;
    }

    public boolean checkUsernameRegister(String registeredUsername) {
        if (registeredUsername.length() >= 4 && registeredUsername.matches("[a-zA-Z0-9]+(\\\\-[a-zA-Z0-9]+)?"))
            return true;
        else
            return false;
    }

    public boolean checkPasswordRegister(String registeredPassword) {
        if (registeredPassword.length() >= 6 && registeredPassword.matches("[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)?"))
            return true;
        else
            return false;
    }

    class BackgroundTask extends AsyncTask<String, Void, String>
    {

        AlertDialog alertDialog;

        String link;

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected String doInBackground(String... args)
        {
            link = "http://softwarecontrolleddrone.esy.es/AddMember.php";

            String firstName, lastName, username, password;

            firstName = args[0];
            lastName = args[1];
            username = args[2];
            password = args[3];

            try
            {
                URL url = new URL(link);

                //Opens connection to url
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //Encoded data to be written to the url
                String data_string = URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&" +
                        URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

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
            catch(MalformedURLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
           if(result!=null && result.equalsIgnoreCase("Registration Success"))
            {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                Toast.makeText(RegisterActivity.this, R.string.RegistrationSuccessful, Toast.LENGTH_SHORT)
                        .show();
                startActivity(intent);
            }
            else{
               new AlertDialog.Builder(context)
                       .setIcon(android.R.drawable.ic_dialog_alert)
                       .setTitle(R.string.dialogMsg3)
                       .setMessage(result)
                       .setPositiveButton("Continue", new DialogInterface.OnClickListener()
                       {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();
                           }
                       })
                       .show();
           }

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
