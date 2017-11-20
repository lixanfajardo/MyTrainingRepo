package com.android.f45techdashboard.Interfaces;

import android.view.View;

/**
 * Created by LeakSun on 10/31/2017.
 */

public interface ShiftUpdateInterface {

    void addMorningData(int newSize, String onshiftNames);

    void addAfternoonData(int newSize, String onshiftNames);

    void addEveningData(int newSize, String onshiftNames);

    int getMorningDataCount();

    int getAfternoonDataCount();

    int getEveningDataCount();

    View getLayout();
}
