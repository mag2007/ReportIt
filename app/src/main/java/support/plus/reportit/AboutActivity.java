package support.plus.reportit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.GnuGeneralPublicLicense30;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

/**
 * Created by Wladislaw Tauberger on 24.03.16 at 16:05.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Toolbar toolbar = (Toolbar) findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.about);
        toolbar.setNavigationIcon(R.drawable.ic_back);



        // *** Declaration buttons, texts...
       Button bversion = (Button) findViewById(R.id.bversion);
       Button bchangelog = (Button) findViewById(R.id.bchangelog);
       Button blicenses = (Button) findViewById(R.id.blicenses);
        Button bcredits = (Button) findViewById(R.id.bcredits);

        // TextView nameMe = (TextView) findViewById(R.id.nameMe);
        Button bnameMe = (Button) findViewById(R.id.bnameMe);
        Button bweblMe = (Button) findViewById(R.id.bwebMe);
        Button bgoogleMe = (Button) findViewById(R.id.bgoogleMe);
        Button binstaMe = (Button) findViewById(R.id.binstaMe);
        Button blinkedMe = (Button) findViewById(R.id.blinkedMe);
        Button bnameRegi = (Button) findViewById(R.id.bnameRegi);
        Button binstaRegi = (Button) findViewById(R.id.binstaRegi);
        Button bnameHolger = (Button) findViewById(R.id.bnameHolger);
        Button bwebHolger = (Button) findViewById(R.id.bwebHolger);

        // end of declaration ***

        // *** setting my name in deffernet size
        bnameMe.setText(Html.fromHtml("<html><body>Wladislaw Tauberger<br><small>Worms, Germany</small></body><html>"));
        bnameRegi.setText(Html.fromHtml("<html><body>Regina Stirz<br><small>Girlfriend, translate in german, design tips</small></body><html>"));
        bnameHolger.setText(Html.fromHtml("<html><body>Holger Strunk<br><small>Collegue, help with coding, debugging</small></body><html>"));

        // end of name ***

        // *** Getting version and setting it in string
        // int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

       bversion.setText(Html.fromHtml("<html><body>Version: <br><small>" + versionName +"</small></body><html>"));

        // end of app version ***


        // *** Open my website on click
        bweblMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://tauberger.net");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        // end of opening site ***

        // *** Open Holger website on click
        bwebHolger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://strunk.info");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        // end of opening site ***

        // *** Open my gplus profile on click
        bgoogleMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://plus.google.com/+WladislawTauberger/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        // end of opening gplus ***

        // *** Open my insta on click
        binstaMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/wladrus/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        // end of opening insta ***

        // *** Open regi insta on click
        binstaRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/reginastirz/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        // end of opening insta ***


        // *** Open my linkedin on click
        blinkedMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/taubergerw");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        // end of opening linkedin ***

    }

    // *** Call changelog on click
    public void onClick4(View v) {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title(R.string.app_name)
                .customView(R.layout.license, wrapInScrollView)
                .backgroundColor(getResources().getColor(R.color.cardColor))
                .positiveText(R.string.close)
                .show();
    }

    // end of changelog ***

    // *** Call changelog on click
    public void onClick1(View v) {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title(R.string.app_name)
                .customView(R.layout.changelog, wrapInScrollView)
                .backgroundColor(getResources().getColor(R.color.cardColor))
                .positiveText(R.string.close)
                .show();
    }

    // end of changelog ***

    // *** Call credits on click
    public void onClick3(View v) {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title(R.string.credits)
                .backgroundColor(getResources().getColor(R.color.cardColor))
                .customView(R.layout.credits, wrapInScrollView)
                .positiveText(R.string.close)
                .show();
    }

    // end of changelog ***

    // *** Call licenses on click
    public void onClick2(View v) {
        final Notices notices = new Notices();
        notices.addNotice(new Notice("License Dialog", "https://psdev.de/LicensesDialog/", "Copyright 2013 Philip Schiffer", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Material design icon generator plugin", "https://github.com/konifar/android-material-design-icon-generator-plugin", "Copyright 2014-2015 Yusuke Konishi", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Material dialogs", "https://github.com/afollestad/material-dialogs", "Copyright (c) 2015 Aidan Michael Follestad", new MITLicense()));
        notices.addNotice(new Notice("AppIntro", "https://github.com/PaoloRotolo/AppIntro", "Copyright 2015 Paolo Rotolo", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Android View Animations", "https://github.com/daimajia/AndroidViewAnimations", "Copyright (c) 2014 daimajia", new MITLicense()));
        notices.addNotice(new Notice("FloatingActionButton", "https://github.com/Clans/FloatingActionButton", "Copyright 2015 Dmytro Tarianyk", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("iTextPDF", "https://github.com/itext/itextpdf", "", new GnuGeneralPublicLicense30()));
        notices.addNotice(new Notice("Digitus", "https://github.com/afollestad/digitus", "Copyright 2016 Aidan Follestad", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Android In-App Billing v3 Library", "https://github.com/anjlab/android-inapp-billing-v3", "Copyright 2014 AnjLab", new ApacheSoftwareLicense20()));


        new LicensesDialog.Builder(this)
                .setNotices(notices)
                .setIncludeOwnLicense(false)
                .build()
                .show();
    }

    // end of licenses ***

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
