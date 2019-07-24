package com.lt.module_main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;


@Route(path = "/main/main")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Autowired(name = "msg") //如果参数的键值和成员变量一致 就不用写name=“”
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ARouter接受参数
        ARouter.getInstance().inject(this);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

    }


    public void judgement(View view) {
        ARouter.getInstance().build("/app/app").withString("msg", "从main模块跳转").navigation();
    }
}
