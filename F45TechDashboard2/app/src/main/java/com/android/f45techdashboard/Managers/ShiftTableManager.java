package com.android.f45techdashboard.Managers;

import android.util.Log;

import com.android.f45techdashboard.Controllers.ShiftTableController;
import com.android.f45techdashboard.Models.DataModelLists;
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


    public void notifyObserver(DeputyDataModel dataModel)
    {
        String shift = "morning";


        if (dataModel != null)
        {
            String timeString = dataModel.data.StartTimeLocalized.substring((dataModel.data.StartTimeLocalized.indexOf("T") + 1), dataModel.data.StartTimeLocalized.indexOf("+"));
            Log.v("LIXAN", "DATE: " + timeString);
        }
        else
            {
                Log.e("LIXAN", "MODEL IS NULL 8:::::D");
            }



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
