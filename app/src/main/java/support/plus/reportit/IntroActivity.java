package support.plus.reportit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Wladislaw Tauberger on 22.02.16 at 16:06.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class IntroActivity extends AppIntro2 {
    private static final int REQUEST_WRITE_STORAGE = 112;

    @Override
    public void init(Bundle savedInstanceState) {

        SharedPreferences pref = getSharedPreferences("IntroStat", Context.MODE_PRIVATE);
        if(pref.getBoolean("intro_executed", false)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Calendar c = Calendar.getInstance();
            int dayNo = c.get(Calendar.DATE);
            int year = c.get(Calendar.YEAR);
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
            String month_name = month_date.format(c.getTime());

            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("dayStarted", dayNo);
            editor.putString("monthStarted", String.valueOf(month_name));
            editor.putInt("yearStarted", year);

            editor.putBoolean("intro_executed", true);
            editor.commit();
        }

        addSlide(AppIntroFragment.newInstance(getString(R.string.app_name), getString(R.string.intro_welcome),
                R.drawable.iconapp, getResources().getColor(R.color.colorPrimary)));

        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_create_new), getString(R.string.intro_create_new2),
                R.drawable.screen2, getResources().getColor(R.color.colorPrimary)));

        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_sumup), getString(R.string.intro_sumup2),
                R.drawable.screen1, getResources().getColor(R.color.colorPrimary)));

        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_export), getString(R.string.intro_export2),
                R.drawable.screen3, getResources().getColor(R.color.colorPrimary)));



        setVibrate(true);
        setVibrateIntensity(20);

        setDepthAnimation();

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    SharedPreferences pref = getSharedPreferences("IntroStat", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("intro_executed", false);
                    editor.commit();
                    android.os.Process.killProcess(android.os.Process.myPid());
                } else
                {
                    Toast.makeText(this, R.string.warning_readpermission, Toast.LENGTH_LONG).show();
                    finishAffinity();
                }
            }
        }

    }


    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();

    }


    @Override
    public void onSlideChanged() {

    }

    public void getStarted(View v){
        loadMainActivity();

    }


}
