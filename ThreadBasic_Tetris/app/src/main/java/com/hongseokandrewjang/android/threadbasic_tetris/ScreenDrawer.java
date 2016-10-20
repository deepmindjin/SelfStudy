package com.hongseokandrewjang.android.threadbasic_tetris;

import android.os.Handler;

/**
 * Created by HongSeokAndrewJang on 2016-10-19.
 */

public class ScreenDrawer extends Thread{
    Handler handler;
    StageView stageView;
    PreviewView previewView;

    ScreenDrawer(Handler handler, StageView stageView, PreviewView previewView){
        this.handler = handler;
        this.stageView = stageView;
        this.previewView = previewView;
    }

    @Override
    public void run() {
        try{
            while(MainActivity.gameStart) {
                if (MainActivity.needNewBlock) {
                    previewView.postInvalidate();
                    MainActivity.needNewBlock = false;
                }
                Thread.sleep(1000);
                stageView.controllingBlock.start();
                stageView.postInvalidate();
            }
        }catch (Exception e){}
    }
}
