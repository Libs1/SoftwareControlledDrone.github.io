package com.example.softwarecontrolleddrone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FlightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        Button f1, f2, f3;

        f1 = (Button)findViewById(R.id.flight_1);
        f1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(FlightActivity.this, FlightPatActivity1.class);
                startActivity(intent);

            }
        });

        f2 = (Button)findViewById(R.id.flight_2);
        f2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(FlightActivity.this, FlightPatActivity2.class);
                startActivity(intent);

            }
        });

        f3 = (Button)findViewById(R.id.flight_3);
        f3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(FlightActivity.this, FlightPatActivity3.class);
                startActivity(intent);

            }
        });
    }
}
