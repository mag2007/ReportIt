package support.plus.reportit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.digitus.Digitus;
import com.afollestad.digitus.DigitusCallback;
import com.afollestad.digitus.DigitusErrorType;
import com.afollestad.digitus.FingerprintDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Wladislaw Tauberger on 22.02.16 at 16:06.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class splashscreen extends AppCompatActivity implements DigitusCallback, FingerprintDialog.Callback{

    private TextView mStatus;
    private Button mButton;

    private static final int SPLASH_DURATION = 2000;
    private Handler myHandler;

    private static final int REQUEST_WRITE_STORAGE = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // intro
        TextView by = (TextView) findViewById(R.id.by);
        TextView appname = (TextView) findViewById(R.id.appname);
        ImageView logo = (ImageView) findViewById(R.id.logo);
        ImageView supportlogo = (ImageView) findViewById(R.id.supportlogo);

        SharedPreferences pref = getSharedPreferences("IntroStat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int countMe = pref.getInt("startCount",0);
        countMe ++;
        editor.putInt("startCount", countMe);
        editor.commit();

        YoYo.with(Techniques.Tada)
                .duration(2000)
                .playOn(logo);
        YoYo.with(Techniques.Landing)
                .duration(2500)
                .playOn(supportlogo);
        YoYo.with(Techniques.Landing)
                .duration(2500)
                .playOn(appname);
        YoYo.with(Techniques.Landing)
                .duration(3000)
                .playOn(by);


        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            SharedPreferences pref2 = getSharedPreferences("IntroStat", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = pref2.edit();
            editor2.putBoolean("intro_executed", false);
            editor2.commit();
        }
        // Splashscreen Options
        myHandler = new Handler();

        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("Fingerprint", Context.MODE_PRIVATE);
                boolean fingerprint_True = pref.getBoolean("fingerprintSet", false);
                if(fingerprint_True==true){
                    FingerprintDialog dialog = new FingerprintDialog();
                    dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
                    dialog.setCancelable(false);
                    dialog.show(splashscreen.this, getString(R.string.app_name), 69);

                } else {
                    Intent intent = new Intent(splashscreen.this, IntroActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
            }
        }, SPLASH_DURATION);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Digitus.get().handleResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onDigitusReady(Digitus digitus) {
        mStatus.setText(R.string.status_ready);
        mButton.setEnabled(true);
    }

    @Override
    public void onDigitusListening(boolean newFingerprint) {
        mButton.setText(R.string.stop_listening);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stop listening
                Digitus.get().stopListening();
                mStatus.setText(R.string.status_ready);
                // Clicking the button again will start listening again
                mButton.setText(R.string.start_listening);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Digitus.get().startListening();
                    }
                });
            }
        });

        mStatus.setText(newFingerprint ? R.string.status_listening_new : R.string.status_listening);
    }

    @Override
    public void onDigitusAuthenticated(Digitus digitus) {
        // Update status message,
        // mStatus.setText(R.string.status_authenticated);

        // Setup button to start listening again
        mButton.setText(R.string.start_listening);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Digitus.get().startListening();
            }
        });
    }

    @Override
    public void onDigitusError(Digitus digitus, DigitusErrorType type, Exception e) {
        // You could choose to do something different in each of these cases
        switch (type) {
            case FINGERPRINT_NOT_RECOGNIZED:
                mStatus.setText(getString(R.string.status_error, e.getMessage()));
                break;
            case FINGERPRINTS_UNSUPPORTED:
                mStatus.setText(getString(R.string.status_error, e.getMessage()));
                break;
            case HELP_ERROR:
                mStatus.setText(getString(R.string.status_error, e.getMessage()));
                break;
            case PERMISSION_DENIED:
                mStatus.setText(getString(R.string.status_error, e.getMessage()));
                break;
            case REGISTRATION_NEEDED:
                mStatus.setText(getString(R.string.status_error, e.getMessage()));
                mButton.setText(R.string.open_security_settings);
                mButton.setEnabled(true);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mButton.setText(R.string.start_listening);
                        Digitus.get().openSecuritySettings();
                    }
                });
                break;
            case UNRECOVERABLE_ERROR:
                mStatus.setText(getString(R.string.status_error, e.getMessage()));
                break;
        }
    }

    @Override
    public void onFingerprintDialogAuthenticated() {
        // Toast.makeText(this, R.string.dialog_authenticated, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(splashscreen.this, IntroActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void onFingerprintDialogVerifyPassword(final FingerprintDialog dialog, final String password) {
        // Simulate server contact
        SharedPreferences pref = getSharedPreferences("Fingerprint", Context.MODE_PRIVATE);
        String passwordSet = pref.getString("passwordSet", "password");
        dialog.notifyPasswordValidation(password.equals(passwordSet));
    }

    @Override
    public void onFingerprintDialogStageUpdated(FingerprintDialog dialog, FingerprintDialog.Stage stage) {
        Log.d("Digitus", "Dialog stage: " + stage.name());
    }

    @Override
    public void onFingerprintDialogCancelled() {
        FingerprintDialog dialog = new FingerprintDialog();
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
        dialog.setCancelable(false);
        dialog.show(splashscreen.this, getString(R.string.app_name), 69);
    }
}
