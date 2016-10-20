package com.hongseokandrewjang.android.viewbasic_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new CustomView(this));
    }
}

class CustomView extends View {

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.RED);
        if((x>=0)&&(y>=0)) {
            canvas.drawCircle(x, y, rad, paint);
        }
    }

    int x = -1;
    int y = -1;
    int rad = 100;
    Paint paint = new Paint();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                x = (int)event.getX();
                y = (int)event.getY();
                invalidate();
                break;
        }
        return true;
    }

    public CustomView(Context context) {
        super(context);
    }

}