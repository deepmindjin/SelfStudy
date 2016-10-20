package com.hongseokandrewjang.android.threadbasic_tetris;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int BACKGROUND = 0;
    public static final int BLOCK_I = 1;
    public static final int BLOCK_J = 2;
    public static final int BLOCK_L = 3;
    public static final int BLOCK_S = 4;
    public static final int BLOCK_Z = 5;
    public static final int BLOCK_T = 6;
    public static final int BLOCK_O = 7;
    public static final int BORDER = 9;

    public static final int BLOCK_GOING_DOWN = 1;
    public static final int NEW_BLOCK = 2;
    public static final int MOVE_BLOCK = 3;
    public static final int GAME_OVER = 4;

    public static boolean needNewBlock = false;
    public static boolean gameStart = false;


    FrameLayout ground, preview;
    Button btnDown, btnLeft, btnRight, btnTurn, btnStart;
    StageView stageView;
    PreviewView previewView;
    Stage stage;

    int stageLevel = 1;
    int stageMap[][];

    Block controllingBlock;
    Block nextBlock;
    BlockFactory blockFactory;
    ScreenDrawer screenDrawer;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BLOCK_GOING_DOWN:
                    stageView.moveDown();
                    stageView.postInvalidate();
                    break;
                case NEW_BLOCK:
                    stageView.newBlockCome = true;
                    stageView.controllingBlock = nextBlock;
                    stageView.controllingBlock.alive = true;
                    nextBlock = blockFactory.getRandomBlock();
                    stageView.postInvalidate();
                    previewView.postInvalidate();
                    break;
                case MOVE_BLOCK:
                    stageView.postInvalidate();
                    break;
                case GAME_OVER:
                    gameStart = false;
                    Toast.makeText(getBaseContext(),"게임끝!",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDown = (Button)findViewById(R.id.btnDown);
        btnDown.setOnClickListener(this);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(this);
        btnRight = (Button)findViewById(R.id.btnRight);
        btnRight.setOnClickListener(this);
        btnTurn = (Button)findViewById(R.id.btnTurn);
        btnTurn.setOnClickListener(this);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        // Make initiate blockfactory
        blockFactory = new BlockFactory(handler);

        // Set the Map by putting stage level
        ground = (FrameLayout)findViewById(R.id.ground);
        preview = (FrameLayout)findViewById(R.id.preview);
        setStage(stageLevel);

        // Construct new View objectives
        stageView = new StageView(this,stageLevel,controllingBlock,handler);
        previewView = new PreviewView(this,nextBlock);

        // Add view to framelayout
        ground.addView(stageView);
        preview.addView(previewView);

        // Set the screendrawer(Thread)
        screenDrawer = new ScreenDrawer(handler, stageView, previewView);

    }

    public void setStage(int stageLevel){
        stage = new Stage(stageLevel);
        stageMap = stage.getCurrentStageMap();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDown:
                stageView.moveDown();
                break;
            case R.id.btnLeft:
                stageView.moveLeft();
                break;
            case R.id.btnRight:
                stageView.moveRight();
                break;
            case R.id.btnTurn:
                stageView.moveTurn();
                break;
            case R.id.btnStart:
                startGame();
                break;
        }
        handler.sendEmptyMessage(MOVE_BLOCK);
    }

    public void startGame(){
        if (!gameStart) {
            // Set new block to stage and preview
            controllingBlock = blockFactory.getRandomBlock();
            nextBlock = blockFactory.getRandomBlock();
            previewView.nextBlock = nextBlock;
            stageView.controllingBlock = controllingBlock;
            stageView.nextBlock = nextBlock;
            // Make the controlling block move;
            stageView.controllingBlock.alive = true;
            stageView.controllingBlock.start();
            stageView.newBlockCome = true;
            handler.sendEmptyMessage(NEW_BLOCK);
            screenDrawer.start();
            gameStart = true;
        }
    }

    public void newBlock(){
        controllingBlock = nextBlock;
        nextBlock = blockFactory.getRandomBlock();
        stageView.controllingBlock = controllingBlock;
        stageView.controllingBlock.alive = true;
        stageView.nextBlock = nextBlock;
        stageView.controllingBlock.start();
    }

}
