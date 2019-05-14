package com.example.prayertimings;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<PrayerTime> prayerTimeList;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       // listView=(ListView) findViewById(R.id.listTime);
        recyclerView=(RecyclerView) findViewById(R.id.recylerview);
        prayerTimeList=new ArrayList<>();
        fetch_data(); // calling fetch data method to fetch the data and initialize recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void fetch_data(){
// making retrofit instance and passing it base url
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ApiInterface.Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
    //pass interface refverence to retrofit and it will generate class automatically
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
//making the call object
        Call<List<PrayerTime>> call=apiInterface.gettime();
        //calling enqueue method
        call.enqueue(new Callback<List<PrayerTime>>() {
            @Override
            public void onResponse(Call<List<PrayerTime>> call, Response<List<PrayerTime>> response) {
             //populate list with responce from the network
                prayerTimeList = (ArrayList<PrayerTime>) response.body();


              //set the recyclerview
                RecyclerviewAdapterClass adapterClass=new RecyclerviewAdapterClass(getApplicationContext(),prayerTimeList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapterClass);
            }

            RecyclerviewAdapterClass adapterClass=new RecyclerviewAdapterClass(getApplicationContext(),prayerTimeList);



            @Override
            public void onFailure(Call<List<PrayerTime>> call, Throwable t) {

            }
        });
    }
}
