package support.plus.reportit.rv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import support.plus.reportit.MainActivity;
import support.plus.reportit.R;

/**
 * Created by Wladislaw Tauberger on 24.03.16 at 16:05.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class AllReportsRV extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    SwipeRefreshLayout mSwipeRefreshLayout;


    RecyclerView recyclerView1;
    RecyclerView.Adapter rvadapter1;
    RecyclerView.LayoutManager rvLayoutManager1;

    public static ArrayList<String> itemTexte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);


        Toolbar toolbar = (Toolbar) findViewById(R.id.rv_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Your reports");
        toolbar.setNavigationIcon(R.drawable.ic_back);

        itemTexte = new ArrayList<>();

        /* example---add later
        itemTexte.addAll(Arrays.asList("Example week", "Long press", "Add new"));
        item2Texte.addAll(Arrays.asList("Here will be the date of creation", "to delete card", "with buttom below"));
        */

        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);
        rvLayoutManager1 = new LinearLayoutManager(this);
        recyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView1.setLayoutManager(rvLayoutManager1);
        com.github.clans.fab.FloatingActionButton fbAddReport = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fbAddReport);

        rvadapter1 = new RVAdapter();
        recyclerView1.setAdapter(rvadapter1);

        add_items_setup();

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rvadapter1.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
                finish();
                startActivity(getIntent());
                }

        });


        Intent intent = getIntent();
        boolean addClicked = intent.getBooleanExtra("addClicked", false);
        if(addClicked){
            fbAddReport.performClick();
        }


        Intent addIntent = getIntent();
        boolean click =  addIntent.getBooleanExtra("addClicked2", false);
        if(click){
            fbAddReport.performClick();
        }


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

            } else {

            }

        } catch (Exception e) {
        }

        List<String> files = getList(new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt" +File.separator+  "Reports"));

    }



    private List<String> getList(File parentDir) {
        ArrayList<String> inFiles = new ArrayList<String>();
        String[] fileNames = parentDir.list();
        for (String fileName : fileNames) {
            if (fileName.toLowerCase().endsWith("")) {
                inFiles.add(fileName);
            } else {
                File file = new File(File.separator + fileName);
                if (file.isDirectory()) {
                    inFiles.addAll(getList(file));
                }
            }
        }
        itemTexte.addAll(Arrays.asList(fileNames));
        return inFiles;
    }

    private static String cutName(String str) {
        return str.substring(0,str.length()-0);
    }




    public void add_items_setup() {
        com.github.clans.fab.FloatingActionButton fbAddReport = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fbAddReport);

        fbAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(AllReportsRV.this)
                        .title(R.string.add_report)
                        .content(R.string.add_reportdesc)
                        .inputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)
                        .inputRange(1, 15)
                        .backgroundColor(getResources().getColor(R.color.cardColor))
                        .input(getString(R.string.example_work), "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                String name = String.valueOf(input);
                                File myFile2 = new File(Environment.getExternalStorageDirectory() + File.separator + "/ReportIt/Reports/" + name);
                                if (myFile2.exists()) {
                                    Toast toast = Toast.makeText(AllReportsRV.this, R.string.error_file_exists, Toast.LENGTH_SHORT);
                                    toast.show();
                                    return;
                                } else {
                                    Calendar c = Calendar.getInstance();
                                    int weekNo = c.get(Calendar.WEEK_OF_YEAR);
                                    itemTexte.add(name);
                                    rvadapter1.notifyDataSetChanged();
                                    recyclerView1.smoothScrollToPosition(itemTexte.size());
                                    try {
                                        File mydirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "ReportIt");
                                        boolean success = true;
                                        if (!mydirectory.exists()) {
                                            success = mydirectory.mkdir();
                                        }
                                        if (success) {
                                            File reportsdirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "/ReportIt/Reports");
                                            boolean success2 = true;
                                            if (!reportsdirectory.exists()) {
                                                success2 = reportsdirectory.mkdir();
                                            }
                                            if (success2) {
                                                File myFile = new File(Environment.getExternalStorageDirectory() + File.separator + "/ReportIt/Reports/" + name);
                                                if (!myFile.exists()) {
                                                    myFile.createNewFile();
                                                }

                                            }


                                        } else {

                                        }

                                    } catch (Exception e) {
                                    }
                                }
                                return;
                            }
                        })
                        .cancelable(false)
                        .positiveText(R.string.add)
                        .negativeText(R.string.cancel)
                        .show();
            }
        });

    }

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-4);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void onRefresh() {

    }
}