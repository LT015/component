package com.lt.component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lt.lib_common.bean.User;

import java.lang.reflect.Type;

@Route(path = "/app/app")
public class FirstActivity extends AppCompatActivity {
    private long mExitTime = 0;
    @Autowired
    public String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ARouter.getInstance().inject(this);

       //ARouter接受参数
//        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
//        serializationService.init(this);
//        User user = serializationService.parseObject(getIntent().getStringExtra("user"), User.class);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void judgement(View view) {
        User user = new User();
        user.setUserId("001");
        user.setName("张三");

        ARouter.getInstance().build("/main/main")
                .withString("msg", "从app模块跳转")
                .withSerializable("user",user)
                .navigation();
    }






    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            //两秒之内按返回键就会退出
            if(System.currentTimeMillis() - mExitTime > 2000){
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
