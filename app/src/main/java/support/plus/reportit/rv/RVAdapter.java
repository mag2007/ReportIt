package support.plus.reportit.rv;


import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import support.plus.reportit.R;

/**
 * Created by Wladislaw Tauberger on 22.02.16 at 16:07.
 * Copyright (C) 2016  Wladislaw Tauberger
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolderKlasse> {


    public class ViewHolderKlasse extends RecyclerView.ViewHolder{

        TextView itemTextView;



        public ViewHolderKlasse(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.textViewItem);

        }
    }

/* Within the RecyclerView.Adapter class */


    @Override
    public ViewHolderKlasse onCreateViewHolder(ViewGroup viewGroup, int i) {


        View itemView1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_rv, null);

        return new ViewHolderKlasse(itemView1);
    }

    @Override
    public void onBindViewHolder(final ViewHolderKlasse viewHolderKlasse, final int i) {

        viewHolderKlasse.itemTextView.setText(AllReportsRV.itemTexte.get(i));

        viewHolderKlasse.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*int weekNr = i;
                weekNr++;
                Snackbar.make(v, "Week Nr. " +weekNr, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent =new Intent(v.getContext(), FragmentActivity.class);
                intent.putExtra("weekID", i);
                intent.putExtra("filename", String.valueOf(AllReportsRV.itemTexte.get(i)));
                v.getContext().startActivity(intent);





            }
        });

        viewHolderKlasse.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new MaterialDialog.Builder(v.getContext())
                        .title("Delete this file?")
                        .content("Are you sure you want to delete this file? \n\nIt can not be undone!")
                        .backgroundColor(v.getContext().getResources().getColor(R.color.cardColor))
                        .positiveText(R.string.yes)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                String filename = String.valueOf(AllReportsRV.itemTexte.get(i));
                                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "/ReportIt/Reports" + File.separator + filename);
                                boolean deleted = file.delete();
                                AllReportsRV.itemTexte.remove(i);
                                notifyDataSetChanged();
                            }
                        })
                        .negativeText(R.string.no)
                        .iconRes(R.drawable.ic_error_outline_red_500_24dp)
                        .show();

                return false;
            }
        });

    }



    @Override
    public int getItemCount() {
        return AllReportsRV.itemTexte.size();
    }

}