package com.hongseokandrewjang.android.threadexcercise01;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    break;

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    class ThreadOne extends Thread{
        Handler mHandler;
        Thread(Handler handler){
            mHandler = handler;
        }
        @Override
        public void run() {
            int sum = 0;
            for(int i=0;i<100;i++){
                sum+=i;
            }
            Message message = new Message();
            message.what = 1;
            message.arg1 = sum;

        }
    }
}
