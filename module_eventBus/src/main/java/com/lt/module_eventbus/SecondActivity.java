package com.lt.module_eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBean("EventBus","测试"));
            }
        });
    }
}
