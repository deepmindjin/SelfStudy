package com.hongseokandrewjang.android.threadexcercise02;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int SHOW_BACK_VALUE = 1;
    int mMainValue = 0;
    int mBackValue = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_BACK_VALUE:
                    mBackText.setText("BackValue : "+msg.arg1);
                    break;
            }
        }
    };
    TextView mMainText;
    TextView mBackText;
    Button btnIncrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainText = (TextView)findViewById(R.id.mainvalue);
        mBackText = (TextView)findViewById(R.id.backvalue);
        btnIncrease = (Button)findViewById(R.id.increase);
        btnIncrease.setOnClickListener(this);

//        BackThread thread = new BackThread(handler);
//        thread.setDaemon(true);
//        thread.start();

        BackRunnable runnable = new BackRunnable(handler);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void onClick(View v) {
        mMainValue++;
        mMainText.setText("MainValue : "+mMainValue);
        mBackText.setText("BackValue : "+mBackValue);
    }

    class BackThread extends Thread{
        Handler handler;
        public BackThread(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            while(true) {
                mBackValue++;
                handler.sendEmptyMessage(SHOW_BACK_VALUE);
                try {Thread.sleep(1000);} catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class BackRunnable implements Runnable{
        int mBackValue = 0;
        Handler handler;
        public BackRunnable(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            while(true) {
                mBackValue++;
                Message message = Message.obtain(handler,SHOW_BACK_VALUE,mBackValue);
                handler.sendMessage(message);
//                handler.sendEmptyMessage(SHOW_BACK_VALUE);

//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mBackText.setText("BackValue : "+mBackValue);
//                    }
//                });
                try {Thread.sleep(1000);}
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
