package com.hongseokandrewjang.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;

public class StageView extends View {

    public final int WIDTH_COUNT = 14;
    public final int HEIGHT_COUNT = 21;
    public static int stageWidth;
    public static int stageHeight;
    public static int unitWidth;
    public static int unitHeight;

    int[][] originalMap;
    int[][] collisionTestMap;
    int[][] collisionSafeMap;
    int stageLevel = 1;
    boolean newBlockCome = false;
    int[][] pastBlockPosition  = new int[2][4];

    Stage stage;
    Block controllingBlock;
    Block nextBlock;
    Handler handler;

    // Get the size of stage
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        stageWidth = this.getWidth();
        unitWidth = stageWidth/WIDTH_COUNT;
        stageHeight = this.getHeight();
        unitHeight = stageHeight/HEIGHT_COUNT;
    }

    public StageView(Context context, int stageLevel, Block controllingBlock, Handler handler) {
        super(context);
        // Set basic properties
        unitWidth = stageWidth/WIDTH_COUNT;
        this.stageLevel = stageLevel;
        this.controllingBlock = controllingBlock;
//        currentBlock = controllingBlock.getCurrentBlock();
        this.handler = handler;
        stage = new Stage(stageLevel);
        originalMap = stage.getCurrentStageMap();
        collisionSafeMap = originalMap;
        collisionTestMap = collisionSafeMap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BlockDrawer blockDrawer;
        super.onDraw(canvas);

        // 1. If there is the controlling block, put it into the map
        if (controllingBlock!=null) {
//            collisionTest();
        }

        // 2. Put the valid map into the blockdrawer(which draws given map into the view)
        if (!newBlockCome) {
            for (int i = 0; i < WIDTH_COUNT; i++) {
                for (int j = 0; j < HEIGHT_COUNT; j++) {
                    blockDrawer = new BlockDrawer(getContext(), unitHeight, unitWidth, collisionSafeMap, i, j, canvas);
                    blockDrawer.drawBlock();
                }
            }
        }
        newBlockCome = false;
    }

    public boolean collisionTest(){
        boolean collision = false;
        int before = 0;
        int after = 0;

        for(int i=0;i<WIDTH_COUNT;i++){
            for (int j=0;j<HEIGHT_COUNT;j++){
                if (collisionSafeMap[j][i]==0)
                    before++;
                for(int k=0;k<4;k++){
                    if ((i==pastBlockPosition[0][k])&&(j==pastBlockPosition[1][k])){
                        collisionTestMap[j][i] = 0;
                    }
                }
            }
        }

        for(int i = controllingBlock.x; i < controllingBlock.x + 4; i++) {
            for (int j = controllingBlock.y; j < controllingBlock.y + 4; j++) {
                if (controllingBlock.getCurrentBlock()[j - controllingBlock.y][i - controllingBlock.x]!=0) {
                    collisionTestMap[j][i] = controllingBlock.getCurrentBlock()[j - controllingBlock.y][i - controllingBlock.x];
                }
            }
        }

        for(int i=0;i<WIDTH_COUNT;i++){
            for (int j=0;j<HEIGHT_COUNT;j++){
                if(collisionTestMap[j][i] == 0){
                    after++;
                }
            }
        }

        if (after==before){
            collisionSafeMap = collisionTestMap;
            return false;
        }else{
            handler.sendEmptyMessage(MainActivity.NEW_BLOCK);
            collisionTestMap = collisionSafeMap;
        }

        return collision;
    }

    public void saveCurrentBlockPosition(){
        int count = 0;
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if (controllingBlock.getCurrentBlock()[j][i]!=0){
                    pastBlockPosition[0][count] = controllingBlock.x+i;
                    pastBlockPosition[1][count] = controllingBlock.y+j;
                    count++;
                }
            }
        }
    }

    public void openOldBlock(){

    }

    public void moveRight(){
        saveCurrentBlockPosition();
        controllingBlock.x = controllingBlock.x+ 1;
        if (!collisionTest()){
            controllingBlock.x = controllingBlock.x- 1;
        }
        handler.sendEmptyMessage(MainActivity.MOVE_BLOCK);
    }

    public void moveLeft(){
        saveCurrentBlockPosition();
        controllingBlock.x = controllingBlock.x - 1;
        if (!collisionTest()){
            controllingBlock.x = controllingBlock.x + 1;
        }
        handler.sendEmptyMessage(MainActivity.MOVE_BLOCK);
    }

    public void moveDown(){
        saveCurrentBlockPosition();
        controllingBlock.y = controllingBlock.y + 1;
        if (!collisionTest()){
            controllingBlock.y = controllingBlock.y - 1;
        }
        handler.sendEmptyMessage(MainActivity.MOVE_BLOCK);
    }

    public void moveTurn(){
        saveCurrentBlockPosition();
        controllingBlock.rotateBlock();
        if (!collisionTest()){
            controllingBlock.unRotateBlock();
        }
        handler.sendEmptyMessage(MainActivity.MOVE_BLOCK);
    }

}
