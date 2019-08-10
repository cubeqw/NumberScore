package com.cubeqw.numberscore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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

public class ChangeLevel extends Activity {
    SharedPreferences settings;
    boolean darktheme;
    RelativeLayout main;
    HTextView e, m, h, t;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mp = MediaPlayer.create(this, R.raw.click);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_change_level);
        main=findViewById(R.id.main);
        settings = getApplicationContext().getSharedPreferences("theme", Context.MODE_PRIVATE);
        darktheme=settings.getBoolean("theme", false);
        if(darktheme){
            main.setBackgroundColor(Color.parseColor("#404040"));
        }
        t=findViewById(R.id.change_level);
        e=findViewById(R.id.easy_button);
        m=findViewById(R.id.medium_button);
        h=findViewById(R.id.hard_button);
        e.animateText("");
        e.setAnimateType(HTextViewType.SCALE);
        e.animateText(getResources().getText(R.string.easy));
        m.animateText("");
        m.setAnimateType(HTextViewType.SCALE);
        m.animateText(getResources().getText(R.string.medium));
        h.animateText("");
        h.setAnimateType(HTextViewType.SCALE);
        h.animateText(getResources().getText(R.string.hard));
        t.animateText("");
        t.setAnimateType(HTextViewType.SCALE);
        t.animateText(getResources().getText(R.string.change_level));
    }
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim));
        if (mp.isPlaying())
        {
            mp.seekTo(0);
        }
        else
        {
            mp.start();
        }
switch (v.getId()){
    case R.id.hard_button:
        Intent intent=new Intent(this, Hard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
}
    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
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

}
