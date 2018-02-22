package com.example.firmanvsly.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Activity extends AppCompatActivity {
    ImageView btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        btn1 = (ImageView) findViewById(R.id.btnHal1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(getApplicationContext(), Halaman1.class);
                startActivity(i);
            }
        });
        btn2 = (ImageView) findViewById(R.id.btnHal2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
        btn3 = (ImageView) findViewById(R.id.btnHal3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(getApplicationContext(), About.class);
                startActivity(i);
            }
        });
    }
}
