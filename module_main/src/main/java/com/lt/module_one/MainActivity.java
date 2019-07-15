package com.lt.module_one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lt.model_eventbus.EventBean;
import com.lt.model_eventbus.EventBus;
import com.lt.model_eventbus.SecondActivity;
import com.lt.model_eventbus.Subscribe;
import com.lt.model_eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void a(EventBean eventBean){
        Log.d(TAG, eventBean.toString());
    }

}
