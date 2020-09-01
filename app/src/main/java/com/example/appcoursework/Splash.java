package com.example.appcoursework;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcoursework.data.DbHelper;

public class Splash extends AppCompatActivity {
    private static int splashInterval = 5000;
    ImageView imagesplash;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        imagesplash= findViewById(R.id.splashImage);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        imagesplash.setAnimation(fromtop);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {




                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                this.finish();
            }

            private void finish() {

            }
        }, splashInterval);
    }

}
