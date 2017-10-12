package com.example.leaksun.compoundcomponentstraining;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by LeakSun on 12/10/2017.
 */

public class BoxController extends LinearLayout{


    public BoxController(Context context) {
        super(context);
        initComponents(context);
    }

    public BoxController(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        initComponents(context);
    }

    public BoxController(Context context, AttributeSet attributeSet, int defStyle)
    {
        super(context, attributeSet, defStyle);
        initComponents(context);
    }

    private void initComponents(Context context)
    {
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflate.inflate(R.layout.left_right_box_container, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    public void setStationText(TextView viewID, String text)
    {
        viewID.setText(text);
    }

    public void setXYposition(int x, int y, View view)
    {
        view.setX(x);
        view.setY(y);
    }
}
