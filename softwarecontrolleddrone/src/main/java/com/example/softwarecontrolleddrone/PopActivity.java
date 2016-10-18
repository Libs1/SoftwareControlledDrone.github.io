/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

/**
 * Created by Denis on 10/17/2016.
 */
public class PopActivity extends Activity {
    private Switch switch1;
    private Button save_button;
    private SeekBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop);
        switch1 = (Switch) findViewById(R.id.switch1);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));


        save_button = (Button) findViewById(R.id.save);
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
        /*i.putExtra("message",editText.getText().toString());*/
                i.putExtra("switch", switch1.getText().toString());
                setResult(RESULT_OK, i);
                finish();

            }
        });

        bar = (SeekBar) findViewById((R.id.seekBar));
    }
}
