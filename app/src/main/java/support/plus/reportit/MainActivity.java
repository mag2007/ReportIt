package support.plus.reportit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.plus.PlusShare;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import support.plus.reportit.rv.AllReportsRV;
import support.plus.reportit.shortcuts.last_saved_class;
/**
 * Created by Wladislaw Tauberger on 24.03.16 at 16:05.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BillingProcessor.IBillingHandler {

    BillingProcessor bp;

    private DrawerLayout Drawer;
    String userName;
    String userBossName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default config
        final SharedPreferences pref3 = getSharedPreferences("Theme", Context.MODE_PRIVATE);
        Boolean themeDark = pref3.getBoolean("darkTheme", false);
        if(themeDark==true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        }


        if (savedInstanceState != null) {
            // Restore value of members from saved state
            userName = savedInstanceState.getString(userName);
            userBossName = savedInstanceState.getString(userBossName);
        } else {
            // Probably initialize members with default values for a new instance
        }


        // PURCHASES
        // bp = new BillingProcessor(this, "NULL", this);
        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmHAZCKx41vl+qm/p57tBdK2V70D8Lqa3T2BZC9JZFAH9inMwGOF/A4yPI+Ys4o3z9MIysaUcKcUkQSHSvcO7i3tKfHwpwZDsqUoL8xvMDbTLK7VUtSARkVkz5svisYzWBrLbqLrqCqafKo146thqv1ZlVUUNKkyFd9aWzMUPWz5IkIlFL8VHzvUCcDi+rzbePE4CfJbSOvzttGxOiTjRnDW6yKZLo3E58i5FtvHr7n5wUt/FiPaEdlufTTjD9CWIBgsnqu0wjIV7PN3uDBx9TT3zsYorpb/EUCHrM3rFTjpCDyvykMmAwmc/rlNi2jCZZ/Uo+CvwIdZAmJO5mdm04wIDAQAB", this);

        // PURCHASES


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_menu);


        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Drawer.setDrawerListener(new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close));
        Button blastSavedOpen = (Button)findViewById(R.id.blastSavedOpen);

        Intent lastReportIntent = getIntent();
        boolean loadLast = lastReportIntent.getBooleanExtra("loadLast", false);
        if(loadLast){
            last_saved_class last_saved_class = new last_saved_class(MainActivity.this);
            finishAffinity();
        }

        final Button baddButton = (Button)findViewById(R.id.baddButton);
        blastSavedOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_saved_class last_saved_class = new last_saved_class(MainActivity.this);
                finishAffinity();

            }
        });
        baddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean clickedButton = true;
                Intent intent = new Intent(MainActivity.this, AllReportsRV.class);
                intent.putExtra("addClicked", clickedButton);
                startActivity(intent);
                finishAffinity();
            }
        });


        final NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navView.getMenu().getItem(0));

        TextView dateCurrent = (TextView) findViewById(R.id.dateCurrent);
        final TextView appUsedTimes = (TextView) findViewById(R.id.appUsedTimes);
        TextView reportsCompletely = (TextView) findViewById(R.id.reportsCompletely);
        final TextView appFirstStarted = (TextView) findViewById(R.id.appFirstStarted);

        Calendar c = Calendar.getInstance();
        int dayNo = c.get(Calendar.DATE);
        int year = c.get(Calendar.YEAR);
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(c.getTime());
        dateCurrent.setText(getString(R.string.current_date) + dayNo + getString(R.string.of) + month_name + getString(R.string.space) + year);


        SharedPreferences pref = getSharedPreferences("IntroStat", Context.MODE_PRIVATE);
        int dayC = pref.getInt("dayStarted", 27);
        String monthC = pref.getString("monthStarted", "September");
        int yearC = pref.getInt("yearStarted", 1993);
        appFirstStarted.setText(getString(R.string.first_started) + dayC + getString(R.string.space) + monthC + getString(R.string.space) + yearC);

        int count = pref.getInt("startCount", 0);
        appUsedTimes.setText(getString(R.string.was_used) + count + getString(R.string.times));


        createCustomAnimation();


        findViewById(R.id.shareFB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareAppLinkViaFacebook("https://play.google.com/store/apps/details?id=support.plus.reportit");
            }
        });

        findViewById(R.id.shareGP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new PlusShare.Builder(MainActivity.this)
                        .setType("text/plain")
                        .setText("#reportit #supportplus Check out this awesome app ")
                        .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=support.plus.reportit"))
                        .getIntent();

                startActivityForResult(shareIntent, 0);
            }
        });

        findViewById(R.id.shareTW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://twitter.com/intent/tweet?text=Check out this awesome app https://play.google.com/store/apps/details?id=support.plus.reportit");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });


        Button bdonate1 = (Button) findViewById(R.id.bdonate1);
        Button bdonate3 = (Button) findViewById(R.id.bdonate3);
        Button bdonate5 = (Button) findViewById(R.id.bdonate5);
        Button ownedPurchases = (Button) findViewById(R.id.ownedPurchases);

        bdonate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(MainActivity.this, "donation_1x");
                Snackbar thanks = Snackbar.make(findViewById(R.id.coordinatorlayout), R.string.buy1x_thanks, Snackbar.LENGTH_LONG);
                thanks.show();
            }
        });

        bdonate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(MainActivity.this, "donation_3x");
                Snackbar thanks = Snackbar.make(findViewById(R.id.coordinatorlayout), R.string.buy3x_thanks, Snackbar.LENGTH_LONG);
                thanks.show();
            }
        });

        bdonate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(MainActivity.this, "donation_5x");
                Snackbar thanks = Snackbar.make(findViewById(R.id.coordinatorlayout), R.string.buy5x_thanks, Snackbar.LENGTH_LONG);
                thanks.show();
            }
        });

        ownedPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.loadOwnedPurchasesFromGoogle();
                Snackbar thanks = Snackbar.make(findViewById(R.id.coordinatorlayout), R.string.buy_restored_succes, Snackbar.LENGTH_LONG);
                thanks.show();
            }
        });


        try {
            File mydirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt");
            boolean success = true;
            if (!mydirectory.exists()) {
                success = mydirectory.mkdir();
            }
            if (success) {
                File reportsdirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt"+File.separator+"Reports");
                boolean success2 = true;
                if (!reportsdirectory.exists()) {
                    success2 = reportsdirectory.mkdir();
                }

            }

        } catch (Exception e) {
        }

            File file=new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt"+File.separator+"Reports");
            File[] list = file.listFiles();
            int countFiles = 0;
            for (File f: list){
                countFiles++;
                reportsCompletely.setText(getString(R.string.created_part1) +countFiles+getString(R.string.created_part2));
            }

    }


    private void shareAppLinkViaFacebook(String content) {


        try {
            Intent intent1 = new Intent();
            intent1.setClassName("com.facebook.katana", "com.facebook.katana.activity.composer.ImplicitShareIntentHandler");
            intent1.setAction("android.intent.action.SEND");
            intent1.setType("text/plain");
            intent1.putExtra(Intent.EXTRA_TEXT, content);
            intent1.addCategory(Intent.CATEGORY_LAUNCHER);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivity(intent1);
        } catch (Exception e) {
            // If we failed (no native FB app installed), try share through SEND
            Intent intent = new Intent(Intent.ACTION_SEND);
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + content;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
            startActivity(intent);
        }
    }

    private void createCustomAnimation() {
        final FloatingActionMenu menu3 = (FloatingActionMenu) findViewById(R.id.menuShare);

        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(menu3.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(menu3.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(menu3.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(menu3.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                menu3.getMenuIconView().setImageResource(menu3.isOpened()
                        ? R.drawable.ic_share_white_24dp : R.drawable.ic_done_white_24dp);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        menu3.setIconToggleAnimatorSet(set);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.edit_userData) {
            Button bSave = (Button) findViewById(R.id.bSave);
            final EditText inuserName = (EditText) findViewById(R.id.inuserName);
            final EditText inuserBossName = (EditText) findViewById(R.id.inuserBossName);
            inuserName.setVisibility(View.VISIBLE);
            inuserBossName.setVisibility(View.VISIBLE);
            bSave.setVisibility(View.VISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("userName", userName);
        savedInstanceState.putString("userBossName", userBossName);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Drawer.closeDrawers();
        final int mItemId = item.getItemId();
        Drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (mItemId) {
                    case R.id.drawer_recyclerview:
                        startActivity(new Intent(MainActivity.this, AllReportsRV.class));
                        finishAffinity();
                        break;
                    case R.id.drawer_moreapps:
                        Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Support+%2B");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        finishAffinity();
                        break;
                    case R.id.drawer_settings:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        finishAffinity();
                        break;
                    case R.id.drawer_about:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        finishAffinity();
                        break;

                }
            }
        }, 75);
        return true;
    }

    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new MaterialDialog.Builder(this)
                    .title(R.string.exit1)
                    .iconRes(R.drawable.ic_error_outline_red_500_24dp)
                    .content(R.string.exit2)
                    .backgroundColor(getResources().getColor(R.color.cardColor))
                    .positiveText(R.string.yes)
                    .negativeText(R.string.no)
                    .cancelable(false)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            finishAffinity();
                        }
                    })
                    .show();


        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Intent lastReportIntent = getIntent();
        boolean loadLast = lastReportIntent.getBooleanExtra("loadLast", false);
        if(loadLast){
            last_saved_class last_saved_class = new last_saved_class(MainActivity.this);
            finishAffinity();
        }

    }



    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        Snackbar thanks = Snackbar.make(findViewById(R.id.coordinatorlayout), R.string.thanks_donation, Snackbar.LENGTH_LONG);
        thanks.show();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        Snackbar thanks = Snackbar.make(findViewById(R.id.coordinatorlayout), R.string.error_donation, Snackbar.LENGTH_LONG);
        thanks.show();
    }

    @Override
    public void onBillingInitialized() {

    }
}