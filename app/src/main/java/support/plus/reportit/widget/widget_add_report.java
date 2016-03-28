package support.plus.reportit.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

import android.content.Intent;
import android.widget.RemoteViews;

import support.plus.reportit.MainActivity;
import support.plus.reportit.R;
import support.plus.reportit.rv.AllReportsRV;
import support.plus.reportit.splashscreen;


/**
 * Created by Wladislaw Tauberger on 24.03.16 at 16:05.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class widget_add_report extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_add_report);

        Intent addIntent = new Intent(context, AllReportsRV.class);
        addIntent.putExtra("addClicked2", true);
        PendingIntent addPendingIntent = PendingIntent.getActivity(context, 0, addIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.add_report_button, addPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);


        Intent openAppIntent = new Intent(context, splashscreen.class);
        PendingIntent openAppPendingIntent = PendingIntent.getActivity(context, 1, openAppIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.openApp, openAppPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);


        Intent lastReportIntent = new Intent(context, MainActivity.class);
        lastReportIntent.putExtra("loadLast", true);
        PendingIntent lastReportPendingIntent = PendingIntent.getActivity(context, 2, lastReportIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.openLastReport, lastReportPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);


    }


}
