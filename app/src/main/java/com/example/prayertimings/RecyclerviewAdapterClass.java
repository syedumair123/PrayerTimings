package com.example.prayertimings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerviewAdapterClass extends  RecyclerView.Adapter<RecyclerviewAdapterClass.Myviewholder>{
    private LayoutInflater inflater;
    private ArrayList<PrayerTime> prayerTimeArrayList;
    public RecyclerviewAdapterClass(Context context, ArrayList<PrayerTime> prayerTimeArrayList){
        inflater= android.view.LayoutInflater.from(context);
        this.prayerTimeArrayList=prayerTimeArrayList;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = android.view.LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.single_prayer_time_layout, viewGroup, false);
        Myviewholder viewHolder = new Myviewholder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        myviewholder.fajrtime.setText(prayerTimeArrayList.get(i).getFajr());
        myviewholder.zuhurtime.setText(prayerTimeArrayList.get(i).getZhuhr());
        myviewholder.asrtime.setText(prayerTimeArrayList.get(i).getAsr());
        myviewholder.maghribtime.setText(prayerTimeArrayList.get(i).getMaghrib());
        myviewholder.ishatime.setText(prayerTimeArrayList.get(i).getIsha());
        String itemnum=""+(i+1);
        myviewholder.itemcoun.setText(itemnum);

    }

    @Override
    public int getItemCount() {
        return prayerTimeArrayList.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {

   TextView fajrtime,zuhurtime,asrtime,maghribtime,ishatime,itemcoun;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            fajrtime=(TextView) itemView.findViewById(R.id.fajrtime);
            zuhurtime=(TextView) itemView.findViewById(R.id.zuhurtime);
            asrtime=(TextView) itemView.findViewById(R.id.Asrtime);
            maghribtime=(TextView) itemView.findViewById(R.id.Maghribtime);
            ishatime=(TextView) itemView.findViewById(R.id.Ishatime);
            itemcoun=(TextView) itemView.findViewById(R.id.itemcount);
        }
    }
}


