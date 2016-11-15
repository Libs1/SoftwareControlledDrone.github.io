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
    private Button save_button;
    public Switch switch1;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private static final String KEY_TEXT_VALUE = "button";
    String background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            CharSequence savedText = savedInstanceState.getCharSequence(KEY_TEXT_VALUE);
            //ControllerActivity.b.
        }

        //window layout management
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .4), (int) (height * .5));

        //initializers
        setContentView(R.layout.activity_pop);
        switch1 = (Switch) findViewById(R.id.switch1);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);

        //Final values decalred above to simplify the code
        final SharedPreferences sharedPreferences = getSharedPreferences(PREFS,0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        //loading sets
        switch1.setChecked(sharedPreferences.getBoolean("Switch",false));
        rb1.setChecked(sharedPreferences.getBoolean("rb1",false));
        rb2.setChecked(sharedPreferences.getBoolean("rb2",false));
        rb3.setChecked(sharedPreferences.getBoolean("rb3",false));


        save_button = (Button) findViewById(R.id.save);
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editor.putBoolean("Switch", switch1.isChecked());
                editor.putBoolean("rb1", rb1.isChecked());
                editor.putBoolean("rb2", rb2.isChecked());
                editor.putBoolean("rb3", rb3.isChecked());
                editor.commit();


                SharedPreferences sharedPreferences = getSharedPreferences(PREFS, 0);
                Boolean switch1 = sharedPreferences.getBoolean("Switch", false);
                Boolean rb1 = sharedPreferences.getBoolean("rb1", false);
                Boolean rb2 = sharedPreferences.getBoolean("rb2", false);
                Boolean rb3 = sharedPreferences.getBoolean("rb3", false);

                if(switch1) {
                    if(rb1){
                        ControllerActivity.b.setBackgroundResource(R.drawable.buttonshape_led_red);
                    }else if(rb2){
                        ControllerActivity.b.setBackgroundResource(R.drawable.buttonshape_led_green);
                    }else if(rb3){
                        ControllerActivity.b.setBackgroundResource(R.drawable.buttonshape_led_blue);
                    }else{
                        ControllerActivity.b.setBackgroundResource(R.color.white);
                        Toast.makeText(PopActivity.this, R.string.no_choice, Toast.LENGTH_SHORT)
                                .show();
                    }
                    ControllerActivity.b.setText(R.string.led_on);
                } else {
                    ControllerActivity.b.setText(R.string.led_off);
                    ControllerActivity.b.setBackgroundResource(R.drawable.buttonshape_led);
                }


                finish();
            }
        });
    }

}