package com.example.firmanvsly.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Pilih extends AppCompatActivity{


    ImageView img_logout,img1,img2,img3;
    String id, username;
    TextView txt_user;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih);

        img1 = (ImageView) findViewById(R.id.btnHal1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(getApplicationContext(), Halaman1.class);
                startActivity(i);
            }
        });
        txt_user = (TextView) findViewById(R.id.welcome);
        img_logout = (ImageView) findViewById(R.id.btnHal4);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);
        txt_user.setText("Anda Login Sebagai : " + username);
        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();
                Intent intent = new Intent(Pilih.this, Activity.class);
                finish();
                startActivity(intent);
            }
        });

        img2 = (ImageView) findViewById(R.id.btnHal2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(getApplicationContext(), Pesan.class);
                startActivity(i);
            }
        });

        img3 = (ImageView) findViewById(R.id.btnHal3);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(getApplicationContext(), About.class);
                startActivity(i);
            }
        });

        /*btn4 = (ImageView) findViewById(R.id.btnHal4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(getApplicationContext(), Halaman4.class);
                startActivity(i);
            }
        });*/
    }
}
