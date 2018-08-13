package com.vnbamboo.clonefreakingmath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity{
    int number1, number2, res, point, highScore;
    ProgressBar pbTimer;
    countDown countDownTimer;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getSupportActionBar().hide();
        pbTimer = (ProgressBar) findViewById(R.id.pbTimer);
        point = 0;
        play();

    }
    public void openEndAvtivity(boolean isOverTime){
        countDownTimer.cancel();
        updateScore();
        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra("result", toString().valueOf(point - 1));
        intent.putExtra("highScore", toString().valueOf(highScore));
        if(isOverTime)
            intent.putExtra("status", "TIME OUT!");
        else intent.putExtra("status", "GAME OVER!");
        super.startActivity(intent);
    }
    public int randomNumber(int a, int b){
        return ((int) ((Math.random() * 100 + a) % b + 1));
    }
    public void play(){
        countDownTimer = new countDown(1500, 1);
        countDownTimer.start();
        point++;
        res = 0;
        number1 = randomNumber(1, 20);
        number2 = randomNumber(1, 20);
        switch (randomNumber(1,2)){
            case 1: res = number1 + number2;
                break;
            default: res = number1 + number2 + randomNumber(-2,2);
                break;
        }
        TextView txtNumber1 = (TextView) findViewById(R.id.txtNumber1);
        TextView txtNumber2 = (TextView) findViewById(R.id.txtNumber2);
        TextView txtRes = (TextView) findViewById(R.id.txtRes);
        TextView txtPoint = (TextView) findViewById(R.id.txtPoint);

        txtNumber1.setText((toString().valueOf(number1)));
        txtNumber2.setText(toString().valueOf(number2));
        txtRes.setText(toString().valueOf(res));
        txtPoint.setText(toString().valueOf(point-1));

        Button btnWrong = (Button) findViewById(R.id.btnWrong);
        Button btnCorrect = (Button) findViewById(R.id.btnCorrect);

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if(isCorrect()) {
                    openEndAvtivity(false);
                }
                else {
                    countDownTimer.cancel();
                    play();
                }
            }
        });
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if(isCorrect()) {
                    countDownTimer.cancel();
                    play();
                }
                else {
                    openEndAvtivity(false);
                }
            }
        });

    }
    public boolean isCorrect(){
        if(res == number1 + number2) return true;
        return false;
    }
    public class countDown extends CountDownTimer{
        public countDown(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick( long millisUntilFinished ) {
            pbTimer.setProgress((int) (millisUntilFinished/10));
        }

        @Override
        public void onFinish() {
            openEndAvtivity(true);
        }
    }
    private void updateScore(){
        SharedPreferences prefs = getSharedPreferences("myHighScore", MODE_PRIVATE);
        highScore = prefs.getInt("highScore", 0);

        if(highScore < (point - 1)) {
            highScore = point - 1;
            SharedPreferences preferences = getSharedPreferences("myHighScore", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highScore", highScore);
            editor.commit();
        }
    }
}
