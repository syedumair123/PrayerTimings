package com.example.prayertimings;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String Base_Url="http://masjidi.co.uk/api/";
    @GET("getMontlyPrayerTime/38/2019-04-23/B")
    Call<List<PrayerTime>> gettime();
}
