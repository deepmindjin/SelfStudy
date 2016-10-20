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
    int[][] currentBlock;
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
        currentBlock = controllingBlock.getCurrentBlock();
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
            collisionTest();
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

//    public boolean collisionTest(){
//        int isNewBlock = 0;
//        boolean collision = false;
//        int before = 0;
//        int after = 0;
//
//        for (int i=0;i<WIDTH_COUNT;i++){
//            for(int j=0; j<HEIGHT_COUNT;j++){
//                before = before + collisionSafeMap[j][i];
//            }
//        }
//
//        for (int i = controllingBlock.x; i < controllingBlock.x + 4; i++) {
//            for (int j = controllingBlock.y; j < controllingBlock.y + 4; j++) {
//                if (controllingBlock.getCurrentBlock()[j - controllingBlock.y][i - controllingBlock.x]!=0) {
//                    collisionTestMap[j][i] = controllingBlock.getCurrentBlock()[j - controllingBlock.y][i - controllingBlock.x];
//                    isNewBlock++;
//                }
//            }
//        }
//
//        for (int i=0;i<WIDTH_COUNT;i++){
//            for(int j=0; j<HEIGHT_COUNT;j++){
//                after = after + collisionTestMap[j][i];
//            }
//        }
//
//        if (isNewBlock!=4){
//            controllingBlock.alive = false;
//            handler.sendEmptyMessage(MainActivity.NEW_BLOCK);
//            newBlockCome = false;
//        }else{
//            collisionSafeMap = collisionTestMap;
//        }
//
//        return true;
//    }

    public boolean collisionTest(){
        boolean collision = false;
        int difference = 0;a

        for (int i = controllingBlock.x; i < controllingBlock.x + 4; i++) {
            for (int j = controllingBlock.y; j < controllingBlock.y + 4; j++) {
                if (controllingBlock.getCurrentBlock()[j - controllingBlock.y][i - controllingBlock.x]!=0) {
                    collisionTestMap[j][i] = controllingBlock.getCurrentBlock()[j - controllingBlock.y][i - controllingBlock.x];
                }
            }
        }

        for(int i=0;i<WIDTH_COUNT;i++){
            for (int j=0;j<HEIGHT_COUNT;j++){

                if (collisionSafeMap[j][i]!=collisionTestMap[j][i]){
                    difference++;
                }
            }
        }

        if (difference==4){
            newBlockCome = true;
            return false;
        }

        collisionSafeMap = collisionTestMap;





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



    public void moveRight(){
        saveCurrentBlockPosition();
        controllingBlock.x += 1;
        if (!collisionTest()){
            controllingBlock.x -= 1;
        }
        handler.sendEmptyMessage(MainActivity.MOVE_BLOCK);
    }

    public void moveLeft(){
        saveCurrentBlockPosition();
        controllingBlock.x -= 1;
        if (!collisionTest()){
            controllingBlock.x += 1;
        }
        handler.sendEmptyMessage(MainActivity.MOVE_BLOCK);
    }

    public void moveDown(){
        saveCurrentBlockPosition();
        controllingBlock.y += 1;
        if (!collisionTest()){
            controllingBlock.y -= 1;
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

//    public boolean collisionCheck(){
//        boolean result = false;
//        for(int i=0 ;i < width ; i++){
//            for(int j=0;j < height ; j++){
//                // 현재 블럭의 셀의 값을 가져온다
//                int cBlockValue = block[j][i];
//                if( cBlockValue > 0){ // 현재 블럭 셀의 값이 0보다 클경우만 충돌체크를 한다
//                    // 이동한곳의 stage 셀값과 block 의 셀값을 더한다
//                    int sum = cBlockValue + Stage.stageMap[y+j][x+i];
//                    if(sum > cBlockValue){ // 두개 셀을 더한값이 블럭셀의 값보다 크면 충돌
//                        return true;
//                    }
//                }
//            }
//        }
//        return result;
//    }
}
