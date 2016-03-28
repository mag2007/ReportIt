package support.plus.reportit.export;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import support.plus.reportit.R;

/**
 * Created by Wladislaw Tauberger on 24.03.16 at 16:05.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class export_email_class extends AppCompatActivity{

    public static Context context;

    Activity activity;

    public export_email_class(final Context context,Activity activity, final int weekID, String FILE){

        this.context=context;
        this.activity=activity;


        File file = new File(FILE);
        Uri pngUri = Uri.fromFile(file);
        if(!file.exists()){
            export_pdf_class export_pdf_class = new export_pdf_class(context, activity, weekID, FILE);
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.report_name);
        shareIntent.putExtra(Intent.EXTRA_STREAM, pngUri);
        context.startActivity(shareIntent);


    }



}
