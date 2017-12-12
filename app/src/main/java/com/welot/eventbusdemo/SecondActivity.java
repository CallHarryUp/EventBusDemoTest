package com.welot.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ((Button) findViewById(R.id.btn_second)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SecondActivity.this,MainActivity.class));
                //发送时间 finish
                EventBus.getDefault().postSticky(new MessageEvent("EventBus refresh"));
                finish();
            }
        });


    }
}
