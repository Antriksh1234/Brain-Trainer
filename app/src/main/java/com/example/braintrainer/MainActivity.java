package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    androidx.gridlayout.widget.GridLayout gridLayout;
    TextView timer,question,score,rightWrongTextView;
    Button Go;
    int number1,number2,right,total;
    int rightOption;

    public void displayfinalScore()
    {
        rightWrongTextView.setText("Your score : "+right+"/"+total);
        Button playagain = findViewById(R.id.playagain);
        playagain.setVisibility(View.VISIBLE);
        Button option1 = findViewById(R.id.option1);
        Button option2 = findViewById(R.id.option2);
        Button option3 = findViewById(R.id.option3);
        Button option4 = findViewById(R.id.option4);
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);

    }
    public void playAgain(View view)
    {
        Button play = (Button)view;
        play.setVisibility(View.INVISIBLE);
        Button option1 = findViewById(R.id.option1);
        Button option2 = findViewById(R.id.option2);
        Button option3 = findViewById(R.id.option3);
        Button option4 = findViewById(R.id.option4);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        right=0;
        total=0;
        rightWrongTextView.setText("");
        setup();
        score.setText("0/0");
        countDownTimer.onTick(5000);
        countDownTimer.start();
    }
    public void startgame(View view)
    {
        Go.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        rightWrongTextView.setVisibility(View.VISIBLE);
        rightWrongTextView.setText("");
        setCountDownTimer();
        setup();
    }

   public void setCountDownTimer()
   {

       countDownTimer = new CountDownTimer(5000,1000) {

           MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.clock);
           @Override
           public void onTick(long millisUntilFinished) {
               if(total==30){
                   cancel();
                   displayfinalScore();
               }
               else {
                   timer.setText(millisUntilFinished / 1000 + "s");

               }
           }

           @Override
           public void onFinish() {
                total++;
                score.setText(right+"/"+total);
                timer.setText("0s");
                if(total==30) {
                    cancel();
                    displayfinalScore();
                }
                else
                    setup();
           }
       };
   }

    public void setup()
    {
        int result;
        int minusOrPlusTurn = new Random().nextInt(10);
        number1 = new Random().nextInt(100)+142;
        number2 = new Random().nextInt(100)+50;
        rightOption = new Random().nextInt(4)+1;
        if(minusOrPlusTurn%2==0) {
            question.setText(number1 + " + " + number2);
            result = number1 + number2;
        }
        else
        {
            result = number1 - number2;
            question.setText(number1 + " - " + number2);
        }
        Button option1 = findViewById(R.id.option1);
        Button option2 = findViewById(R.id.option2);
        Button option3 = findViewById(R.id.option3);
        Button option4 = findViewById(R.id.option4);
        if(rightOption==1) {
            option1.setText(result+"");
            int extra = new Random().nextInt(5)+1;
            option2.setText(result-10+"");
            option3.setText(result+10+"");
            option4.setText(result+extra*2+"");
        }
        else if(rightOption==2){
            option2.setText(result+"");
            int extra = new Random().nextInt(5)+1;
            option1.setText(result+extra+1+"");
            option3.setText(result+ extra+2+"");
            option4.setText(result+10+"");
        }
        else if(rightOption==3) {
            option3.setText(result+"");
            int extra = new Random().nextInt(5)+1;
            option2.setText(result+extra+1+"");
            option1.setText(result-10+"");
            option4.setText(result+10+"");
        }
        else {
            option4.setText(result+"");
            int extra = new Random().nextInt(5)+1;
            option2.setText(result-10+"");
            option3.setText(result+ extra+2+"");
            option1.setText(result+10+"");
        }
        countDownTimer.onTick(5000);
        countDownTimer.start();
    }


    public void checkQuestion(View view)
    {
        Button pressed = (Button)view;
        if(Integer.parseInt((String)pressed.getTag())==rightOption) {
            right++;
            rightWrongTextView.setText("Right!!!");
        }
        else
            rightWrongTextView.setText("Wrong:(");
        total++;
        score.setText(right+"/"+total);
        setup();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridlayout);
        gridLayout.setVisibility(View.INVISIBLE);

        timer = findViewById(R.id.timer);
        timer.setVisibility(View.INVISIBLE);

        question = findViewById(R.id.question);
        question.setVisibility(View.INVISIBLE);

        score = findViewById(R.id.score);
        score.setVisibility(View.INVISIBLE);

        rightWrongTextView = findViewById(R.id.rightWrongTextView);
        rightWrongTextView.setVisibility(View.INVISIBLE);

        Go = findViewById(R.id.GoButton);
    }
}
