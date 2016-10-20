package com.hongseokandrewjang.android.shoottheballoon;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int GROUND_LIMIT = 20;
    private int groundSize = 0;
    public int unit = 0;
    public boolean gameOver = false;
    int radius = 0;

    Balloon balloon;
    CustomView customView;
    FrameLayout playground;
    Button btnRight, btnLeft, btnStart, btnShoot;

    private int playerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dp =getResources().getDisplayMetrics();
        groundSize = dp.widthPixels;
        unit = groundSize/GROUND_LIMIT;
        playerPosition = groundSize/2-unit;
        radius = unit;

        btnRight = (Button)findViewById(R.id.btnRight);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnShoot = (Button)findViewById(R.id.btnShoot);
        btnStart = (Button)findViewById(R.id.btnStart);
        playground = (FrameLayout)findViewById(R.id.playground);
        btnRight.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnShoot.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        customView = new CustomView(this);
        balloon = new Balloon(customView);
        playground.addView(customView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLeft:
                if (playerPosition - unit >0)
                playerPosition = playerPosition - unit;
                break;
            case R.id.btnRight:
                if (playerPosition + unit*2<groundSize)
                playerPosition = playerPosition + unit;
                break;
            case R.id.btnShoot:
                new Bullet(customView).start();
                break;
            case R.id.btnStart:
                new Balloon(customView).start();
//                gameStart();
                break;
        }
        customView.postInvalidate();
    }

//    public void gameStart(){
//        balloon = new Balloon(customView);
//        balloon.start();
//        CollisionTest test = new CollisionTest(balloon, customView.bullets,this);
//        test.run();
//        customView.bullets.clear();
//    }

    class CollisionTest extends Thread {
        Context context;
        Balloon balloon;
        ArrayList<Bullet> bullets = new ArrayList<>();

        public CollisionTest(Balloon balloon, ArrayList<Bullet> bullets, Context context) {
            this.balloon = balloon;
            this.bullets = bullets;
            this.context = context;
        }

        public void check() {
            double distance = 0;
            for(Bullet bullet : bullets) {
                distance = Math.pow(balloon.position - bullet.x,2)+Math.pow(bullet.y,2);
                if (distance<radius){
                    gameOver = true;
                    break;
                }
            }
        }

        @Override
        public void run() {
            while (!gameOver) {
                try {
                    this.check();
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
            Toast.makeText(context, "게임끝!", Toast.LENGTH_LONG).show();
        }
    }

    class Balloon extends Thread{
        int position = 0;
        CustomView customView;

        public Balloon(CustomView customView){
            this.customView = customView;
            customView.setBalloon(this);
            position = groundSize/2-unit;
        }

        @Override
        public void run() {
            while(!gameOver){
                moveBalloon();
                customView.postInvalidate();
                try{
                    Thread.sleep(100);
                }catch (Exception e){
                }
            }
        }

        private void moveBalloon(){
            Random random = new Random();
            int randomMove = random.nextInt(3) - 1;
            int nextPosition = position + unit*randomMove;
            if ((unit<=nextPosition)&&(nextPosition<=groundSize-unit)) {
                position = nextPosition;
            }
        }
    }

    class Bullet extends Thread {
        int x = 0;
        int y = groundSize;
        CustomView customView;

        public Bullet(CustomView customView) {
            this.customView = customView;
            x = playerPosition;
            customView.add(this);
        }

        @Override
        public void run() {
            while (y - 10 >= 0) {
                y = y - 10;
                customView.postInvalidate();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
            customView.bullets.remove(this);
        }
    }

    class CustomView extends View{
        Balloon balloon;
        ArrayList<Bullet> bullets = new ArrayList<>();
        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);
        }

        public void setBalloon(Balloon balloon){
            this.balloon = balloon;
        }

        public void add(Bullet bullet){
            bullets.add(bullet);
        }

//        public void collisionTest(){
//            for (Bullet bullet : bullets){
//                double distance = Math.pow(bullet.x - balloon.position,2) + Math.pow(bullet.y,2);
//                if(distance<radius){
//                    gameOver = true;
//                }
//            }
//        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 배경그리기
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(0,0,groundSize,groundSize,paint);

            // 풍선 그리기
            paint.setColor(Color.CYAN);
            canvas.drawCircle(balloon.position,radius,radius,paint);

            // 플레이어 그리기
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(playerPosition-unit,groundSize-unit,playerPosition,groundSize,paint);

            // 총알 그리기
            paint.setColor(Color.RED);
            for(Bullet bullet : bullets){
                canvas.drawCircle(bullet.x, bullet.y, radius/4,paint);
            }
        }

    }
}



