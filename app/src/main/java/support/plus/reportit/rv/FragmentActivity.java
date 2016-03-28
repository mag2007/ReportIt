package support.plus.reportit.rv;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

import support.plus.reportit.R;
import support.plus.reportit.export.export_email_class;
import support.plus.reportit.export.export_pdf_class;


/**
 * Created by Wladislaw Tauberger on 22.02.16 at 16:08.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */


public class FragmentActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        Toolbar toolbar = (Toolbar) findViewById(R.id.fragment_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_back);

        Intent intent = getIntent();

        createCustomAnimation();

        String intfilename = intent.getStringExtra("filename");
        final int weekID = getIntent().getIntExtra("weekID", 0);

        SharedPreferences prefLast = getSharedPreferences("lastSaved", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefLast.edit();
        editor.putString("intfilename", intfilename);
        editor.putInt("weekID", weekID);
        editor.commit();

        TextView filenameText = (TextView) findViewById(R.id.filename);
        final EditText filetag = (EditText) findViewById(R.id.filetag);
        TextView fileauthorboss = (TextView) findViewById(R.id.fileauthorboss);
        Button bStartDate = (Button) findViewById(R.id.bStartDate);
        final EditText filenumber = (EditText) findViewById(R.id.filenumber);
        Button bEndDate = (Button) findViewById(R.id.bEndDate);
        final TextView startDate = (TextView) findViewById(R.id.startDate);
        final TextView endDate = (TextView) findViewById(R.id.endDate);


        bStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences pref = getApplicationContext().getSharedPreferences("buttonsPressed", MODE_APPEND);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("bstartpressed", true);
                editor.commit();
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        bEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences pref = getApplicationContext().getSharedPreferences("buttonsPressed", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("bendpressed", true);
                editor.commit();
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });


        try {
            File mydirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt");
            boolean success = true;
            if (!mydirectory.exists()) {
                success = mydirectory.mkdir();
            }
            if (success) {
                File reportsdirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt"+File.separator+"Exported PDF's");
                boolean success2 = true;
                if (!reportsdirectory.exists()) {
                    success2 = reportsdirectory.mkdir();
                }

            }

        } catch (Exception e) {
        }



        // Declaration weekdays editfields
        // Monday
        final EditText mondayText1 = (EditText) findViewById(R.id.mondayText1);
        final EditText mondayText2 = (EditText) findViewById(R.id.mondayText2);
        final EditText mondayText3 = (EditText) findViewById(R.id.mondayText3);
        final EditText mondayText4 = (EditText) findViewById(R.id.mondayText4);
        final EditText mondayText5 = (EditText) findViewById(R.id.mondayText5);
        final EditText mondayText6 = (EditText) findViewById(R.id.mondayText6);

        final EditText mondayTextTime1 = (EditText) findViewById(R.id.mondayTextTime1);
        final EditText mondayTextTime2 = (EditText) findViewById(R.id.mondayTextTime2);
        final EditText mondayTextTime3 = (EditText) findViewById(R.id.mondayTextTime3);
        final EditText mondayTextTime4 = (EditText) findViewById(R.id.mondayTextTime4);
        final EditText mondayTextTime5 = (EditText) findViewById(R.id.mondayTextTime5);
        final EditText mondayTextTime6 = (EditText) findViewById(R.id.mondayTextTime6);
        // End monday ---- Start tuesday
        final EditText tuesdayText1 = (EditText) findViewById(R.id.tuesdayText1);
        final EditText tuesdayText2 = (EditText) findViewById(R.id.tuesdayText2);
        final EditText tuesdayText3 = (EditText) findViewById(R.id.tuesdayText3);
        final EditText tuesdayText4 = (EditText) findViewById(R.id.tuesdayText4);
        final EditText tuesdayText5 = (EditText) findViewById(R.id.tuesdayText5);
        final EditText tuesdayText6 = (EditText) findViewById(R.id.tuesdayText6);

        final EditText tuesdayTextTime1 = (EditText) findViewById(R.id.tuesdayTextTime1);
        final EditText tuesdayTextTime2 = (EditText) findViewById(R.id.tuesdayTextTime2);
        final EditText tuesdayTextTime3 = (EditText) findViewById(R.id.tuesdayTextTime3);
        final EditText tuesdayTextTime4 = (EditText) findViewById(R.id.tuesdayTextTime4);
        final EditText tuesdayTextTime5 = (EditText) findViewById(R.id.tuesdayTextTime5);
        final EditText tuesdayTextTime6 = (EditText) findViewById(R.id.tuesdayTextTime6);
        // End tuesday ---- Start wednesday
        final EditText wednesdayText1 = (EditText) findViewById(R.id.wednesdayText1);
        final EditText wednesdayText2 = (EditText) findViewById(R.id.wednesdayText2);
        final EditText wednesdayText3 = (EditText) findViewById(R.id.wednesdayText3);
        final EditText wednesdayText4 = (EditText) findViewById(R.id.wednesdayText4);
        final EditText wednesdayText5 = (EditText) findViewById(R.id.wednesdayText5);
        final EditText wednesdayText6 = (EditText) findViewById(R.id.wednesdayText6);

        final EditText wednesdayTextTime1 = (EditText) findViewById(R.id.wednesdayTextTime1);
        final EditText wednesdayTextTime2 = (EditText) findViewById(R.id.wednesdayTextTime2);
        final EditText wednesdayTextTime3 = (EditText) findViewById(R.id.wednesdayTextTime3);
        final EditText wednesdayTextTime4 = (EditText) findViewById(R.id.wednesdayTextTime4);
        final EditText wednesdayTextTime5 = (EditText) findViewById(R.id.wednesdayTextTime5);
        final EditText wednesdayTextTime6 = (EditText) findViewById(R.id.wednesdayTextTime6);
        // End wednesday ---- Start thursday
        final EditText thursdayText1 = (EditText) findViewById(R.id.thursdayText1);
        final EditText thursdayText2 = (EditText) findViewById(R.id.thursdayText2);
        final EditText thursdayText3 = (EditText) findViewById(R.id.thursdayText3);
        final EditText thursdayText4 = (EditText) findViewById(R.id.thursdayText4);
        final EditText thursdayText5 = (EditText) findViewById(R.id.thursdayText5);
        final EditText thursdayText6 = (EditText) findViewById(R.id.thursdayText6);

        final EditText thursdayTextTime1 = (EditText) findViewById(R.id.thursdayTextTime1);
        final EditText thursdayTextTime2 = (EditText) findViewById(R.id.thursdayTextTime2);
        final EditText thursdayTextTime3 = (EditText) findViewById(R.id.thursdayTextTime3);
        final EditText thursdayTextTime4 = (EditText) findViewById(R.id.thursdayTextTime4);
        final EditText thursdayTextTime5 = (EditText) findViewById(R.id.thursdayTextTime5);
        final EditText thursdayTextTime6 = (EditText) findViewById(R.id.thursdayTextTime6);
        // End thursday ---- Start friday
        final EditText fridayText1 = (EditText) findViewById(R.id.fridayText1);
        final EditText fridayText2 = (EditText) findViewById(R.id.fridayText2);
        final EditText fridayText3 = (EditText) findViewById(R.id.fridayText3);
        final EditText fridayText4 = (EditText) findViewById(R.id.fridayText4);
        final EditText fridayText5 = (EditText) findViewById(R.id.fridayText5);
        final EditText fridayText6 = (EditText) findViewById(R.id.fridayText6);

        final EditText fridayTextTime1 = (EditText) findViewById(R.id.fridayTextTime1);
        final EditText fridayTextTime2 = (EditText) findViewById(R.id.fridayTextTime2);
        final EditText fridayTextTime3 = (EditText) findViewById(R.id.fridayTextTime3);
        final EditText fridayTextTime4 = (EditText) findViewById(R.id.fridayTextTime4);
        final EditText fridayTextTime5 = (EditText) findViewById(R.id.fridayTextTime5);
        final EditText fridayTextTime6 = (EditText) findViewById(R.id.fridayTextTime6);
        // End of friday and declaration weekdays editfields

        filenameText.setText(String.valueOf(cutName(intfilename)));

        com.github.clans.fab.FloatingActionButton bexport_pdf = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.export_pdf);
        com.github.clans.fab.FloatingActionButton bexport_rtf = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.export_rtf);
        com.github.clans.fab.FloatingActionButton bshare_email = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.share_email);

        if (bexport_pdf != null) {
            bexport_pdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int weekID = getIntent().getIntExtra("weekID", 0);
                    String filename = String.valueOf(AllReportsRV.itemTexte.get(weekID));
                    String RESULT
                            = Environment.getExternalStorageDirectory() + File.separator + "ReportIt" + File.separator+  "Exported PDF's"+ File.separator +cutName(filename) + getString(R.string.pdf_extension);

                    export_pdf_class export_pdf_class = new export_pdf_class(FragmentActivity.this, FragmentActivity.this, weekID, RESULT);

                }
            });
        }

        if (bshare_email != null) {
            bshare_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int weekID = getIntent().getIntExtra("weekID", 0);
                    String filename = String.valueOf(AllReportsRV.itemTexte.get(weekID));
                    String RESULT
                            = Environment.getExternalStorageDirectory() + File.separator + "ReportIt" + File.separator+  "Exported PDF's"+ File.separator +cutName(filename) + getString(R.string.pdf_extension);

                    export_email_class export_email_class = new export_email_class(FragmentActivity.this, FragmentActivity.this, weekID, RESULT);

                }
            });
        }

        if (bexport_rtf != null) {
            bexport_rtf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FragmentActivity.this, "Coming soon", Toast.LENGTH_LONG).show();
                }
            });
        }

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("userData", MODE_APPEND);
        String TextuserBossNameString = pref.getString("userBoss", "");
        fileauthorboss.setText(getString(R.string.instructor) + String.valueOf(TextuserBossNameString));

        StringBuilder textCatched = new StringBuilder();

        String filename = intfilename;
        Scanner read = null;
        try {
            read = new Scanner(new File(Environment.getExternalStorageDirectory() + File.separator + "/ReportIt/Reports" + File.separator + cutName(filename)));

            read.useDelimiter(";;");
            String start, start2, start3, start4, start5;

            while (read.hasNext()) {
                start = read.next();
                filenumber.setText(read.next().toString());
                filetag.setText(read.next().toString());
                startDate.setText(read.next().toString());
                endDate.setText(read.next().toString());
                mondayText1.setText(read.next().toString());
                mondayTextTime1.setText(read.next().toString());
                mondayText2.setText(read.next().toString());
                mondayTextTime2.setText(read.next().toString());
                mondayText3.setText(read.next().toString());
                mondayTextTime3.setText(read.next().toString());
                mondayText4.setText(read.next().toString());
                mondayTextTime4.setText(read.next().toString());
                mondayText5.setText(read.next().toString());
                mondayTextTime5.setText(read.next().toString());
                mondayText6.setText(read.next().toString());
                mondayTextTime6.setText(read.next().toString());



                read.nextLine();
                start2 = read.next();
                tuesdayText1.setText(read.next().toString());
                tuesdayTextTime1.setText(read.next().toString());
                tuesdayText2.setText(read.next().toString());
                tuesdayTextTime2.setText(read.next().toString());
                tuesdayText3.setText(read.next().toString());
                tuesdayTextTime3.setText(read.next().toString());
                tuesdayText4.setText(read.next().toString());
                tuesdayTextTime4.setText(read.next().toString());
                tuesdayText5.setText(read.next().toString());
                tuesdayTextTime5.setText(read.next().toString());
                tuesdayText6.setText(read.next().toString());
                tuesdayTextTime6.setText(read.next().toString());



                read.nextLine();
                start3 = read.next();
                wednesdayText1.setText(read.next().toString());
                wednesdayTextTime1.setText(read.next().toString());
                wednesdayText2.setText(read.next().toString());
                wednesdayTextTime2.setText(read.next().toString());
                wednesdayText3.setText(read.next().toString());
                wednesdayTextTime3.setText(read.next().toString());
                wednesdayText4.setText(read.next().toString());
                wednesdayTextTime4.setText(read.next().toString());
                wednesdayText5.setText(read.next().toString());
                wednesdayTextTime5.setText(read.next().toString());
                wednesdayText6.setText(read.next().toString());
                wednesdayTextTime6.setText(read.next().toString());


                read.nextLine();
                start4 = read.next();
                thursdayText1.setText(read.next().toString());
                thursdayTextTime1.setText(read.next().toString());
                thursdayText2.setText(read.next().toString());
                thursdayTextTime2.setText(read.next().toString());
                thursdayText3.setText(read.next().toString());
                thursdayTextTime3.setText(read.next().toString());
                thursdayText4.setText(read.next().toString());
                thursdayTextTime4.setText(read.next().toString());
                thursdayText5.setText(read.next().toString());
                thursdayTextTime5.setText(read.next().toString());
                thursdayText6.setText(read.next().toString());
                thursdayTextTime6.setText(read.next().toString());


                read.nextLine();
                start5 = read.next();
                fridayText1.setText(read.next().toString());
                fridayTextTime1.setText(read.next().toString());
                fridayText2.setText(read.next().toString());
                fridayTextTime2.setText(read.next().toString());
                fridayText3.setText(read.next().toString());
                fridayTextTime3.setText(read.next().toString());
                fridayText4.setText(read.next().toString());
                fridayTextTime4.setText(read.next().toString());
                fridayText5.setText(read.next().toString());
                fridayTextTime5.setText(read.next().toString());
                fridayText6.setText(read.next().toString());
                fridayTextTime6.setText(read.next().toString());



            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("userData", MODE_APPEND);
        String TextuserNameString = pref.getString("userName", "");
        String TextuserBossNameString = pref.getString("userBoss", "");
        int weekID = getIntent().getIntExtra("weekID", 0);
        // Declaration weekdays editfields
        // Monday
        EditText filenumber = (EditText) findViewById(R.id.filenumber);
        EditText filetag = (EditText) findViewById(R.id.filetag);
        TextView startDate = (TextView) findViewById(R.id.startDate);
        TextView endDate = (TextView) findViewById(R.id.endDate);
        EditText mondayText1 = (EditText) findViewById(R.id.mondayText1);
        EditText mondayText2 = (EditText) findViewById(R.id.mondayText2);
        EditText mondayText3 = (EditText) findViewById(R.id.mondayText3);
        EditText mondayText4 = (EditText) findViewById(R.id.mondayText4);
        EditText mondayText5 = (EditText) findViewById(R.id.mondayText5);
        EditText mondayText6 = (EditText) findViewById(R.id.mondayText6);

        EditText mondayTextTime1 = (EditText) findViewById(R.id.mondayTextTime1);
        EditText mondayTextTime2 = (EditText) findViewById(R.id.mondayTextTime2);
        EditText mondayTextTime3 = (EditText) findViewById(R.id.mondayTextTime3);
        EditText mondayTextTime4 = (EditText) findViewById(R.id.mondayTextTime4);
        EditText mondayTextTime5 = (EditText) findViewById(R.id.mondayTextTime5);
        EditText mondayTextTime6 = (EditText) findViewById(R.id.mondayTextTime6);
        // End monday ---- Start tuesday
        EditText tuesdayText1 = (EditText) findViewById(R.id.tuesdayText1);
        EditText tuesdayText2 = (EditText) findViewById(R.id.tuesdayText2);
        EditText tuesdayText3 = (EditText) findViewById(R.id.tuesdayText3);
        EditText tuesdayText4 = (EditText) findViewById(R.id.tuesdayText4);
        EditText tuesdayText5 = (EditText) findViewById(R.id.tuesdayText5);
        EditText tuesdayText6 = (EditText) findViewById(R.id.tuesdayText6);

        EditText tuesdayTextTime1 = (EditText) findViewById(R.id.tuesdayTextTime1);
        EditText tuesdayTextTime2 = (EditText) findViewById(R.id.tuesdayTextTime2);
        EditText tuesdayTextTime3 = (EditText) findViewById(R.id.tuesdayTextTime3);
        EditText tuesdayTextTime4 = (EditText) findViewById(R.id.tuesdayTextTime4);
        EditText tuesdayTextTime5 = (EditText) findViewById(R.id.tuesdayTextTime5);
        EditText tuesdayTextTime6 = (EditText) findViewById(R.id.tuesdayTextTime6);
        // End tuesday ---- Start wednesday
        EditText wednesdayText1 = (EditText) findViewById(R.id.wednesdayText1);
        EditText wednesdayText2 = (EditText) findViewById(R.id.wednesdayText2);
        EditText wednesdayText3 = (EditText) findViewById(R.id.wednesdayText3);
        EditText wednesdayText4 = (EditText) findViewById(R.id.wednesdayText4);
        EditText wednesdayText5 = (EditText) findViewById(R.id.wednesdayText5);
        EditText wednesdayText6 = (EditText) findViewById(R.id.wednesdayText6);

        EditText wednesdayTextTime1 = (EditText) findViewById(R.id.wednesdayTextTime1);
        EditText wednesdayTextTime2 = (EditText) findViewById(R.id.wednesdayTextTime2);
        EditText wednesdayTextTime3 = (EditText) findViewById(R.id.wednesdayTextTime3);
        EditText wednesdayTextTime4 = (EditText) findViewById(R.id.wednesdayTextTime4);
        EditText wednesdayTextTime5 = (EditText) findViewById(R.id.wednesdayTextTime5);
        EditText wednesdayTextTime6 = (EditText) findViewById(R.id.wednesdayTextTime6);
        // End wednesday ---- Start thursday
        EditText thursdayText1 = (EditText) findViewById(R.id.thursdayText1);
        EditText thursdayText2 = (EditText) findViewById(R.id.thursdayText2);
        EditText thursdayText3 = (EditText) findViewById(R.id.thursdayText3);
        EditText thursdayText4 = (EditText) findViewById(R.id.thursdayText4);
        EditText thursdayText5 = (EditText) findViewById(R.id.thursdayText5);
        EditText thursdayText6 = (EditText) findViewById(R.id.thursdayText6);

        EditText thursdayTextTime1 = (EditText) findViewById(R.id.thursdayTextTime1);
        EditText thursdayTextTime2 = (EditText) findViewById(R.id.thursdayTextTime2);
        EditText thursdayTextTime3 = (EditText) findViewById(R.id.thursdayTextTime3);
        EditText thursdayTextTime4 = (EditText) findViewById(R.id.thursdayTextTime4);
        EditText thursdayTextTime5 = (EditText) findViewById(R.id.thursdayTextTime5);
        EditText thursdayTextTime6 = (EditText) findViewById(R.id.thursdayTextTime6);
        // End thursday ---- Start friday
        EditText fridayText1 = (EditText) findViewById(R.id.fridayText1);
        EditText fridayText2 = (EditText) findViewById(R.id.fridayText2);
        EditText fridayText3 = (EditText) findViewById(R.id.fridayText3);
        EditText fridayText4 = (EditText) findViewById(R.id.fridayText4);
        EditText fridayText5 = (EditText) findViewById(R.id.fridayText5);
        EditText fridayText6 = (EditText) findViewById(R.id.fridayText6);

        EditText fridayTextTime1 = (EditText) findViewById(R.id.fridayTextTime1);
        EditText fridayTextTime2 = (EditText) findViewById(R.id.fridayTextTime2);
        EditText fridayTextTime3 = (EditText) findViewById(R.id.fridayTextTime3);
        EditText fridayTextTime4 = (EditText) findViewById(R.id.fridayTextTime4);
        EditText fridayTextTime5 = (EditText) findViewById(R.id.fridayTextTime5);
        EditText fridayTextTime6 = (EditText) findViewById(R.id.fridayTextTime6);
        // End of friday and declaration weekdays editfields
        int id = item.getItemId();
        if (id == R.id.save_fragment) {
            try {
                File mydirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt");
                boolean success = true;
                if (!mydirectory.exists()) {
                    success = mydirectory.mkdir();
                }
                if (success) {
                    String filename = String.valueOf(AllReportsRV.itemTexte.get(weekID));
                    File myFile = new File(Environment.getExternalStorageDirectory() + File.separator + "/ReportIt/Reports" + File.separator + cutName(filename));
                    PrintWriter fOut = new PrintWriter(new FileWriter(myFile, false));
                    // String strFileName = myFile.getName();       ADD LATER
                    fOut.write(getString(R.string.monday)+";;");
                    fOut.write(String.valueOf(filenumber.getText().toString()+";;"));
                    fOut.write(String.valueOf(filetag.getText().toString()+";;"));
                    fOut.write(String.valueOf(startDate.getText().toString()+";;"));
                    fOut.write(String.valueOf(endDate.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayText1.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayTextTime1.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayText2.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayTextTime2.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayText3.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayTextTime3.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayText4.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayTextTime4.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayText5.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayTextTime5.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayText6.getText().toString()+";;"));
                    fOut.write(String.valueOf(mondayTextTime6.getText().toString()+";;\n"));
                    fOut.write(getString(R.string.tuesday)+";;");
                    fOut.write(String.valueOf(tuesdayText1.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayTextTime1.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayText2.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayTextTime2.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayText3.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayTextTime3.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayText4.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayTextTime4.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayText5.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayTextTime5.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayText6.getText().toString()+";;"));
                    fOut.write(String.valueOf(tuesdayTextTime6.getText().toString()+";;\n"));
                    fOut.write(getString(R.string.wednesday)+";;");
                    fOut.write(String.valueOf(wednesdayText1.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayTextTime1.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayText2.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayTextTime2.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayText3.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayTextTime3.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayText4.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayTextTime4.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayText5.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayTextTime5.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayText6.getText().toString()+";;"));
                    fOut.write(String.valueOf(wednesdayTextTime6.getText().toString()+";;\n"));
                    fOut.write(getString(R.string.thursday)+";;");
                    fOut.write(String.valueOf(thursdayText1.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayTextTime1.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayText2.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayTextTime2.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayText3.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayTextTime3.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayText4.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayTextTime4.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayText5.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayTextTime5.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayText6.getText().toString()+";;"));
                    fOut.write(String.valueOf(thursdayTextTime6.getText().toString()+";;\n"));
                    fOut.write(getString(R.string.friday)+";;");
                    fOut.write(String.valueOf(fridayText1.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayTextTime1.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayText2.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayTextTime2.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayText3.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayTextTime3.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayText4.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayTextTime4.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayText5.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayTextTime5.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayText6.getText().toString()+";;"));
                    fOut.write(String.valueOf(fridayTextTime6.getText().toString()+";;"));
                    fOut.close();
                }


                else {

                }

            } catch (Exception e) {
            }
            Snackbar snackbar = Snackbar.make(this.findViewById(R.id.export_pdf), R.string.saved_report, Snackbar.LENGTH_SHORT);
            snackbar.show();
            return true;
        }


        if (id == android.R.id.home) {
            new MaterialDialog.Builder(this)
                    .title(R.string.save)
                    .backgroundColor(getResources().getColor(R.color.cardColor))
                    .iconRes(R.drawable.ic_error_outline_red_500_24dp)
                    .content(R.string.save_data)
                    .positiveText(R.string.save)
                    .negativeText(R.string.cancel)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            saveData();
                            Intent intent = new Intent(FragmentActivity.this, AllReportsRV.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent intent = new Intent(FragmentActivity.this, AllReportsRV.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                    })
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if(view.isShown()) {
            final SharedPreferences pref = getApplicationContext().getSharedPreferences("buttonsPressed", MODE_APPEND);
            SharedPreferences.Editor editor = pref.edit();

            Boolean pressed1 = pref.getBoolean("bstartpressed", Boolean.parseBoolean(null));
            Boolean pressed2 = pref.getBoolean("bendpressed", Boolean.parseBoolean(null));

            if(pressed1==true){
                TextView startDate = (TextView) findViewById(R.id.startDate);
                startDate.setText(String.valueOf(dayOfMonth) + "." + String.valueOf(monthOfYear+1) + "." + String.valueOf(year));
                editor.putBoolean("bstartpressed", false);
                editor.commit();
            }
            if(pressed2==true) {
                TextView endDate = (TextView) findViewById(R.id.endDate);
                endDate.setText(String.valueOf(dayOfMonth) + "." + String.valueOf(monthOfYear+1) + "." + String.valueOf(year));
                editor.putBoolean("bendpressed", false);
                editor.commit();
            }

        }
    }



    public static class DatePickerFragment extends DialogFragment
    {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), R.style.DialogTheme, (FragmentActivity)getActivity(), year, month, day);

        }


    }

    private static String cutName(String str) {
        return str.substring(0,str.length()-0);
    }

    private void createCustomAnimation() {
        final FloatingActionMenu menu3 = (FloatingActionMenu) findViewById(R.id.menuShareReport);

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
                        ? R.drawable.ic_unarchive_white_24dp : R.drawable.ic_done_white_24dp);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        menu3.setIconToggleAnimatorSet(set);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment, menu);


        return super.onCreateOptionsMenu(menu);
    }


    public void saveData(){
        int weekID = getIntent().getIntExtra("weekID", 0);
        // Declaration weekdays editfields
        // Monday
        EditText filenumber = (EditText) findViewById(R.id.filenumber);
        EditText filetag = (EditText) findViewById(R.id.filetag);
        TextView startDate = (TextView) findViewById(R.id.startDate);
        TextView endDate = (TextView) findViewById(R.id.endDate);
        EditText mondayText1 = (EditText) findViewById(R.id.mondayText1);
        EditText mondayText2 = (EditText) findViewById(R.id.mondayText2);
        EditText mondayText3 = (EditText) findViewById(R.id.mondayText3);
        EditText mondayText4 = (EditText) findViewById(R.id.mondayText4);
        EditText mondayText5 = (EditText) findViewById(R.id.mondayText5);
        EditText mondayText6 = (EditText) findViewById(R.id.mondayText6);

        EditText mondayTextTime1 = (EditText) findViewById(R.id.mondayTextTime1);
        EditText mondayTextTime2 = (EditText) findViewById(R.id.mondayTextTime2);
        EditText mondayTextTime3 = (EditText) findViewById(R.id.mondayTextTime3);
        EditText mondayTextTime4 = (EditText) findViewById(R.id.mondayTextTime4);
        EditText mondayTextTime5 = (EditText) findViewById(R.id.mondayTextTime5);
        EditText mondayTextTime6 = (EditText) findViewById(R.id.mondayTextTime6);
        // End monday ---- Start tuesday
        EditText tuesdayText1 = (EditText) findViewById(R.id.tuesdayText1);
        EditText tuesdayText2 = (EditText) findViewById(R.id.tuesdayText2);
        EditText tuesdayText3 = (EditText) findViewById(R.id.tuesdayText3);
        EditText tuesdayText4 = (EditText) findViewById(R.id.tuesdayText4);
        EditText tuesdayText5 = (EditText) findViewById(R.id.tuesdayText5);
        EditText tuesdayText6 = (EditText) findViewById(R.id.tuesdayText6);

        EditText tuesdayTextTime1 = (EditText) findViewById(R.id.tuesdayTextTime1);
        EditText tuesdayTextTime2 = (EditText) findViewById(R.id.tuesdayTextTime2);
        EditText tuesdayTextTime3 = (EditText) findViewById(R.id.tuesdayTextTime3);
        EditText tuesdayTextTime4 = (EditText) findViewById(R.id.tuesdayTextTime4);
        EditText tuesdayTextTime5 = (EditText) findViewById(R.id.tuesdayTextTime5);
        EditText tuesdayTextTime6 = (EditText) findViewById(R.id.tuesdayTextTime6);
        // End tuesday ---- Start wednesday
        EditText wednesdayText1 = (EditText) findViewById(R.id.wednesdayText1);
        EditText wednesdayText2 = (EditText) findViewById(R.id.wednesdayText2);
        EditText wednesdayText3 = (EditText) findViewById(R.id.wednesdayText3);
        EditText wednesdayText4 = (EditText) findViewById(R.id.wednesdayText4);
        EditText wednesdayText5 = (EditText) findViewById(R.id.wednesdayText5);
        EditText wednesdayText6 = (EditText) findViewById(R.id.wednesdayText6);

        EditText wednesdayTextTime1 = (EditText) findViewById(R.id.wednesdayTextTime1);
        EditText wednesdayTextTime2 = (EditText) findViewById(R.id.wednesdayTextTime2);
        EditText wednesdayTextTime3 = (EditText) findViewById(R.id.wednesdayTextTime3);
        EditText wednesdayTextTime4 = (EditText) findViewById(R.id.wednesdayTextTime4);
        EditText wednesdayTextTime5 = (EditText) findViewById(R.id.wednesdayTextTime5);
        EditText wednesdayTextTime6 = (EditText) findViewById(R.id.wednesdayTextTime6);
        // End wednesday ---- Start thursday
        EditText thursdayText1 = (EditText) findViewById(R.id.thursdayText1);
        EditText thursdayText2 = (EditText) findViewById(R.id.thursdayText2);
        EditText thursdayText3 = (EditText) findViewById(R.id.thursdayText3);
        EditText thursdayText4 = (EditText) findViewById(R.id.thursdayText4);
        EditText thursdayText5 = (EditText) findViewById(R.id.thursdayText5);
        EditText thursdayText6 = (EditText) findViewById(R.id.thursdayText6);

        EditText thursdayTextTime1 = (EditText) findViewById(R.id.thursdayTextTime1);
        EditText thursdayTextTime2 = (EditText) findViewById(R.id.thursdayTextTime2);
        EditText thursdayTextTime3 = (EditText) findViewById(R.id.thursdayTextTime3);
        EditText thursdayTextTime4 = (EditText) findViewById(R.id.thursdayTextTime4);
        EditText thursdayTextTime5 = (EditText) findViewById(R.id.thursdayTextTime5);
        EditText thursdayTextTime6 = (EditText) findViewById(R.id.thursdayTextTime6);
        // End thursday ---- Start friday
        EditText fridayText1 = (EditText) findViewById(R.id.fridayText1);
        EditText fridayText2 = (EditText) findViewById(R.id.fridayText2);
        EditText fridayText3 = (EditText) findViewById(R.id.fridayText3);
        EditText fridayText4 = (EditText) findViewById(R.id.fridayText4);
        EditText fridayText5 = (EditText) findViewById(R.id.fridayText5);
        EditText fridayText6 = (EditText) findViewById(R.id.fridayText6);

        EditText fridayTextTime1 = (EditText) findViewById(R.id.fridayTextTime1);
        EditText fridayTextTime2 = (EditText) findViewById(R.id.fridayTextTime2);
        EditText fridayTextTime3 = (EditText) findViewById(R.id.fridayTextTime3);
        EditText fridayTextTime4 = (EditText) findViewById(R.id.fridayTextTime4);
        EditText fridayTextTime5 = (EditText) findViewById(R.id.fridayTextTime5);
        EditText fridayTextTime6 = (EditText) findViewById(R.id.fridayTextTime6);
        // End of friday and declaration weekdays editfields
        try {
            File mydirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt");
            boolean success = true;
            if (!mydirectory.exists()) {
                success = mydirectory.mkdir();
            }
            if (success) {
                String filename = String.valueOf(AllReportsRV.itemTexte.get(weekID));
                File myFile = new File(Environment.getExternalStorageDirectory() + File.separator + "/ReportIt/Reports" + File.separator + cutName(filename));
                PrintWriter fOut = new PrintWriter(new FileWriter(myFile, false));
                // String strFileName = myFile.getName();       ADD LATER
                fOut.write(getString(R.string.monday) + ";;");
                fOut.write(String.valueOf(filenumber.getText().toString()+";;"));
                fOut.write(String.valueOf(filetag.getText().toString()+";;"));
                fOut.write(String.valueOf(startDate.getText().toString()+";;"));
                fOut.write(String.valueOf(endDate.getText().toString()+";;"));
                fOut.write(String.valueOf(mondayText1.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayTextTime1.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayText2.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayTextTime2.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayText3.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayTextTime3.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayText4.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayTextTime4.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayText5.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayTextTime5.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayText6.getText().toString() + ";;"));
                fOut.write(String.valueOf(mondayTextTime6.getText().toString() + ";;\n"));
                fOut.write(getString(R.string.tuesday) + ";;");
                fOut.write(String.valueOf(tuesdayText1.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayTextTime1.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayText2.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayTextTime2.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayText3.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayTextTime3.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayText4.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayTextTime4.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayText5.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayTextTime5.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayText6.getText().toString() + ";;"));
                fOut.write(String.valueOf(tuesdayTextTime6.getText().toString() + ";;\n"));
                fOut.write(getString(R.string.wednesday) + ";;");
                fOut.write(String.valueOf(wednesdayText1.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayTextTime1.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayText2.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayTextTime2.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayText3.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayTextTime3.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayText4.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayTextTime4.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayText5.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayTextTime5.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayText6.getText().toString() + ";;"));
                fOut.write(String.valueOf(wednesdayTextTime6.getText().toString() + ";;\n"));
                fOut.write(getString(R.string.thursday) + ";;");
                fOut.write(String.valueOf(thursdayText1.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayTextTime1.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayText2.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayTextTime2.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayText3.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayTextTime3.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayText4.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayTextTime4.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayText5.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayTextTime5.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayText6.getText().toString() + ";;"));
                fOut.write(String.valueOf(thursdayTextTime6.getText().toString() + ";;\n"));
                fOut.write(getString(R.string.friday) + ";;");
                fOut.write(String.valueOf(fridayText1.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayTextTime1.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayText2.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayTextTime2.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayText3.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayTextTime3.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayText4.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayTextTime4.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayText5.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayTextTime5.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayText6.getText().toString() + ";;"));
                fOut.write(String.valueOf(fridayTextTime6.getText().toString() + ";;"));

                fOut.close();
            } else {

            }

        } catch (Exception e) {
        }

    }

    @Override
    public void onBackPressed() {
        new MaterialDialog.Builder(this)
                .title(R.string.save)
                .iconRes(R.drawable.ic_error_outline_red_500_24dp)
                .backgroundColor(getResources().getColor(R.color.cardColor))
                .content(R.string.save_data)
                .positiveText(R.string.save)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        saveData();
                        Intent intent = new Intent(FragmentActivity.this, AllReportsRV.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(FragmentActivity.this, AllReportsRV.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                })
                .show();



    }
}
