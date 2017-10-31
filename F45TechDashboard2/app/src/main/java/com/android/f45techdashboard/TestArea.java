package com.android.f45techdashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.f45techdashboard.Animations.MarqueeAnimation;
import com.android.f45techdashboard.Controllers.ShiftTableController;
import com.android.f45techdashboard.Managers.ShiftTableManager;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class TestArea extends AppCompatActivity {

    TextView marqueeView;
    LinearLayout layout;
    ShiftTableManager shiftManager;
    BarChart chart;
    ArrayList<String> graphLabels;
    ArrayList<BarEntry> entries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_area);

        layout = (LinearLayout) findViewById(R.id.linearParentLayout);
        shiftManager = new ShiftTableManager();
        ShiftTableController controller = new ShiftTableController(this);

        shiftManager.putObserver("observerKo", controller);

                if (layout != null)
                {
                    layout.addView(controller);
                }
                else
                    {
                        Log.e("LIXAN", "onCreate: layout is NULL damn it why?");
                    }



        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                shiftManager.notifyObserver("morning");
                shiftManager.notifyObserver("afternoon");
                shiftManager.notifyObserver("evening");

            }
        });

        chart = (BarChart) findViewById(R.id.barChart);

        graphLabels = new ArrayList<String>();

        graphLabels.add("Open");
        graphLabels.add("Unresolved");
        graphLabels.add("Resolved");

        entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(7f, 0));
        entries.add(new BarEntry(14f, 0));
        entries.add(new BarEntry(21f, 0));


        BarDataSet dataSet = new BarDataSet(entries, "Cells");

        BarData data = new BarData(graphLabels, dataSet);
        chart.setData(data);



    }
}
