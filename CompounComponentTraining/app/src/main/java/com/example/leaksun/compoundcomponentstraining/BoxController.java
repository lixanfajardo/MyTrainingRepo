package com.example.leaksun.compoundcomponentstraining;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by LeakSun on 12/10/2017.
 */

public class BoxController extends RelativeLayout{

    Context context;

    public BoxController(Context context) {
        super(context);
        this.context = context;
    }

    public BoxController(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        this.context = context;
    }

    public BoxController(Context context, AttributeSet attributeSet, int defStyle)
    {
        super(context, attributeSet, defStyle);
        this.context = context;
    }

    public void setLayoutParams(String layoutType, int w , int h)
    {
        switch (layoutType)
        {
            case "numberLeft" :
                inflate(context, R.layout.left_right_box_container, this);
                break;
            case "numberBottom":
                inflate(context, R.layout.layout_text_bottom, this);
                break;
            case "numberOverlayLeft" :
                inflate(context, R.layout.layout_text_overlay, this);
                break;
            case "numberOverlayRight":
                inflate(context, R.layout.layout_text_overlay_right, this);
                break;
        }
        View id = findViewById(R.id.vvVideo);
        if (id != null)
        {
            id.setLayoutParams(new LayoutParams(w, h));
        }
        else
            {
                Log.e("LIXAN", "View is NULL");
            }
    }

    public BoxController returnLayout(){
        return this;
    }


    public void setLabelText(String text)
    {
        TextView viewID = (TextView) findViewById(R.id.stationLabel);
        viewID.setText(text);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        returnLayout();
    }


}
