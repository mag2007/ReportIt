package support.plus.reportit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Calendar;

import support.plus.reportit.alarm.AlarmReceiver;
import support.plus.reportit.rv.AllReportsRV;

/**
 * Created by Wladislaw Tauberger on 22.02.16 at 16:06.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class SettingsActivity extends AppCompatActivity {

        String userName;
        String userBossName;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        // Default config

        if (savedInstanceState != null) {
        // Restore value of members from saved state
        userName = savedInstanceState.getString(userName);
        userBossName = savedInstanceState.getString(userBossName);
        } else {
        // Probably initialize members with default values for a new instance
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        final TextView TextuserName = (TextView) findViewById(R.id.userName);
        final TextView TextuserBossName = (TextView) findViewById(R.id.userBossName);
        final EditText inuserName = (EditText) findViewById(R.id.inuserName);
        final EditText inuserBossName = (EditText) findViewById(R.id.inuserBossName);
        final Switch sNoti = (Switch) findViewById(R.id.sNoti);
        final Button btimePick = (Button) findViewById(R.id.btimePick);
        final Button bSave = (Button) findViewById(R.id.bSave);
        final Switch sfingerprint_activ = (Switch) findViewById(R.id.sfingerprint_activ);
        final Button bsetpassword = (Button) findViewById(R.id.bsetpassword);
        final Switch sdarkTheme = (Switch) findViewById(R.id.sdarkTheme);
        final Button bchooseRingtone = (Button) findViewById(R.id.bringtonePick);


        final SharedPreferences pref3 = getSharedPreferences("Theme", Context.MODE_PRIVATE);
        Boolean themeDark = pref3.getBoolean("darkTheme", false);
        if (themeDark==true){
            sdarkTheme.setChecked(true);
        } else {
            sdarkTheme.setChecked(false);
        }
        sdarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final SharedPreferences pref3 = getSharedPreferences("Theme", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref3.edit();
                    editor.putBoolean("darkTheme", true);
                    editor.commit();
                    getDelegate().setLocalNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES);
                    // Now recreate for it to take effect
                    // startActivity(getIntent());
                    recreate();
                } else {
                    SharedPreferences.Editor editor = pref3.edit();
                    editor.putBoolean("darkTheme", false);
                    editor.commit();
                    getDelegate().setLocalNightMode(
                            AppCompatDelegate.MODE_NIGHT_AUTO);
                    // Now recreate for it to take effect
                    // startActivity(getIntent());
                    recreate();
                }
            }
        });

    /*
    SECURITY SETTINGS
     */

    final SharedPreferences pref1 = getSharedPreferences("Fingerprint", Context.MODE_PRIVATE);
    Boolean fingerprintSet = pref1.getBoolean("fingerprintSet", false);
    if (fingerprintSet==true){
        sfingerprint_activ.setChecked(true);
        bsetpassword.setVisibility(View.VISIBLE);
    } else {
        sfingerprint_activ.setChecked(false);
        bsetpassword.setVisibility(View.GONE);
    }
    sfingerprint_activ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                SharedPreferences.Editor editor = pref1.edit();
                editor.putBoolean("fingerprintSet", true);
                editor.commit();
                bsetpassword.setVisibility(View.VISIBLE);
            } else {
                SharedPreferences.Editor editor = pref1.edit();
                editor.putBoolean("fingerprintSet", false);
                editor.commit();
                bsetpassword.setVisibility(View.GONE);
            }
        }
    });

        bsetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(SettingsActivity.this)
                        .title(R.string.set_password)
                        .content(R.string.set_password_desc)
                        .inputType( InputType.TYPE_CLASS_TEXT  | InputType.TYPE_TEXT_VARIATION_PASSWORD )
                        .inputRange(1, 10)
                        .input(getString(R.string.password), "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                String name = String.valueOf(input);
                                SharedPreferences pref1 = getSharedPreferences("Fingerprint", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref1.edit();
                                editor.putString("passwordSet", name);
                                editor.commit();
                                return;
                            }
                        })
                        .positiveText(R.string.set_password)
                        .negativeText(R.string.cancel)
                        .cancelable(false)
                        .show();
            }

        });

    /*
    END SECURITY SETTINGS
     */

    /*
    NOTIFICATION SETTINGS
     */
        btimePick.setVisibility(View.GONE);
        sNoti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
        btimePick.setVisibility(View.VISIBLE);
            bchooseRingtone.setVisibility(View.VISIBLE);
        } else {
        btimePick.setVisibility(View.GONE);
            bchooseRingtone.setVisibility(View.GONE);
        }
        }
        });


    final SharedPreferences pref4 = getApplicationContext().getSharedPreferences("userNotification", MODE_PRIVATE);
    Boolean notificationSet = pref4.getBoolean("alarmSet", false);
    if (notificationSet==true){
        sNoti.setChecked(true);
        btimePick.setVisibility(View.VISIBLE);
        bchooseRingtone.setVisibility(View.VISIBLE);
    } else {
        sNoti.setChecked(false);
        btimePick.setVisibility(View.GONE);
        bchooseRingtone.setVisibility(View.GONE);
    }
    sNoti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                SharedPreferences.Editor editor = pref4.edit();
                editor.putBoolean("alarmSet", true);
                editor.commit();
                btimePick.setVisibility(View.VISIBLE);
                bchooseRingtone.setVisibility(View.VISIBLE);

            } else {
                SharedPreferences.Editor editor = pref4.edit();
                editor.putBoolean("alarmSet", false);
                editor.commit();
                btimePick.setVisibility(View.GONE);
                bchooseRingtone.setVisibility(View.GONE);


            }
        }
    });
    bchooseRingtone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, R.string.choose_ringtone);
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
            startActivityForResult(intent, 5);
        }
    });

    btimePick.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
        }
        });

    /*
    END NOTIFICATION SETTINGS
     */


    /*
    SAVE SETTINGS
     */
        bSave.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
        String saveworkplace = "Student";
        String saveuserBossName = "Boss";
        saveworkplace = inuserName.getText().toString();
        saveuserBossName = inuserBossName.getText().toString();
        TextuserName.setText(getString(R.string.firm_name) + String.valueOf(saveworkplace));
        TextuserBossName.setText(getString(R.string.instructor_name) + String.valueOf(saveuserBossName));
        final SharedPreferences pref2 = getApplicationContext().getSharedPreferences("userData", MODE_APPEND);
        SharedPreferences.Editor editor = pref2.edit();
        editor.putString("userName", saveworkplace);
        editor.putString("userBoss", saveuserBossName);
        editor.putBoolean("data_saved", true);
        editor.commit();
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Data saved. Restart the app to see changes.", Snackbar.LENGTH_LONG);
        snackbar.show();
       // finish();
       startActivity(getIntent());
         }
        });
        final SharedPreferences pref2 = getApplicationContext().getSharedPreferences("userData", MODE_APPEND);
        if(pref2.getBoolean("data_saved", true)){
        String TextuserNameString = pref2.getString("userName", "");
        String TextuserBossNameString = pref2.getString("userBoss", "");
        TextuserName.setText(getString(R.string.firm_name) + String.valueOf(TextuserNameString));
        TextuserBossName.setText(getString(R.string.instructor_name) + String.valueOf(TextuserBossNameString));
        inuserName.setVisibility(View.GONE);
        inuserBossName.setVisibility(View.GONE);
        bSave.setVisibility(View.GONE);

        } else {
        Toast toast = Toast.makeText(this, "No data saved", Toast.LENGTH_SHORT);
        toast.show();
        }
    /*
    END SAVE SETTINGS
     */



}


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent)
    {
        if (resultCode == Activity.RESULT_OK && requestCode == 5)
        {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

            if (uri != null)
            {
                final SharedPreferences pref5 = getApplicationContext().getSharedPreferences("soundNotifications", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref5.edit();
                editor.putString("alarmSound", String.valueOf(uri));
                editor.commit();
            }
            else
            {
                final SharedPreferences pref5 = getApplicationContext().getSharedPreferences("soundNotifications", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref5.edit();
                editor.putString("alarmSound", null);
                editor.commit();
            }
        }
    }


public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        if(minute<10){
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Notification set for "+hour+ ":0" + minute, Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Notification set for "+hour+ ":" + minute, Snackbar.LENGTH_LONG);
            snackbar.show();
        }


        int mhour = hour;
        int mminute = minute;
        scheduleNotification(getNotification("TEST"), mhour, mminute);

    }
}

    @Override
    public void recreate()
    {
            super.recreate();
    }


    public void scheduleNotification(Notification notification, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    public Notification getNotification(String content) {
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final SharedPreferences pref5 = getApplicationContext().getSharedPreferences("soundNotifications", MODE_PRIVATE);
        String soundUri2 = pref5.getString("alarmSound", String.valueOf(uri));
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(getString(R.string.wrap_up));
        builder.setContentText(getString(R.string.wrap_up_desc));
        builder.setVibrate(new long[]{1000});
        builder.setSound(Uri.parse(soundUri2));
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setLights(Color.GREEN, 3000, 3000);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentIntent(PendingIntent.getActivity(this, 1, new Intent(this, AllReportsRV.class), PendingIntent.FLAG_UPDATE_CURRENT));
        return builder.build();

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
        if (id == android.R.id.home) {
            this.finish();
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
        getMenuInflater().inflate(R.menu.settings, menu);


        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }
}