package com.android.f45techdashboard.Managers;

import android.util.Log;

import com.android.f45techdashboard.Controllers.ShiftTableController;
import com.android.f45techdashboard.Models.DeputyDataModel;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by LeakSun on 10/30/2017.
 */

public class ShiftTableManager {

    private Map<String, ShiftTableController> observer;
    private Calendar cal;


    public ShiftTableManager()
    {
        observer = new LinkedTreeMap<>();
    }


    public void putObserver(String key, ShiftTableController tableController)
    {
        observer.put(key, tableController);
    }


    public void notifyObserver(DeputyDataModel dataModel, String shift)
    {
       // String shift = "morning";
        ArrayList<String> dataModelArray = new ArrayList<>();
        cal = Calendar.getInstance();

        Log.v("LIXAN", "TIME TO MILIS: " + String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(Integer.parseInt(dataModel.StartTime)),
                TimeUnit.MILLISECONDS.toMinutes(Integer.parseInt(dataModel.StartTime)) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Integer.parseInt(dataModel.StartTime))), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(Integer.parseInt(dataModel.StartTime)) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Integer.parseInt(dataModel.StartTime)))));

        Log.v("LIXAN", "CUR: TIME: " + cal.get(Calendar.HOUR) + " TIME +9: " + (cal.get(Calendar.HOUR_OF_DAY) + 9));

        if(shift != null)
        {
            switch (shift)
            {
                case "morning":
                    for (Map.Entry<String, ShiftTableController> shiftTableController : observer.entrySet())
                    {
                        shiftTableController.getValue().addMorningData(2, "LIXAN GWAPO");
                    }
                    break;
                case "afternoon":
                    for (Map.Entry<String, ShiftTableController> shiftTableController : observer.entrySet())
                    {
                        shiftTableController.getValue().addAfternoonData(2, "LIXAN GWAPO");
                    }
                    break;
                case "evening":
                    for (Map.Entry<String, ShiftTableController> shiftTableController : observer.entrySet())
                    {
                        shiftTableController.getValue().addEveningData(2, "LIXAN GWAPO");
                    }
                    break;
            }
        }
        else
            {
                Log.e("LIXAN", "shift variable is NULL");
            }

    }



}
