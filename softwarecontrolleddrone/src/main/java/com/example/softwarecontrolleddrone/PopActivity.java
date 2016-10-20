/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.prefs.Preferences;

/**
 * Created by Denis on 10/17/2016.
 */
public class PopActivity extends Activity {
    private Switch switch1;
    private Switch countdown;
    public boolean isSwitchChecked;
    public boolean isCountDownChecked;
    public static final String PREFS = "sharedPreferences";
    public static final String BRIGHTNESS = "brightness";
    private Button save_button;
    private SeekBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RadioGroup radioGroup;
        RadioButton rb1;
        RadioButton rb2;
        RadioButton rb3;
        //initializers
        setContentView(R.layout.activity_pop);
        switch1 = (Switch) findViewById(R.id.switch1);
        bar = (SeekBar) findViewById((R.id.seekBar));
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);

        //Final values decalred above to simplify the code
        final SharedPreferences sharedPreferences = getSharedPreferences(PREFS,0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.d("BRIGHTNESS", "" + sharedPreferences.getInt(BRIGHTNESS, 0));

        //loading sets
        switch1.setChecked(sharedPreferences.getBoolean("Switch",false));
        bar.setProgress(sharedPreferences.getInt(BRIGHTNESS, 0));


        //window layout management
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
                //put switch in
                editor.putInt(BRIGHTNESS, bar.getProgress());
                editor.commit();
                finish();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("Switch", isChecked);
                editor.commit();

            }
        });
    }
}



