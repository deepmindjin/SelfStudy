package com.hongseokandrewjang.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

/**
 * Created by HongSeokAndrewJang on 2016-10-19.
 */

// This Class only helps drawing blocks and backgruond
public class BlockDrawer {
    Context context;
    int unitWidth;
    int unitHeight;
    int map[][];
    int x,y;
    Canvas canvas;

    Paint paint = new Paint();

    BlockDrawer(Context context, int unitHeight, int unitWidth, int map[][],int x, int y,Canvas canvas){
        this.context = context;
        this.unitHeight = unitHeight;
        this.unitWidth = unitWidth;
        this.map = map;
        this.x = x;
        this.y = y;
        this.canvas = canvas;
    }

    public void drawBlock(){

        // Set the color of the paint by input type
        switch (map[y][x]){
            case MainActivity.BLOCK_I:
                paint.setColor(ContextCompat.getColor(context, R.color.block_I));
                break;
            case MainActivity.BLOCK_J:
                paint.setColor(ContextCompat.getColor(context, R.color.block_J));
                break;
            case MainActivity.BLOCK_L:
                paint.setColor(ContextCompat.getColor(context, R.color.block_L));
                break;
            case MainActivity.BLOCK_S:
                paint.setColor(ContextCompat.getColor(context, R.color.block_S));
                break;
            case MainActivity.BLOCK_Z:
                paint.setColor(ContextCompat.getColor(context, R.color.block_Z));
                break;
            case MainActivity.BLOCK_T:
                paint.setColor(ContextCompat.getColor(context, R.color.block_T));
                break;
            case MainActivity.BLOCK_O:
                paint.setColor(ContextCompat.getColor(context, R.color.block_O));
                break;
            case MainActivity.BACKGROUND:
                paint.setColor(ContextCompat.getColor(context, R.color.background));
                break;
            case MainActivity.BORDER:
                paint.setColor(ContextCompat.getColor(context, R.color.border));
                break;
        }
        // Blocks are given 1 pixel of border for visibility
        if ((map[y][x]!=MainActivity.BORDER)&&(map[y][x]!=MainActivity.BACKGROUND)) {
            canvas.drawRect(x * unitWidth + 1, y * unitHeight + 1, (x + 1) * unitWidth - 1, (y + 1) * unitHeight - 1, paint);
        }else{
            // Border does not have to be aparted
            canvas.drawRect(x * unitWidth, y * unitHeight, (x + 1) * unitWidth, (y + 1) * unitHeight, paint);
        }
    }

}
