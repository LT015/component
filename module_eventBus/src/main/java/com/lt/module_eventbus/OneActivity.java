package com.lt.module_eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;


@Route(path = "/eventBus/one")
public class OneActivity extends AppCompatActivity {
    private static final String TAG = "OneActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        EventBus.getDefault().register(this);
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void a(EventBean eventBean){
        Log.d(TAG, eventBean.toString());
    }
}
