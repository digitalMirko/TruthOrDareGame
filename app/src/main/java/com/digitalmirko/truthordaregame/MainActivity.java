package com.digitalmirko.truthordaregame;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private Random random = new Random();
    private int lastDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
    }

    public void spinBottle(View view){

        int newDirection = random.nextInt(4200);
        // pivot access x, y
        float pivotX = imageView.getWidth()/2;
        float pivotY = imageView.getHeight()/2;

        Animation rotate = new RotateAnimation(lastDirection, newDirection, pivotX, pivotY);

        // spin duration - 3 seconds
        rotate.setDuration(3000);
        rotate.setFillAfter(true);

        // disable button while bottle is spinning
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                button.setEnabled(false);
                button.getBackground().setColorFilter(0xFFAA4400, PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                button.setEnabled(true);
                button.getBackground().setColorFilter(0xFF74B9FF, PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // use last direction as new starting point for animation
        lastDirection = newDirection;

        // start animation
        imageView.startAnimation(rotate);
    }
}
