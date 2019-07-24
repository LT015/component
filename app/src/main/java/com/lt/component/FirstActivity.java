package com.lt.component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/app/app")
public class FirstActivity extends AppCompatActivity {
    @Autowired
    public String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //ARouter接受参数
        ARouter.getInstance().inject(this);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void judgement(View view) {
        ARouter.getInstance().build("/main/main").withString("msg", "从app模块跳转").navigation();
    }
}
