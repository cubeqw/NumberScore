package com.cubeqw.numberscore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class MainActivity extends Activity {
    MediaPlayer mp, n,d, b;
    ImageView themeswitch;
    boolean darktheme;
    RelativeLayout main;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    HTextView hTextView;
    HTextView hTextView1;
    int score;
    TextView scoreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mp = MediaPlayer.create(this, R.raw.click);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });
        d = MediaPlayer.create(this, R.raw.default_mode);
        b =MediaPlayer.create(this, R.raw.background);
        d.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });
        n = MediaPlayer.create(this, R.raw.dark_mode);
        n.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        hTextView = (HTextView) findViewById(R.id.text3);
        scoreview=findViewById(R.id.score);
        hTextView1 = (HTextView) findViewById(R.id.appname);
        hTextView.animateText("");
        hTextView.setAnimateType(HTextViewType.SCALE);
        hTextView.animateText(getResources().getText(R.string.play));
        hTextView1.animateText("");
        hTextView1.setAnimateType(HTextViewType.TYPER);
        hTextView1.animateText(getResources().getText(R.string.app_name));
        main=findViewById(R.id.main);
        themeswitch=findViewById(R.id.theme);
        settings = getApplicationContext().getSharedPreferences("theme", Context.MODE_PRIVATE);
        darktheme=settings.getBoolean("theme", false);
        score=settings.getInt("score", 0);
        if(score!=0){
            scoreview.setText(getResources().getString(R.string.save_score)+score);
        }
        if(darktheme){
                main.setBackgroundColor(Color.parseColor("#404040"));
            Drawable themeicon = getResources().getDrawable(R.drawable.light);
                themeswitch.setImageDrawable(themeicon);
            }
        b.setLooping(true);
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
        Intent intent=new Intent(this, ChangeLevel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
            }
            public void onClickLogo(View v){
                if (mp.isPlaying())
                {
                    mp.seekTo(0);
                }
                else
                {
                    mp.start();
                }
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim));
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.github.com/cubeqw"));
                startActivity(browserIntent);

            }
            public void onClickTheme(View v){
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim));
                if(!darktheme){
                    if (n.isPlaying())
                    {
                        n.seekTo(0);
                    }
                    else
                    {
                        n.start();
                    }
                    main.setBackgroundColor(Color.parseColor("#404040"));
                Drawable themeicon = getResources().getDrawable(R.drawable.light);
                themeswitch.setImageDrawable(themeicon);
                darktheme=true;
                editor=settings.edit();
                editor.putBoolean("theme", true);
                editor.commit();}
                else{
                    if (d.isPlaying())
                    {
                        d.seekTo(0);
                    }
                    else
                    {
                        d.start();
                    }
                    main.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    Drawable themeicon = getResources().getDrawable(R.drawable.dark);
                    themeswitch.setImageDrawable(themeicon);
                    darktheme=false;
                    editor=settings.edit();
                    editor.putBoolean("theme", false);
                    editor.commit();}
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
    public void onDestroy() {
        super.onDestroy();
        //останавливаешь плеер при закрытии приложения
        b.stop();
    }

    @Override
    protected void onPause() {
        b.pause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        b.start();
        super.onResume();
    }

}