package com.hongseokandrewjang.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;


public class PreviewView extends View{

    private final int WIDTH_COUNT = 6;
    private final int HEIGHT_COUNT = 7;
    private int stageWidth;
    private int stageHeight;
    private int unitWidth;
    private int unitHeight;

    Block nextBlock;
    Paint paint = new Paint();
    int[][] map = {
            {9,9,9,9,9,9},
            {9,0,0,0,0,9},
            {9,0,0,0,0,9},
            {9,0,0,0,0,9},
            {9,0,0,0,0,9},
            {9,0,0,0,0,9},
            {9,9,9,9,9,9}
    };

    public PreviewView(Context context, Block nextBlock) {
        super(context);
        this.nextBlock = nextBlock;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        stageWidth = this.getWidth();
        unitWidth = stageWidth/WIDTH_COUNT;
        stageHeight = this.getHeight();
        unitHeight = stageHeight/HEIGHT_COUNT;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1. Draw basic background
        paint.setColor(ContextCompat.getColor(getContext(), R.color.background));
        canvas.drawRect(0,0,stageWidth,stageWidth,paint);

        // 2. Set the block into the map
        if (nextBlock!=null) {
            int[][] currentBlock = nextBlock.getCurrentBlock();
            for (int i = 1; i <= currentBlock.length; i++) {
                for (int j = 2; j <= currentBlock.length+1; j++) {
                    map[j][i] = currentBlock[j - 2][i - 1];
                }
            }
        }

        // 3. Draw the next block
        for (int i=0;i<WIDTH_COUNT;i++){
            for(int j=0;j<HEIGHT_COUNT;j++){
                BlockDrawer blockDrawer = new BlockDrawer(getContext(),unitHeight, unitWidth, map, i, j,canvas);
                blockDrawer.drawBlock();
            }
        }
    }
}


