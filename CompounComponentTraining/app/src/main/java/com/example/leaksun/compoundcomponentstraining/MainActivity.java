package com.example.leaksun.compoundcomponentstraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.leaksun.compoundcomponentstraining.BoxController;

public class MainActivity extends AppCompatActivity {

    BoxController control;
    TextView stationsLabel;
    RelativeLayout layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        control = new BoxController(getApplicationContext());
        stationsLabel = (TextView) findViewById(R.id.stationLabel);
        layout1 = (RelativeLayout) findViewById(R.id.video_container1);

        /*control.setStationText(stationsLabel, "0");
        control.setXYposition(0, 200, layout1);*/

        control.setLayoutParams("numberOverlayRight", 700, 350);
        control.setLabelText("21");
        setContentView(control.returnLayout());


    }
}
