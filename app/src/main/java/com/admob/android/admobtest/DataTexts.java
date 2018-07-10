package com.admob.android.admobtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.admob.android.admobtest.STATIC_STRINGS.Constants_;

public class DataTexts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_texts);
        TextView tv = (TextView)findViewById(R.id.tv_data_text);
        tv
                .setText(
                        getIntent().getStringExtra(Constants_.TEXT_DATA_FROM_REALM_RESULTS)
                );
        tv.setMovementMethod(new ScrollingMovementMethod());
    }
}
