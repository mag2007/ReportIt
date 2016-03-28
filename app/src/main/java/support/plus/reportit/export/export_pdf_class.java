package support.plus.reportit.export;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import support.plus.reportit.R;
import support.plus.reportit.rv.AllReportsRV;

/**
 * Created by Wladislaw Tauberger on 24.03.16 at 16:05.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class export_pdf_class extends AppCompatActivity{

    public static Context context;

    Activity activity;

    public export_pdf_class(final Context context,Activity activity, final int weekID, String FILE){
        this.context=context;
        this.activity=activity;
        SharedPreferences pref = context.getSharedPreferences("userData", MODE_APPEND);
        String TextuserNameString = pref.getString("userName", "");
        String TextuserBossNameString = pref.getString("userBoss", "");
        String filename = String.valueOf(AllReportsRV.itemTexte.get(weekID));


        TextView filenameText = (TextView) activity.findViewById(R.id.filename);
        EditText filetag = (EditText) activity.findViewById(R.id.filetag);
        TextView fileauthorboss = (TextView) activity.findViewById(R.id.fileauthorboss);
        Button bStartDate = (Button) activity.findViewById(R.id.bStartDate);
        EditText filenumber = (EditText) activity.findViewById(R.id.filenumber);
        Button bEndDate = (Button) activity.findViewById(R.id.bEndDate);
        TextView startDate = (TextView) activity.findViewById(R.id.startDate);
        TextView endDate = (TextView) activity.findViewById(R.id.endDate);


        EditText mondayText1 = (EditText) activity.findViewById(R.id.mondayText1);
        EditText mondayText2 = (EditText) activity.findViewById(R.id.mondayText2);
        EditText mondayText3 = (EditText) activity.findViewById(R.id.mondayText3);
        EditText mondayText4 = (EditText) activity.findViewById(R.id.mondayText4);
        EditText mondayText5 = (EditText) activity.findViewById(R.id.mondayText5);
        EditText mondayText6 = (EditText) activity.findViewById(R.id.mondayText6);

        EditText mondayTextTime1 = (EditText) activity.findViewById(R.id.mondayTextTime1);
        EditText mondayTextTime2 = (EditText) activity.findViewById(R.id.mondayTextTime2);
        EditText mondayTextTime3 = (EditText) activity.findViewById(R.id.mondayTextTime3);
        EditText mondayTextTime4 = (EditText) activity.findViewById(R.id.mondayTextTime4);
        EditText mondayTextTime5 = (EditText) activity.findViewById(R.id.mondayTextTime5);
        EditText mondayTextTime6 = (EditText) activity.findViewById(R.id.mondayTextTime6);
        // End monday ---- Start tuesday
        EditText tuesdayText1 = (EditText) activity.findViewById(R.id.tuesdayText1);
        EditText tuesdayText2 = (EditText) activity.findViewById(R.id.tuesdayText2);
        EditText tuesdayText3 = (EditText) activity.findViewById(R.id.tuesdayText3);
        EditText tuesdayText4 = (EditText) activity.findViewById(R.id.tuesdayText4);
        EditText tuesdayText5 = (EditText) activity.findViewById(R.id.tuesdayText5);
        EditText tuesdayText6 = (EditText) activity.findViewById(R.id.tuesdayText6);

        EditText tuesdayTextTime1 = (EditText) activity.findViewById(R.id.tuesdayTextTime1);
        EditText tuesdayTextTime2 = (EditText) activity.findViewById(R.id.tuesdayTextTime2);
        EditText tuesdayTextTime3 = (EditText) activity.findViewById(R.id.tuesdayTextTime3);
        EditText tuesdayTextTime4 = (EditText) activity.findViewById(R.id.tuesdayTextTime4);
        EditText tuesdayTextTime5 = (EditText) activity.findViewById(R.id.tuesdayTextTime5);
        EditText tuesdayTextTime6 = (EditText) activity.findViewById(R.id.tuesdayTextTime6);
        // End tuesday ---- Start wednesday
        EditText wednesdayText1 = (EditText) activity.findViewById(R.id.wednesdayText1);
        EditText wednesdayText2 = (EditText) activity.findViewById(R.id.wednesdayText2);
        EditText wednesdayText3 = (EditText) activity.findViewById(R.id.wednesdayText3);
        EditText wednesdayText4 = (EditText) activity.findViewById(R.id.wednesdayText4);
        EditText wednesdayText5 = (EditText) activity.findViewById(R.id.wednesdayText5);
        EditText wednesdayText6 = (EditText) activity.findViewById(R.id.wednesdayText6);

        EditText wednesdayTextTime1 = (EditText) activity.findViewById(R.id.wednesdayTextTime1);
        EditText wednesdayTextTime2 = (EditText) activity.findViewById(R.id.wednesdayTextTime2);
        EditText wednesdayTextTime3 = (EditText) activity.findViewById(R.id.wednesdayTextTime3);
        EditText wednesdayTextTime4 = (EditText) activity.findViewById(R.id.wednesdayTextTime4);
        EditText wednesdayTextTime5 = (EditText) activity.findViewById(R.id.wednesdayTextTime5);
        EditText wednesdayTextTime6 = (EditText) activity.findViewById(R.id.wednesdayTextTime6);
        // End wednesday ---- Start thursday
        EditText thursdayText1 = (EditText) activity.findViewById(R.id.thursdayText1);
        EditText thursdayText2 = (EditText) activity.findViewById(R.id.thursdayText2);
        EditText thursdayText3 = (EditText) activity.findViewById(R.id.thursdayText3);
        EditText thursdayText4 = (EditText) activity.findViewById(R.id.thursdayText4);
        EditText thursdayText5 = (EditText) activity.findViewById(R.id.thursdayText5);
        EditText thursdayText6 = (EditText) activity.findViewById(R.id.thursdayText6);

        EditText thursdayTextTime1 = (EditText) activity.findViewById(R.id.thursdayTextTime1);
        EditText thursdayTextTime2 = (EditText) activity.findViewById(R.id.thursdayTextTime2);
        EditText thursdayTextTime3 = (EditText) activity.findViewById(R.id.thursdayTextTime3);
        EditText thursdayTextTime4 = (EditText) activity.findViewById(R.id.thursdayTextTime4);
        EditText thursdayTextTime5 = (EditText) activity.findViewById(R.id.thursdayTextTime5);
        EditText thursdayTextTime6 = (EditText) activity.findViewById(R.id.thursdayTextTime6);
        // End thursday ---- Start friday
        EditText fridayText1 = (EditText) activity.findViewById(R.id.fridayText1);
        EditText fridayText2 = (EditText) activity.findViewById(R.id.fridayText2);
        EditText fridayText3 = (EditText) activity.findViewById(R.id.fridayText3);
        EditText fridayText4 = (EditText) activity.findViewById(R.id.fridayText4);
        EditText fridayText5 = (EditText) activity.findViewById(R.id.fridayText5);
        EditText fridayText6 = (EditText) activity.findViewById(R.id.fridayText6);

        EditText fridayTextTime1 = (EditText) activity.findViewById(R.id.fridayTextTime1);
        EditText fridayTextTime2 = (EditText) activity.findViewById(R.id.fridayTextTime2);
        EditText fridayTextTime3 = (EditText) activity.findViewById(R.id.fridayTextTime3);
        EditText fridayTextTime4 = (EditText) activity.findViewById(R.id.fridayTextTime4);
        EditText fridayTextTime5 = (EditText) activity.findViewById(R.id.fridayTextTime5);
        EditText fridayTextTime6 = (EditText) activity.findViewById(R.id.fridayTextTime6);
        // End of friday and declaration weekdays editfields


        try {
            exportToPDF(FILE, String.valueOf(filenumber.getText().toString()),
                    String.valueOf(filetag.getText().toString()), String.valueOf(startDate.getText().toString()),
                    String.valueOf(endDate.getText().toString()), TextuserNameString, TextuserBossNameString,
                    String.valueOf(mondayText1.getText().toString()), String.valueOf(mondayText2.getText().toString()),
                    String.valueOf(mondayText3.getText().toString()), String.valueOf(mondayText4.getText().toString()),
                    String.valueOf(mondayText5.getText().toString()), String.valueOf(mondayText6.getText().toString()),
                    String.valueOf(tuesdayText1.getText().toString()), String.valueOf(tuesdayText2.getText().toString()),
                    String.valueOf(tuesdayText3.getText().toString()), String.valueOf(tuesdayText4.getText().toString()),
                    String.valueOf(tuesdayText5.getText().toString()), String.valueOf(tuesdayText6.getText().toString()),
                    String.valueOf(wednesdayText1.getText().toString()), String.valueOf(wednesdayText2.getText().toString()),
                    String.valueOf(wednesdayText3.getText().toString()), String.valueOf(wednesdayText4.getText().toString()),
                    String.valueOf(wednesdayText5.getText().toString()), String.valueOf(wednesdayText6.getText().toString()),
                    String.valueOf(thursdayText1.getText().toString()), String.valueOf(thursdayText2.getText().toString()),
                    String.valueOf(thursdayText3.getText().toString()), String.valueOf(thursdayText4.getText().toString()),
                    String.valueOf(thursdayText5.getText().toString()), String.valueOf(thursdayText6.getText().toString()),
                    String.valueOf(fridayText1.getText().toString()), String.valueOf(fridayText2.getText().toString()),
                    String.valueOf(fridayText3.getText().toString()), String.valueOf(fridayText4.getText().toString()),
                    String.valueOf(fridayText5.getText().toString()), String.valueOf(fridayText6.getText().toString()),
                    String.valueOf(mondayTextTime1.getText().toString()), String.valueOf(mondayTextTime2.getText().toString()),
                    String.valueOf(mondayTextTime3.getText().toString()), String.valueOf(mondayTextTime4.getText().toString()),
                    String.valueOf(mondayTextTime5.getText().toString()), String.valueOf(mondayTextTime6.getText().toString()),
                    String.valueOf(tuesdayTextTime1.getText().toString()), String.valueOf(tuesdayTextTime2.getText().toString()),
                    String.valueOf(tuesdayTextTime3.getText().toString()), String.valueOf(tuesdayTextTime4.getText().toString()),
                    String.valueOf(tuesdayTextTime5.getText().toString()), String.valueOf(tuesdayTextTime6.getText().toString()),
                    String.valueOf(wednesdayTextTime1.getText().toString()), String.valueOf(wednesdayTextTime2.getText().toString()),
                    String.valueOf(wednesdayTextTime3.getText().toString()), String.valueOf(wednesdayTextTime4.getText().toString()),
                    String.valueOf(wednesdayTextTime5.getText().toString()), String.valueOf(wednesdayTextTime6.getText().toString()),
                    String.valueOf(thursdayTextTime1.getText().toString()), String.valueOf(thursdayTextTime2.getText().toString()),
                    String.valueOf(thursdayTextTime3.getText().toString()), String.valueOf(thursdayTextTime4.getText().toString()),
                    String.valueOf(thursdayTextTime5.getText().toString()), String.valueOf(thursdayTextTime6.getText().toString()),
                    String.valueOf(fridayTextTime1.getText().toString()), String.valueOf(fridayTextTime2.getText().toString()),
                    String.valueOf(fridayTextTime3.getText().toString()), String.valueOf(fridayTextTime4.getText().toString()),
                    String.valueOf(fridayTextTime5.getText().toString()), String.valueOf(fridayTextTime6.getText().toString()));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Snackbar.make(activity.findViewById(R.id.coordinatorlayoutFragment2), context.getString(R.string.saved_report_pdf), Snackbar.LENGTH_LONG)
                .setActionTextColor(context.getResources().getColor(R.color.md_green_500))
                .setAction(R.string.open, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String filename = String.valueOf(AllReportsRV.itemTexte.get(weekID));
                        String filenameLoc = Environment.getExternalStorageDirectory() + File.separator + "ReportIt" + File.separator+  "Exported PDF's"+ File.separator + cutName(filename) + context.getString(R.string.pdf_extension);
                        Intent i = new Intent(Intent.ACTION_VIEW);
//set pdf to doc if you want to open doc
                        File myfile = new File(filenameLoc);
                        i.setDataAndType(Uri.fromFile(myfile), "application/pdf");
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        try
                        {
                            context.startActivity(i);
                        }
                        catch(ActivityNotFoundException e)
                        {
//if pdf reader not found than open browser to download pdf reader
                            Intent i1=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.adobe.reader&hl=en"));
                            context.startActivity(i1);
                        }
                    }
                }).show();

    }

    private static String cutName(String str) {
        return str.substring(0,str.length()-0);
    }

    public void exportToPDF(String RESULT, String filenumber, String filetag, String startdate,
                             String enddate, String TextuserNameString, String TextuserNameBossString, String mondayText1, String mondayText2, String mondayText3,
                             String mondayText4, String mondayText5, String mondayText6, String tuesdayText1,
                             String tuesdayText2, String tuesdayText3, String tuesdayText4, String tuesdayText5, String tuesdayText6,
                             String wednesdayText1, String wednesdayText2, String wednesdayText3, String wednesdayText4,
                             String wednesdayText5, String wednesdayText6, String thursdayText1, String thursdayText2,
                             String thursdayText3, String thursdayText4, String thursdayText5, String thursdayText6,
                             String fridayText1, String fridayText2, String fridayText3, String fridayText4,
                             String fridayText5, String fridayText6, String mondayTextTime1, String mondayTextTime2, String mondayTextTime3,
                             String mondayTextTime4, String mondayTextTime5, String mondayTextTime6, String tuesdayTextTime1,
                             String tuesdayTextTime2, String tuesdayTextTime3, String tuesdayTextTime4, String tuesdayTextTime5, String tuesdayTextTime6,
                             String wednesdayTextTime1, String wednesdayTextTime2, String wednesdayTextTime3, String wednesdayTextTime4,
                             String wednesdayTextTime5, String wednesdayTextTime6, String thursdayTextTime1, String thursdayTextTime2,
                             String thursdayTextTime3, String thursdayTextTime4, String thursdayTextTime5, String thursdayTextTime6,
                             String fridayTextTime1, String fridayTextTime2, String fridayTextTime3, String fridayTextTime4,
                             String fridayTextTime5, String fridayTextTime6) throws DocumentException, IOException {


        // step 1
        // Specifying the page size
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = createFirstTable(filenumber, filetag, startdate, enddate, TextuserNameString, TextuserNameBossString, mondayText1, mondayText2, mondayText3, mondayText4, mondayText5,
                mondayText6, tuesdayText1, tuesdayText2, tuesdayText3, tuesdayText4, tuesdayText5, tuesdayText6,
                wednesdayText1, wednesdayText2, wednesdayText3, wednesdayText4, wednesdayText5, wednesdayText6,
                thursdayText1, thursdayText2, thursdayText3, thursdayText4, thursdayText5, thursdayText6,
                fridayText1, fridayText2, fridayText3, fridayText4, fridayText5, fridayText6, mondayTextTime1,
                mondayTextTime2,  mondayTextTime3, mondayTextTime4,  mondayTextTime5,  mondayTextTime6, tuesdayTextTime1,
                tuesdayTextTime2,  tuesdayTextTime3,  tuesdayTextTime4,  tuesdayTextTime5,
                tuesdayTextTime6,  wednesdayTextTime1,  wednesdayTextTime2,  wednesdayTextTime3,
                wednesdayTextTime4,  wednesdayTextTime5,  wednesdayTextTime6,  thursdayTextTime1,
                thursdayTextTime2,  thursdayTextTime3,  thursdayTextTime4,  thursdayTextTime5,
                thursdayTextTime6,  fridayTextTime1,  fridayTextTime2,  fridayTextTime3,
                fridayTextTime4,  fridayTextTime5,  fridayTextTime6);
        document.add(table);
        document.add(new Paragraph(" "));
        document.add(createSecondTable());
        // step 5
        document.close();

    }


    public PdfPTable createFirstTable(String filenumber, String filetag, String startdate, String enddate, String TextUserNameString,
                                      String TextUserNameBossString, String mondayText1, String mondayText2, String mondayText3,
                                      String mondayText4, String mondayText5, String mondayText6, String tuesdayText1,
                                      String tuesdayText2, String tuesdayText3, String tuesdayText4, String tuesdayText5, String tuesdayText6,
                                      String wednesdayText1, String wednesdayText2, String wednesdayText3, String wednesdayText4,
                                      String wednesdayText5, String wednesdayText6, String thursdayText1, String thursdayText2,
                                      String thursdayText3, String thursdayText4, String thursdayText5, String thursdayText6,
                                      String fridayText1, String fridayText2, String fridayText3, String fridayText4,
                                      String fridayText5, String fridayText6, String mondayTextTime1, String mondayTextTime2, String mondayTextTime3,
                                      String mondayTextTime4, String mondayTextTime5,
                                      String mondayTextTime6, String tuesdayTextTime1, String tuesdayTextTime2, String tuesdayTextTime3,
                                      String tuesdayTextTime4, String tuesdayTextTime5, String tuesdayTextTime6,
                                      String wednesdayTextTime1, String wednesdayTextTime2, String wednesdayTextTime3, String wednesdayTextTime4,
                                      String wednesdayTextTime5, String wednesdayTextTime6, String thursdayTextTime1, String thursdayTextTime2,
                                      String thursdayTextTime3, String thursdayTextTime4, String thursdayTextTime5, String thursdayTextTime6,
                                      String fridayTextTime1, String fridayTextTime2, String fridayTextTime3, String fridayTextTime4,
                                      String fridayTextTime5, String fridayTextTime6) {




        String mon = String.valueOf(context.getResources().getText(R.string.monday_short));
        String tues = String.valueOf(context.getResources().getText(R.string.tuesday_short));
        String wed = String.valueOf(context.getResources().getText(R.string.wednesday_short));
        String thurs = String.valueOf(context.getResources().getText(R.string.thursday_short));
        String fri = String.valueOf(context.getResources().getText(R.string.friday_short));
        String hours = String.valueOf(context.getResources().getText(R.string.hours));
        String day = String.valueOf(context.getResources().getText(R.string.day));
        String reportNr = String.valueOf(context.getResources().getText(R.string.report_nr));
        String weekFrom = String.valueOf(context.getResources().getText(R.string.weekFrom));
        String weekTo = String.valueOf(context.getResources().getText(R.string.weekTo));
        String firmName = String.valueOf(context.getResources().getText(R.string.firmName));
        String instructorName = String.valueOf(context.getResources().getText(R.string.instructorName));


        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(450 / 5.23f);
        try {
            table.setWidths(new int[]{1, 9, 1});
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PdfPCell cell;

        Font fontbold = FontFactory.getFont("Times-Roman", 12, Font.BOLD);

        cell = new PdfPCell(new Phrase(reportNr + " " + filenumber + "                                                " + weekFrom + "   " + startdate + "   " + weekTo + "   " + enddate,fontbold));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(firmName + "   " + TextUserNameString + "                          " + instructorName + " " + TextUserNameBossString));
        cell.setColspan(3);
        table.addCell(cell);

        table.addCell(day);
        cell = new PdfPCell(new Phrase(filetag));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(hours);

        table.addCell(mon);
        cell = new PdfPCell(new Phrase(mondayText1));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(mondayTextTime1);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(mondayText2));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(mondayTextTime2);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(mondayText3));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(mondayTextTime3);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(mondayText4));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(mondayTextTime4);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(mondayText5));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(mondayTextTime5);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(mondayText6));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(mondayTextTime6);

        table.addCell(tues);
        cell = new PdfPCell(new Phrase(tuesdayText1));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(tuesdayTextTime1);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(tuesdayText2));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(tuesdayTextTime2);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(tuesdayText3));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(tuesdayTextTime3);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(tuesdayText4));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(tuesdayTextTime4);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(tuesdayText5));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(tuesdayTextTime5);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(tuesdayText6));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(tuesdayTextTime6);

        table.addCell(wed);
        cell = new PdfPCell(new Phrase(wednesdayText1));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(wednesdayTextTime1);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(wednesdayText2));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(wednesdayTextTime2);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(wednesdayText3));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(wednesdayTextTime3);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(wednesdayText4));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(wednesdayTextTime4);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(wednesdayText5));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(wednesdayTextTime5);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(wednesdayText6));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(wednesdayTextTime6);

        table.addCell(thurs);
        cell = new PdfPCell(new Phrase(thursdayText1));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(thursdayTextTime1);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(thursdayText2));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(thursdayTextTime2);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(thursdayText3));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(thursdayTextTime3);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(thursdayText4));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(thursdayTextTime4);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(thursdayText5));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(thursdayTextTime5);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(thursdayText6));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(thursdayTextTime6);

        table.addCell(fri);
        cell = new PdfPCell(new Phrase(fridayText1));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(fridayTextTime1);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(fridayText2));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(fridayTextTime2);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(fridayText3));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(fridayTextTime3);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(fridayText4));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(fridayTextTime4);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(fridayText5));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(fridayTextTime5);

        table.addCell(" ");
        cell = new PdfPCell(new Phrase(fridayText6));
        cell.setColspan(1);
        table.addCell(cell);
        table.addCell(fridayTextTime6);

        createSecondTable();
        return table;
    }

    public PdfPTable createSecondTable(){
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(450 / 5.23f);
        try {
            table.setWidths(new int[]{1,9,1});
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PdfPCell cell;

        String signatures = String.valueOf(context.getResources().getText(R.string.signatures));
        String trainee = String.valueOf(context.getResources().getText(R.string.trainee_only));
        String instructor = String.valueOf(context.getResources().getText(R.string.instructor_only));
        String deputy = String.valueOf(context.getResources().getText(R.string.desputy));
        String notes = String.valueOf(context.getResources().getText(R.string.notes));

        cell = new PdfPCell(new Phrase("\n\n  "+signatures+"\n" +
                "                                __________          __________          __________          __________" +
                "\n                                    "+trainee+"                   "+instructor+"               "+deputy+"                   "+notes));
        cell.setColspan(3);
        table.addCell(cell);

        return table;
    }





}
