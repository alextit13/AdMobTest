package com.admob.android.admobtest.animation.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.admob.android.admobtest.animation.interfaces.AnimationInterface;
import com.admob.android.admobtest.animation.interfaces.FinishingAnimation;

public class AnimationController implements AnimationInterface{

    private View mView;
    private Activity mActivity;

    public AnimationController(View mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
    }

    @Override
    public void changeAnimation() {
        mView.animate().alpha(.7f).scaleX(.94f).scaleY(.94f).start();
        new Thread(()->{
            try {
                Thread.sleep(500);
                mActivity.runOnUiThread(()->{
                    mView.animate().alpha(1f).scaleX(1f).scaleY(1f).start();
                    animationFinish();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })
                .start();
    }

    @Override
    public boolean animationFinish() {
        FinishingAnimation fI = (FinishingAnimation)mActivity;
        fI.cancelAnimations(mView);
        return true;
    }
}
