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

public class TestArea extends AppCompatActivity {

    TextView marqueeView;
    LinearLayout layout;
    ShiftTableManager shiftManager;
    int i = 0;
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





    }
}
