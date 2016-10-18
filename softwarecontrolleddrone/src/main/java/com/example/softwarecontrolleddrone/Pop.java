package com.example.softwarecontrolleddrone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;

/**
 * Created by Denis on 10/17/2016.
 */
public class Pop extends Activity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);
        editText = (EditText) findViewById(R.id.editText);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }

    public void onBackPressed(){
        Intent i = new Intent();
        i.putExtra("message",editText.getText().toString());
        setResult(RESULT_OK,i);
        finish();
    }
}
