package com.hongseokandrewjang.android.sachunsung;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;

import java.util.ArrayList;

public class GamePlay extends AppCompatActivity {

    Button start;
    Boolean startFlag = false;
    GridLayout gridView;
    ArrayList<Button> compareNum = new ArrayList<>();
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        start = (Button) findViewById(R.id.btnStart);
        gridView = (GridLayout) findViewById(R.id.grid);
        chronometer = (Chronometer) findViewById(R.id.timer);

        // Chronometer Setting
        long currentTime = SystemClock.elapsedRealtime();



        // Setting the Click Listener on start button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!startFlag) {
                    startFlag = true;
                    for (int i = 0; i < 32; i++) {
                        Button button = new Button(GamePlay.this);
                        button.setText(getRandom());
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                compareNum.add((Button) view);
                                if (compareNum.size() == 2) {
                                    if (compareNum.get(0).getText().equals(compareNum.get(1).getText())) {
                                        compareNum.get(0).setVisibility(View.INVISIBLE);
                                        compareNum.get(1).setVisibility(View.INVISIBLE);
                                    }
                                    compareNum.clear();
                                }
                            }
                        });
                        gridView.addView(button);
                    }
                }
            }
        });



    }

    public String getRandom() {
        String result;
        int ranNum = (int) (Math.random() * 10);
        result = String.valueOf(ranNum);
        return result;
    }
}
