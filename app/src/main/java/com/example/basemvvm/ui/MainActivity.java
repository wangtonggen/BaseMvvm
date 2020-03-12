package com.example.basemvvm.ui;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseSwipeBackLeftActivity;


public class MainActivity extends BaseSwipeBackLeftActivity {
    TextView textView;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = (TextView) findViewById(R.id.tv);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAnimation(R.color.colorAccent);
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void starAnimation(int color) {
        Animator anim = ViewAnimationUtils.createCircularReveal(textView, textView.getWidth() / 2, textView.getHeight() / 2, 0, textView.getWidth() / 2.0f);
        textView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, color));
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }

    private void closeAnimation(final int color) {
        Animator anim = ViewAnimationUtils.createCircularReveal(textView, textView.getWidth() / 2, textView.getHeight(), textView.getWidth() / 2.0f, 0);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                starAnimation(color);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }
}
