package com.android.f45techdashboard.Interfaces;

import android.view.View;

/**
 * Created by LeakSun on 10/31/2017.
 */

public interface ShiftUpdateInterface {

    void addMorningData(int newSize);

    void addAfternoonData(int newSize);

    void addEveningData(int newSize);

    int getMorningDataCount();

    int getAfternoonDataCount();

    int getEveningDataCount();

    View getLayout();
}
