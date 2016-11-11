package com.example.softwarecontrolleddrone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    Context context = this;

    EditText registerUsername, registerPassword;
    Button registerButton2;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUsername = (EditText)findViewById(R.id.registerUsername);
        registerPassword = (EditText)findViewById(R.id.registerPassword);

        registerButton2 = (Button)findViewById(R.id.registerButton2);
        registerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = registerUsername.getText().toString();
                password = registerPassword.getText().toString();

                BackgroundTask backgroundTask = new BackgroundTask();


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

                if(usernameTest == true && passwordTest == true)
                {
                    backgroundTask.execute(username, password);
                    //finish();
                }



            }
        });
    }

    public boolean checkUsernameRegister(String registeredUsername) {
        if (registeredUsername.length() >= 3 && registeredUsername.matches("[a-zA-Z0-9]+(\\\\-[a-zA-Z0-9]+)?"))
            return true;
        else
            return false;
    }

    public boolean checkPasswordRegister(String registeredPassword) {
        if (registeredPassword.length() >= 4 && registeredPassword.matches("[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)?"))
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
            //link = "http://softwarecontrolleddrone.esy.es/AddMember.php";
/*
            alertDialog =  new AlertDialog.Builder(context).create();
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setTitle(R.string.dialogMsg3);
            alertDialog.setMessage(getResources().getString(R.string.dialogMsg4));
            alertDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args)
        {
            link = "http://softwarecontrolleddrone.esy.es/AddMember.php";

            String username, password;

            username = args[0];
            password = args[1];

            try
            {
                URL url = new URL(link);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //Encoded data
                String data_string = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();

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
            new AlertDialog.Builder(context)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.dialogMsg3)
                    .setMessage(R.string.dialogMsg4)
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    })
                    .show();

        }
    }

}
