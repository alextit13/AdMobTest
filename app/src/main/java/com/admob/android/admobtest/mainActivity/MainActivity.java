package com.admob.android.admobtest.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.admob.android.admobtest.R;
import com.admob.android.admobtest.STATIC_STRINGS.Constants_;
import com.admob.android.admobtest.animation.controllers.AnimationController;
import com.admob.android.admobtest.animation.interfaces.FinishingAnimation;
import com.admob.android.admobtest.previewContent.PreviewContent;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements FinishingAnimation, RewardedVideoAdListener {

    private AdView mAdView;

    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAdMobBanner();
        initAdMobVideo();

        initRealm();
    }

    private void initAdMobBanner() {
        MobileAds.initialize(this, getResources().getString(R.string.AD_MOB_IDENTIFIER));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initAdMobVideo() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getResources().getString(R.string.RewardedVideo),
                new AdRequest.Builder().build()); // test s
    }

    private void initRealm() {
        Realm.init(this);
        //Realm realm = Realm.getDefaultInstance();
    }

    public void clickListeners(View view) {
        switch (view.getId()) {
            case R.id.cv_1:
                animationListener((findViewById(R.id.cv_1)));
                // geroi
                break;
            case R.id.cv_2:
                animationListener((findViewById(R.id.cv_2)));
                // cars
                break;
            case R.id.cv_3:
                animationListener((findViewById(R.id.cv_3)));
                // missions
                break;
            case R.id.cv_4:
                animationListener((findViewById(R.id.cv_4)));
                // secrets
                break;
            default:
                break;
        }
    }

    private void animationListener(View viewById) {
        AnimationController controller = new AnimationController(viewById, this);
        controller.changeAnimation();
    }

    @Override
    public void cancelAnimations(View view) {
        checkItemIsClick(view);
    }

    private void checkItemIsClick(View view) {
        Intent intent = new Intent(this, PreviewContent.class);
        switch (view.getId()) {
            case R.id.cv_1:
                putIntent(intent, "cv_1");
                break;
            case R.id.cv_2:
                putIntent(intent, "cv_2");
                break;
            case R.id.cv_3:
                putIntent(intent, "cv_3");
                break;
            case R.id.cv_4:
                putIntent(intent, "cv_4");
                break;
        }
    }

    private void putIntent(Intent intent, String cv) {

        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }

        intent.putExtra(Constants_.TEXT_INTENT_FIRST, cv);
        startActivity(intent);
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

}
