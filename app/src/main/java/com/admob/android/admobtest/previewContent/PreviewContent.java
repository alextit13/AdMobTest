package com.admob.android.admobtest.previewContent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.admob.android.admobtest.DataTexts;
import com.admob.android.admobtest.R;
import com.admob.android.admobtest.STATIC_STRINGS.Constants_;
import com.admob.android.admobtest.database.RealmObjectDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class PreviewContent extends AppCompatActivity {

    private static final String TAG = "LOG_TAG";
    private Realm realm;
    private RealmResults<RealmObjectDatabase> data = null;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_content);
        initAdMobInterstitia();
        initRealm();
        getDataIntent();
    }

    private void initAdMobInterstitia() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.BlockBetweenActivitiesTEST));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .assetFile("default.realm")
                .schemaVersion(1)
                .build();
        realm = Realm.getInstance(config);
    }

    private void getDataIntent() {
        String result = getIntent().getStringExtra(Constants_.TEXT_INTENT_FIRST);
        if (result.equals("cv_1")) {
            data = realm.where(RealmObjectDatabase.class).beginGroup().equalTo("TAG", "Heroes").endGroup().findAll();
        } else if (result.equals("cv_2")) {
            data = realm.where(RealmObjectDatabase.class).beginGroup().equalTo("TAG", "Cars").endGroup().findAll();
        } else if (result.equals("cv_3")) {
            data = realm.where(RealmObjectDatabase.class).beginGroup().equalTo("TAG", "Missions").endGroup().findAll();
        } else if (result.equals("cv_4")) {
            data = realm.where(RealmObjectDatabase.class).beginGroup().equalTo("TAG", "Secrets").endGroup().findAll();
        }
        if (data != null) changeDataInViews(data);
    }

    private void changeDataInViews(RealmResults<RealmObjectDatabase> data) {
        ((TextView) findViewById(R.id.tv_1p)).setText(data.get(0).getName());
        ((TextView) findViewById(R.id.tv_2p)).setText(data.get(1).getName());
        ((TextView) findViewById(R.id.tv_3p)).setText(data.get(2).getName());
        ((TextView) findViewById(R.id.tv_4p)).setText(data.get(3).getName());
    }

    public void clickListeners(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        //Log.d(TAG, "getDataIntent: " + data.get(num_position_click).toString());
        Intent intent = new Intent(this, DataTexts.class);
        intent.putExtra(Constants_.TEXT_DATA_FROM_REALM_RESULTS, realm.where(RealmObjectDatabase.class)
                .equalTo("name", ((TextView) findViewById(view.getId())).getText().toString()).findFirst()
                .getData());
        startActivity(intent);
    }
}
