package com.hongseokandrewjang.android.threadexcercise_teteris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final static int GROUND_LIMIT = 20;
    private int groundUnit = 0;
    private int unit = 0;
    private int playerX = 0;
    private int playerY = 0;
    private boolean gameStart = false;
    private boolean needNewBlock = false;

    private final static int blockI = 1;
    private final static int blockL = 2;
    private final static int blockJ = 3;
    private final static int blockO = 4;
    private final static int blockS = 5;
    private final static int blockT = 6;
    private final static int blockZ = 7;
    private static int map[][] = new int[GROUND_LIMIT][GROUND_LIMIT];

    Block player;
    CustomView customView;
    FrameLayout playGround;
    Button btnDown;
    Button btnLeft;
    Button btnRight;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dp = getResources().getDisplayMetrics();
        groundUnit = dp.widthPixels;
        unit = groundUnit / GROUND_LIMIT;

        playGround = (FrameLayout)findViewById(R.id.playGround);
        btnDown = (Button)findViewById(R.id.btnDown);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        btnStart = (Button)findViewById(R.id.btnStart);
        playGround.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        customView = new CustomView(this);
        playGround.addView(customView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playGround:
                turnBlock();
                break;
            case R.id.btnDown:
                playerY = playerY - 1;
                break;
            case R.id.btnLeft:
                playerX = playerX - 1;
                break;
            case R.id.btnRight:
                playerX = playerX + 1;
                break;
            case R.id.btnStart:
                customView.blocks.clear();
                gameStart = true;
                gameTerminator();
                break;
        }
        customView.invalidate();
    }

    public void gameTerminator() {
        while (gameStart) {
            try {
                if (needNewBlock) {
                    int random = (int) (Math.random() * 7 + 1);
                    playerX = GROUND_LIMIT / 2 - 2;
                    playerY = 0;
                    player = new Block(customView, random);
                    player.start();
                } else {

                }

                for (Block block : customView.blocks) {
                    if (!block.stuckByAnotherBlock) {

                    }
                }

                Thread.sleep(100);
                customView.invalidate();
            } catch (Exception e) {

            }
        }
    }

    public void turnBlock(){

    }

    class Block extends Thread{
        int bottomTiles[][] = new int[4][4];
        boolean stuckByAnotherBlock = false;
        CustomView customView;
        int angle = 0;
        int type = 0;
        // 맨위 중앙에서 시작(시작점을 중심으로 오른쪽으로 그리기 때문에 최대 3개까지 그려지는 블럭들을 위하여 -2 를 하였다)
        int x = 0;
        int y = 0;

        public Block(CustomView customView, int type){
            this.customView = customView;
            this.type = type;
            setBottomTiles(type);
            customView.add(this);
        }

        public void setBottomTiles(int type){
            switch (type){
                case blockI:
                    if(angle%90==0) {
                        bottomTiles[3][0] = 1;
                    }else{
                        bottomTiles[0][0] = 1;
                        bottomTiles[0][1] = 1;
                        bottomTiles[0][2] = 1;
                        bottomTiles[0][3] = 1;
                    }
                    break;
                case blockJ:
                    if(angle%360==0){
                        bottomTiles[2][0] = 1;
                        bottomTiles[2][1] = 1;
                    }else if(angle%360==90){
                        bottomTiles[1][0] = 1;
                        bottomTiles[1][1] = 1;
                        bottomTiles[1][2] = 1;
                    }else if(angle%360==180){
                        bottomTiles[2][0] = 1;
                        bottomTiles[0][1] = 1;
                    }else if(angle%360==270){
                        bottomTiles[0][0] = 1;
                        bottomTiles[0][1] = 1;
                        bottomTiles[1][2] = 1;
                    }
                    break;
                case blockL:
                    break;
                case blockO:
                    break;
                case blockS:
                    break;
                case blockZ:
                    break;
                case blockT:
                    break;
            }
        }

        @Override
        public void run() {

        }
    }

    class CustomView extends View{

        Paint paint = new Paint();
        ArrayList<Block> blocks = new ArrayList<>();

        public void add(Block block){
            blocks.add(block);
        }

        public CustomView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 배경그리기
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(0,0,groundUnit, groundUnit, paint);

            // 내가 움직이는 블럭 그리기

            // 블럭 그리기
            for(int i=0;i< GROUND_LIMIT; i++){
                for(int j=0;j<GROUND_LIMIT;j++){
                    if (map[j][i]==1){
                        paint.setColor(Color.MAGENTA);
                        canvas.drawRect(i*unit,j*unit,(i+1)*unit, (j+1)*unit,paint);
                    }else if(map[j][i]==2){
                        paint.setColor(Color.RED);
                        canvas.drawRect(0,0,0,0,paint);
                    }
                }
            }
        }

        public void drawBasicBlock(int startPositionX, int startPositionY, int type, Canvas canvas){





            // 직접그리기
//            switch (type){
//                case blockI:
//                    unitBlock(startPositionX, startPositionY, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+unit, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+2*unit, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+3*unit, canvas, paint);
//                    Log.i("블럭","BLOCK I");
//                    break;
//                case blockJ:
//                    unitBlock(startPositionX+unit, startPositionY, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY+unit, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY+2*unit, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+2*unit, canvas, paint);
//                    Log.i("블럭","BLOCK J");
//                    break;
//                case blockT:
//                    unitBlock(startPositionX, startPositionY, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY, canvas, paint);
//                    unitBlock(startPositionX+2*unit, startPositionY, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY+unit, canvas, paint);
//                    Log.i("블럭","BLOCK T");
//                    break;
//                case blockS:
//                    unitBlock(startPositionX, startPositionY+unit, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY+unit, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+2*unit, canvas, paint);
//                    Log.i("블럭","BLOCK S");
//                    break;
//                case blockL:
//                    unitBlock(startPositionX, startPositionY, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+unit, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+2*unit, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY+2*unit, canvas, paint);
//                    Log.i("블럭","BLOCK L");
//                    break;
//                case blockO:
//                    unitBlock(startPositionX, startPositionY, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+unit, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY+unit, canvas, paint);
//                    Log.i("블럭","BLOCK O");
//                    break;
//                case blockZ:
//                    unitBlock(startPositionX, startPositionY, canvas, paint);
//                    unitBlock(startPositionX, startPositionY+unit, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY+unit, canvas, paint);
//                    unitBlock(startPositionX+unit, startPositionY+2*unit, canvas, paint);
//                    Log.i("블럭","BLOCK Z");
//                    break;
            }
        }
    }
}
