package com.welot.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private TextView sticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.tv));
        tv.setText("MainActivity");
        sticky = ((TextView) findViewById(R.id.tv_sticky));
        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        //订阅时间
        // EventBus.getDefault().register(this);
        ((Button) findViewById(R.id.btn_sticky)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!EventBus.getDefault().isRegistered(MainActivity.this)) {
                    EventBus.getDefault().register(MainActivity.this);
                }


            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDealEvent(MessageEvent messageEvent) {
        tv.setText(messageEvent.getMessage());
    }

    @Subscribe(sticky = true)
    public void onStickyEvent(MessageEvent messageEvent) {
        sticky.setText(messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBus.getDefault().unregister(this);
    }
}
