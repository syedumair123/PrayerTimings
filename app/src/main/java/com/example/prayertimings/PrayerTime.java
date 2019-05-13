package com.example.prayertimings;

import com.google.gson.annotations.SerializedName;

public class PrayerTime {
    @SerializedName("Fajr")
    private String Fajr;
    private String Zhuhr;
    private String Asr;
    private String Maghrib;
    private String Isha;
    private String Date;
    public PrayerTime(String Fajr,String Zhuhr,String Asr,String Maghrib,String Isha ,String Date){

        this.Fajr=Fajr;
        this.Zhuhr=Zhuhr;
        this.Asr=Asr;
        this.Maghrib=Maghrib;
        this.Isha=Isha;
        this.Date=Date;

    }

    public String getFajr() {
        return Fajr;
    }

    public String getZhuhr() {
        return Zhuhr;
    }

    public String getAsr() {
        return Asr;
    }

    public String getMaghrib() {
        return Maghrib;
    }

    public String getIsha() {
        return Isha;
    }

    public String getDate() {
        return Date;
    }
}
