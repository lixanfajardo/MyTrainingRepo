package com.android.f45techdashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.f45techdashboard.Controllers.ShiftTableController;
import com.android.f45techdashboard.Controllers.TicketVolumeController;
import com.android.f45techdashboard.Controllers.TimerController;
import com.android.f45techdashboard.Managers.ShiftTableManager;
import com.android.f45techdashboard.Models.Constants;
import com.android.f45techdashboard.Models.DeputyDataModel;
import com.android.f45techdashboard.Services.DeputyAPIService;
import com.android.f45techdashboard.Services.FreshdeskAPIService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestArea extends AppCompatActivity {

    TextView marqueeView;
    LinearLayout layout, ticketLayout;
    ShiftTableManager shiftManager;
    TicketVolumeController ticketVolumeController;
    TimerController timerController;
    BarChart chart;
    ArrayList<String> graphLabels;
    ArrayList<BarEntry> entries;
    FrameLayout timerFrame;
    DeputyDataModel deputyDataModel;
    ShiftTableController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_area);


        layout = (LinearLayout) findViewById(R.id.linearParentLayout);
        timerFrame = (FrameLayout) findViewById(R.id.timerFrame);
        ticketLayout = (LinearLayout) findViewById(R.id.frame_ticketArea);
        startService(new Intent(getApplicationContext(), DeputyAPIService.class));
        //startService(new Intent(getApplicationContext(), FreshdeskAPIService.class));

        shiftManager = new ShiftTableManager();
        controller = new ShiftTableController(this);
        timerController = new TimerController(this);
        ticketVolumeController = new TicketVolumeController(this);
        shiftManager.putObserver("observerKo", controller);




    }

    @Override
    protected void onStart() {
        super.onStart();


        if (layout != null && ticketLayout != null)
        {

            ticketVolumeController.setTicketVolumeText("69");
            ticketVolumeController.setResponseTimeText("123");
            ticketLayout.addView(ticketVolumeController);
            timerController.setTimer(TimeUnit.MINUTES.toMillis(30), 1000);
            timerFrame.addView(timerController);
            layout.addView(controller);
        }
        else
        {
            Log.e("LIXAN", "onCreate: layout is NULL damn it why?");
        }



        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (deputyDataModel != null)
                {
                    shiftManager.notifyObserver(deputyDataModel);
                    shiftManager.notifyObserver(deputyDataModel);
                    shiftManager.notifyObserver(deputyDataModel);
                }
                else
                    {
                        Log.e("LIXAN", "MODEL: " + Constants.deputyDataModel);
                    }


            }
        });

        chart = (BarChart) findViewById(R.id.barChart);

        graphLabels = new ArrayList<>();

        graphLabels.add("Open");
        graphLabels.add("Unresolved");
        graphLabels.add("Resolved");

        entries = new ArrayList<>();
        entries.add(new BarEntry(7f, 0));
        entries.add(new BarEntry(14f, 0));
        entries.add(new BarEntry(21f, 0));


        BarDataSet dataSet = new BarDataSet(entries, "Cells");

        BarData data = new BarData(graphLabels, dataSet);
        chart.setData(data);

        marqueeView = (TextView) findViewById(R.id.commonIssueText);
        Animation marqueeAnim = AnimationUtils.loadAnimation(this, R.anim.marquee_animation);
        marqueeView.startAnimation(marqueeAnim);

    }
}
