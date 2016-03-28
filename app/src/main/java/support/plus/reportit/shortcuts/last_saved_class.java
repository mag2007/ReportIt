package support.plus.reportit.shortcuts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import support.plus.reportit.rv.FragmentActivity;

/**
 * Created by Wladislaw Tauberger on 24.03.16 at 16:05.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

    public class last_saved_class extends Activity{


    Activity activity;

        public last_saved_class(Activity activity){

            this.activity=activity;

            SharedPreferences prefLast = activity.getSharedPreferences("lastSaved", Context.MODE_PRIVATE);
            int weekID = prefLast.getInt("weekID", -1);
            String intfilename = prefLast.getString("intfilename", "");

            if(weekID >= 0){
                Intent intent = new Intent(activity, FragmentActivity.class);
                intent.putExtra("weekID", weekID);
                intent.putExtra("filename", intfilename);
                activity.startActivity(intent);
            } else {
                Toast.makeText(activity, "Please create your first Report first", Toast.LENGTH_LONG).show();
            }




        }
    }

