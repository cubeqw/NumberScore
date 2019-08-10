package com.cubeqw.numberscore;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

import java.io.IOException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Hard extends Activity {
    SharedPreferences settings;
    RelativeLayout main;
    boolean darktheme;
    private Timer timer;
    HTextView bt1, bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
    MediaPlayer mp, p,g;

    int time=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hard);
        main=findViewById(R.id.main);
        mp = MediaPlayer.create(this, R.raw.timer);
        p= MediaPlayer.create(this, R.raw.positive_click);
        g= MediaPlayer.create(this, R.raw.game_over);
        bt1=findViewById(R.id.hb1);
        settings = getApplicationContext().getSharedPreferences("theme", Context.MODE_PRIVATE);
        darktheme=settings.getBoolean("theme", false);
        if(darktheme){
            main.setBackgroundColor(Color.parseColor("#404040"));
        }
        startTimer();
        buttonChange();
    }
    public void onClick(View v){
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim));
        switch(v.getId()){
            case R.id.hb1:
                if (g.isPlaying())
                {
                    g.seekTo(0);
                }
                else
                {
                    g.start();
                }    break;
                default:
        if (p.isPlaying())
        {
            p.seekTo(0);
        }
        else
        {
            p.start();
        }    }}
    public void startTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1; i++) {
                            int d=1;
                            if (d==1){
                                mp.start();
                            d=2;}
                            }
                            if (time > -1){
                                time -= 1;
                            }
                            else {
                                int d=1;
                                if (d==1){
                                    mp.stop();
                                    d=2;}

                            }

                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, getResources().getString(R.string.exit),
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
    public void buttonChange(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            bt1.setBackground(getResources().getDrawable(R.drawable.gradiant));
        }
    }

    @Override
    protected void onPause() {
        mp.pause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mp.start();
        super.onResume();
    }
}
