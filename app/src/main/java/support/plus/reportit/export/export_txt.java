package support.plus.reportit.export;


import android.content.Context;
import android.widget.Toast;



/**
 * Created by Wladislaw Tauberger on 24.03.16 at 16:05.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class export_txt {


    private static Context context;
    public export_txt(Context c) {
        context = c;
    }

   public void import_data_txt(String RESULT){

       Toast.makeText(context, RESULT, Toast.LENGTH_SHORT).show();


    }
}
