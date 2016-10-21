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
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;


public class PopActivity extends Activity {
    public static final String PREFS = "sharedPreferences";
    public static final String BRIGHTNESS = "brightness";
    private Button save_button;
    private Switch switch1;
    private SeekBar bar;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //window layout management
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        //initializers
        setContentView(R.layout.activity_pop);
        switch1 = (Switch) findViewById(R.id.switch1);
        bar = (SeekBar) findViewById((R.id.seekBar));
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);

        //Final values decalred above to simplify the code
        final SharedPreferences sharedPreferences = getSharedPreferences(PREFS,0);
        final SharedPreferences settings = getSharedPreferences("answers", MODE_PRIVATE);
        Log.d("BRIGHTNESS", "" + sharedPreferences.getInt(BRIGHTNESS, 0));
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        //loading sets
        switch1.setChecked(sharedPreferences.getBoolean("Switch",false));
        rb1.setChecked(sharedPreferences.getBoolean("rb1",false));
        rb2.setChecked(sharedPreferences.getBoolean("rb2",false));
        rb3.setChecked(sharedPreferences.getBoolean("rb3",false));
        bar.setProgress(sharedPreferences.getInt(BRIGHTNESS, 0));

        save_button = (Button) findViewById(R.id.save);
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                //put switch in
                editor.putInt(BRIGHTNESS, bar.getProgress());
                    editor.putBoolean("rb1", rb1.isChecked());
                    editor.putBoolean("rb2", rb2.isChecked());
                    editor.putBoolean("rb3", rb3.isChecked());
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