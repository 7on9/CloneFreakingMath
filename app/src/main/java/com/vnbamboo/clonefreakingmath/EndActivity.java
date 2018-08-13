package com.vnbamboo.clonefreakingmath;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EndActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        getSupportActionBar().hide();

        TextView txtResult = (TextView) findViewById(R.id.txtResult);
        txtResult.setText("Your point " + getIntent().getStringExtra("result"));

        TextView txtHighScore = (TextView) findViewById(R.id.txtHighScore);
        txtHighScore.setText("High Score " + getIntent().getStringExtra("highScore"));

        TextView txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtStatus.setText(getIntent().getStringExtra("status"));
        Button btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                openMainActivity();
            }
        });


    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}
