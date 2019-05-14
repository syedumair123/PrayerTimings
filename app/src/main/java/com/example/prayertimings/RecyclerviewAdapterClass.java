package com.example.prayertimings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerviewAdapterClass extends  RecyclerView.Adapter<RecyclerviewAdapterClass.Myviewholder> {
    private LayoutInflater inflater;
    SharedPreferences sharedPreferences;
    int check_format;

    private ArrayList<PrayerTime> prayerTimeArrayList;

    public RecyclerviewAdapterClass(Context context, ArrayList<PrayerTime> prayerTimeArrayList) {
        inflater = android.view.LayoutInflater.from(context);
        this.prayerTimeArrayList = prayerTimeArrayList;
        sharedPreferences = context.getSharedPreferences("timeformat", MODE_PRIVATE);
        check_format = sharedPreferences.getInt("format=", 24);
        Log.d("ddd", String.valueOf(check_format));

    }
    //this function ius converting 24-hpur time to 12 hour format
    private String convert_format(String time){
        String time_12=null;

        DateFormat f1=new SimpleDateFormat("HH:mm");
        DateFormat f2=new SimpleDateFormat("hh:mm");
        Date date;
        try {
            date = f1.parse(time);
            time_12=f2.format(date);

        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return time_12;
    }
    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = android.view.LayoutInflater.from(viewGroup.getContext());
        View listItem = layoutInflater.inflate(R.layout.single_prayer_time_layout, viewGroup, false);


        Myviewholder viewHolder = new Myviewholder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {

//check if user has set his preference for 12-hour-format
        if (check_format == 12) {
            String Asr = prayerTimeArrayList.get(i).getAsr();
            String Maghrib = prayerTimeArrayList.get(i).getMaghrib();
            String Isha = prayerTimeArrayList.get(i).getIsha();
            String Asr_12 = convert_format(Asr);
            String MAghrib_12 = convert_format(Maghrib);
            String ISha_12 = convert_format(Isha);
            myviewholder.fajrtime.setText(prayerTimeArrayList.get(i).getFajr());
            myviewholder.zuhurtime.setText(prayerTimeArrayList.get(i).getZhuhr());
            myviewholder.asrtime.setText(Asr_12);
            myviewholder.maghribtime.setText(MAghrib_12);
            myviewholder.ishatime.setText(ISha_12);
            String itemnum = "" + (i + 1);
            myviewholder.itemcoun.setText(itemnum);

        }
//timings are already in 24-hour-format as user is desiring so no need to convert
else {
            myviewholder.fajrtime.setText(prayerTimeArrayList.get(i).getFajr());
            myviewholder.zuhurtime.setText(prayerTimeArrayList.get(i).getZhuhr());
            myviewholder.asrtime.setText(prayerTimeArrayList.get(i).getAsr());
            myviewholder.maghribtime.setText(prayerTimeArrayList.get(i).getMaghrib());
            myviewholder.ishatime.setText(prayerTimeArrayList.get(i).getIsha());
            String itemnum = "" + (i + 1);
            myviewholder.itemcoun.setText(itemnum);
        }
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


