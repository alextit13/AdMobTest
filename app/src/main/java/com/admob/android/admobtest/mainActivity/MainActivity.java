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

        testInitDatabase();
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
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }

    private void initRealm() {
        Realm.init(this);
        //Realm realm = Realm.getDefaultInstance();
    }

    private void testInitDatabase() {
        /*Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();


        // heroes

        RealmObjectDatabase tommy = realm.createObject(RealmObjectDatabase.class);
        tommy.setTAG("Heroes");
        tommy.setName("Tommy");
        tommy.setData("Для анимации персонажа использовалась технология захвата движения; моделью стал актёр Джонатан Сэйл.\n" +
                "В озвучивании Томми Версетти принимал участие американский актёр Рэй Лиотта, известный по съёмкам в культовом голливудском боевике 1990 года «Славные парни». За свою работу он получил $5 млн авансом. Озвучивание оказалось интересной, хотя и непростой задачей для Лиотты. Позднее Рэй признался, что даже никогда не играл в «GTA: Vice City».\n");

        RealmObjectDatabase vance = realm.createObject(RealmObjectDatabase.class);
        vance.setTAG("Heroes");
        vance.setName("Vance");
        vance.setData("При жизни Лэнс был наркодельцем, вместе с братом Виктором до смерти последнего руководил кокаиновой империей Вэнсов. Роль Лэнса была в вождении вертолёта, в то же время брат Виктор управлял самой сделкой.\n" +
                "На протяжении всего появления в GTA Vice City он носит белый костюм с фиолетовой рубашкой. На игровом арте он показан в светло-фиолетовом костюме. В GTA Vice City Stories он поначалу появляется в красной майке и джинсах, затем в роскошном светло-голубом костюме.");

        RealmObjectDatabase Adam = realm.createObject(RealmObjectDatabase.class);
        Adam.setTAG("Heroes");
        Adam.setName("Adam");
        Adam.setData("Как и большинство диджеев, Адам считает свой стиль музыки лучшим. В Grand Theft Auto: Vice City он упоминает, что обожает клипы на песни, потому что они \"показывают, что внешность гораздо важнее таланта\" (хотя двумя годами ранее излагал совершенно противоположные идеи). Адам также говорит, что его мать \"никак не связана\" с его \"личным выбором\" пойти на радио.\n");

        RealmObjectDatabase Ricardo = realm.createObject(RealmObjectDatabase.class);
        Ricardo.setTAG("Heroes");
        Ricardo.setName("Ricardo");
        Ricardo.setData("Рикардо Диас — вторичный антагонист игры GTA Vice City, и один из главных персонажей в GTA: Vice City Stories.\n" +
                "Диас является боссом своего наркокартеля, до 1986 года он главный наркобарон в Вайс-Сити. Рикардо Диас родился в Колумбии, в 1942 году. Переехал в США в 1978 году, а в Вайс-Сити попал в начале 80-х, подкупив INS.");


        // cars

        RealmObjectDatabase Blista_Compact = realm.createObject(RealmObjectDatabase.class);
        Blista_Compact.setTAG("Cars");
        Blista_Compact.setName("Blista Compact");
        Blista_Compact.setData("Blista Compact – caмый обычный компактный спортивный автомобиль, особенно полезный для быстрого перемещения. Ускорение, управляемость и скоростные данные делают его пригодным практически в любой ситуации. Оснащён системой переднего привода. Поэтому, если изменить выходной крутящий момент в файлах игры, автомобиль будет тяжело поворачивать.\n");

        RealmObjectDatabase Grand_burrito = realm.createObject(RealmObjectDatabase.class);
        Grand_burrito.setTAG("Cars");
        Grand_burrito.setName("Grand burrito");
        Grand_burrito.setData("Gang Burrito, обладает просто отличными характеристиками, и не только для своего класса. Он вполне может посоперничать даже с некоторыми легковыми машинами. Не смотря на некоторые сложности с поворотами на больших скоростях, Gang Burrito остается, пожалуй, лучшим фургоном в игре и, вообще, отличной машиной по всем параметрам. Его характеристики сильно улучшены, по сравнению с обычным Burrito. Мощный двигатель разгоняет машину до максимальной скорости в 241 км/ч, машина оснащена заднеприводным шасси, но отлично себя чувствует как на твердом асфальте, так и на бездорожье.\n");

        RealmObjectDatabase Stinger = realm.createObject(RealmObjectDatabase.class);
        Stinger.setTAG("Cars");
        Stinger.setName("Stinger");
        Stinger.setData("Stinger — двухдверный спорткар, который впервые появился в Grand Theft Auto 1 и был многократно изменён в дизайне. Производителем автомобиля во вселенной HD является компания Grotti.\n");

        RealmObjectDatabase Costa_Guard = realm.createObject(RealmObjectDatabase.class);
        Costa_Guard.setTAG("Cars");
        Costa_Guard.setName("Costa Guard");
        Costa_Guard.setData("Данная лодка используется Береговой охраной для устранения аварийно-опасных ситуаций на воде. Имеет довольно высокую скорость и хорошую маневренность. Как и на прочем служебном транспорте, на данной лодке отсутствует радио.");


        // missions

        RealmObjectDatabase Demolition_man = realm.createObject(RealmObjectDatabase.class);
        Demolition_man.setTAG("Missions");
        Demolition_man.setName("Demolition man");
        Demolition_man.setData("Эйвери смотрит на некоторые места стройки, которые находятся выше по дороге от его стройки. Проблема в том, что стройка уже началась, поэтому Эйвери просит Томми уничтожить её. Томми направляется к месту, недалёко от стройки, где находится Top Fun с RC Goblin и четырьмя бомбами. Томми садится в фургончик и управляет вертолётом, подцепляя бомбы, избегая рабочих и охрану. После того, как вертолёт установит все бомбы, Томми взрывает их.");

        RealmObjectDatabase Two_Bit_Hit = realm.createObject(RealmObjectDatabase.class);
        Two_Bit_Hit.setTAG("Missions");
        Two_Bit_Hit.setName("Two Bit Hit");
        Two_Bit_Hit.setData("миссия в Grand Theft Auto: Vice City, последняя выдаваемая игроку Эйвери Кэррингтоном. В этой миссии игроку предстоит развязать войну между двумя бандами.\n");

        RealmObjectDatabase Keep_Your_Friends_Close = realm.createObject(RealmObjectDatabase.class);
        Keep_Your_Friends_Close.setTAG("Missions");
        Keep_Your_Friends_Close.setName("Keep Your Friends Close");
        Keep_Your_Friends_Close.setData("Томми Версетти взял под контроль преступный мир Вайс-Сити, что сильно раздражает Сонни Форелли. После неудачной попытки насильственно взять налог с деловых предприятий Томми, Сонни приезжает в город, чтобы лично встретиться с Томми. Предупреждённый о приезде Сонни, Томми готовит 3 миллиона долларов поддельных наличных в надежде откупиться ими.\n" +
                "Однако, когда Сонни, наконец, приходит, коварный Лэнс Вэнс открыто предает Томми, ссылаясь на «бизнес», как свою мотивацию. В то же время, Томми также не оправился от осознания того, что 15 лет он провел в тюрьме Форелли из-за спланированной засады.\n" +
                "Эти откровения ускоряют перестрелку в особняке Версетти. Во-первых, Томми убивает Лэнса на крыше особняка, затем идет на убийство Сонни у начала лестницы в фойе вместе с его охранниками. Томми также предположительно ранен во время перестрелки, в конце ролика, Розенберг сетует, что Томми испортил свой костюм (правда, ни крови, ни каких-либо повреждений не видно). После этого Томми и Кен обсуждают свои планы на будущее и празднуют победу.");

        RealmObjectDatabase Check_Out = realm.createObject(RealmObjectDatabase.class);
        Check_Out.setTAG("Missions");
        Check_Out.setName("Check Out");
        Check_Out.setData("Томми Версетти заходит в международный аэропорт имени Эскобара и подходит к таксофону, Мистер Блэк поручил Томми убить бизнесмена, забрать его портфель и отвезти в Ammu-Nation. Мистер Блэк спрятал снайперскую винтовку в аэропорту для того, чтобы Томми убил бизнесмена и забрал чемодан. Мистер Блэк говорит Томми, что есть женщина, которая будет отвлекать бизнесмена и его будет легко убить. Томми ждёт пока окончится разговор и убивает бизнесмена. Далее Томми везёт чемодан в Амму-нацию в Деловом районе, и миссия завершается.");


        // secrets

        RealmObjectDatabase flat_1c = realm.createObject(RealmObjectDatabase.class);
        flat_1c.setTAG("Secrets");
        flat_1c.setName("Квартира 1с");
        flat_1c.setData("Это жилище маньяка находится на первом острове, чуть севернее станции Pay N’ Spray в районе Ocean Beach. Вам нужно найти указанный дом (см. скриншот) и подняться по ступенькам на второй этаж. Справа будет черный дверной проем – идем туда. В этой комнате можно взять бензопилу, которая лежит в окровавленной ванне.\n");

        RealmObjectDatabase paint = realm.createObject(RealmObjectDatabase.class);
        paint.setTAG("Secrets");
        paint.setName("Рисунок из светящихся окон");
        paint.setData("Нужный высотный дом находится на первом острове, в районе Vice Point недалеко от клуба Малибу. Легче всего его найти, если искать в промежутке между 23:00 и 23:59 по местному времени, т.к. в это время из светящихся окон этого дома будет выложен недвусмысленный рисунок (видно далеко, не пропустите).\n");


        RealmObjectDatabase ball = realm.createObject(RealmObjectDatabase.class);
        ball.setTAG("Secrets");
        ball.setName("Мяч");
        ball.setData("На дне одного осушенного бассейна, находящегося недалеко от Vercetti Estate (Виллы Верчетти) лежит пляжный мяч. Этот мяч можно набивать головой. Ваши максимальные достижения заносятся в пункт меню статистики “Higest score with Keepie-Uppy beach ball”.\n" +
                "Мяч лежит не только в этом бассейне. Его можно найти в некоторых местах на пляже (спасибо Russell-у за уточнение).\n" +
                "Дополнение от sergey: вы знаете, что рядом с виллой Томми есть дом и бассейн с мячом. Этот мяч можно выбить к гаражу этого дома. Там есть кольцо, в него можно тоже забивать мяч.\n");


        RealmObjectDatabase water = realm.createObject(RealmObjectDatabase.class);
        water.setTAG("Secrets");
        water.setName("Бассейн в виде женского тела");
        water.setData("Недалеко от мяча есть бассейн интересной формы. Также в этом районе имеется бассейн с эмблемой Rockstar.");


        realm.commitTransaction();*/
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
