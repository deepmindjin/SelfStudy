package com.hongseokandrewjang.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Question> questionBank = new ArrayList<>();
    String currentQuestion;
    Boolean currentAnswer = false;
    Boolean checkCheating = false;
    TextView questionView;
    int currentIndex = 0;

    @Override
    protected void onStart() {
        super.onStart();
        // 문제를 만듭니다
        addQuestion("태평양은 대서양보다 더 크다",true);
        addQuestion("수에즈 운하는 홍해와 인도양을 연결한다",false);
        addQuestion("나일강은 이집트에서 시작된다",false);
        addQuestion("아마존강은 아메리카 대륙에서 가장 긴 강이다",true);
        addQuestion("바이칼 호수는 세계에서 가장 오래되고 가장 깊은 담수호이다",true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionView = (TextView)findViewById(R.id.quizText);
        Button trueBtn = (Button)findViewById(R.id.trueBtn);
        Button falseBTn = (Button)findViewById(R.id.falseBtn);
        Button cheatingBtn = (Button)findViewById(R.id.cheatingBtn);
        ImageButton goBeforeBtn = (ImageButton)findViewById(R.id.goBeforeBtn);
        ImageButton goAfterBtn = (ImageButton)findViewById(R.id.goAfterBtn);

        // 버튼에 onClickListener를 달아줍니다
        goAfterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = currentIndex + 1;
                if(currentIndex<questionBank.size())
                    changeQuestion(currentIndex);
                else {
                    currentIndex = currentIndex - 1;
                }
            }
        });

        goBeforeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = currentIndex - 1;
                if(currentIndex >= 0)
                    changeQuestion(currentIndex);
                else {
                    currentIndex = currentIndex + 1;
                }
            }
        });

        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnswer == true){
                    didCheating();
                    Toast.makeText(MainActivity.this, "정답입니다", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "틀렸습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        falseBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnswer == false){
                    didCheating();
                    Toast.makeText(MainActivity.this, "정답입니다", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "틀렸습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cheatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cheatingAnswer;
                if (currentAnswer == true){
                    cheatingAnswer = "True";
                }else{
                    cheatingAnswer = "False";
                }
                Toast.makeText(MainActivity.this, "답은 " + cheatingAnswer+" 입니다.", Toast.LENGTH_SHORT).show();
                checkCheating = true;
            }
        });


    }

    private void addQuestion(String question, Boolean answer){
        Question newQuestion = new Question(question,answer);
        questionBank.add(newQuestion);
    }
    private void didCheating(){
        if(checkCheating){
            Toast.makeText(MainActivity.this, "힌트를 보시면 안됩니다~~", Toast.LENGTH_LONG).show();
        }
    }

    private void changeQuestion(int currentIndex){
        Question question = questionBank.get(currentIndex);
        questionView.setText(question.question);
        currentAnswer = question.answer;
        currentQuestion = question.question;
    }
}

