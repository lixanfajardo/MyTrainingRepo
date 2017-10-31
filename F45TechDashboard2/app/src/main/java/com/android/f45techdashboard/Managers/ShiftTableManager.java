package com.android.f45techdashboard.Managers;

import com.android.f45techdashboard.Controllers.ShiftTableController;
import com.google.gson.internal.LinkedTreeMap;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by LeakSun on 10/30/2017.
 */

public class ShiftTableManager {

    private Map<String, ShiftTableController> observer;


    public ShiftTableManager()
    {
        observer = new LinkedTreeMap<>();
    }


    public void putObserver(String key, ShiftTableController tableController)
    {
        observer.put(key, tableController);
    }


    public void notifyObserver(String shift)
    {
        switch (shift)
        {
            case "morning":
                for (Map.Entry<String, ShiftTableController> shiftTableController : observer.entrySet())
                {
                    shiftTableController.getValue().addMorningData(2);
                }
                break;
            case "afternoon":
                for (Map.Entry<String, ShiftTableController> shiftTableController : observer.entrySet())
                {
                    shiftTableController.getValue().addAfternoonData(2);
                }
                break;
            case "evening":
                for (Map.Entry<String, ShiftTableController> shiftTableController : observer.entrySet())
                {
                    shiftTableController.getValue().addEveningData(2);
                }
                break;
        }
    }



}
