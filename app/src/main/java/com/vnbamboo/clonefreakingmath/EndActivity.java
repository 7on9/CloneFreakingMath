package com.vnbamboo.clonefreakingmath;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        getSupportActionBar().hide();

        TextView txtResult = (TextView) findViewById(R.id.txtResult);
        txtResult.setText("Your point " + getIntent().getStringExtra("result"));

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
