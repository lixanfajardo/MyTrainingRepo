package com.android.f45techdashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.f45techdashboard.Animations.MarqueeAnimation;

public class TestArea extends AppCompatActivity {

    TextView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_area);

        marqueeView = (TextView) findViewById(R.id.commonIssueText);


            marqueeView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            marqueeView.setSingleLine(true);
            marqueeView.setSelected(true);

    }
}
