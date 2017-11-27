package com.android.f45techdashboard.Managers;

import android.util.Log;

import com.android.f45techdashboard.Controllers.ShiftTableController;
import com.android.f45techdashboard.Models.DataModelLists;
import com.android.f45techdashboard.Models.DeputyDataModel;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
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


    public void notifyObserver(ArrayList<DeputyDataModel> dataModel)
    {
        String shift = "morning";
        DeputyDataModel deputyDataModel = null;

        if (dataModel != null)
        {
            for (int i = 0 ; dataModel.size() > i; i++)
            {
                deputyDataModel = dataModel.get(i);
                String timeString = deputyDataModel.StartTimeLocalized.substring((deputyDataModel.StartTimeLocalized.indexOf("T") + 1), deputyDataModel.StartTimeLocalized.indexOf("+"));
                Log.v("LIXAN", "DATE: " + timeString + " NAME: " + deputyDataModel._DPMetaData.EmployeeInfo.DisplayName);

                try {

                    Date tryDate = new Date(Long.parseLong(deputyDataModel.StartTime) * 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                    dateFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getTimeZone("GMT+8").getID()));


                    Log.d("LIXAN", "DATE: " + dateFormat.format(tryDate));

                } catch (Exception e) {
                    e.printStackTrace();
                }


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
        else {
            Log.d("LIXAN", "notifyObserver: dataModel IS NULL 8:::::::D");
        }


    }



}
