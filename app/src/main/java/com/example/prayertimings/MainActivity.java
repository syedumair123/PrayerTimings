package com.example.prayertimings;

        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.location.LocationListener;
        import android.os.Build;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.widget.Toast;

        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
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
        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        prayerTimeList = new ArrayList<>();

        fetch_data(); // calling fetch data method to fetch the data and initialize recyclerview

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void fetch_data() {
// making retrofit instance and passing it base url
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiInterface.Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        //pass interface refverence to retrofit and it will generate class automatically
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//making the call object
        Call<List<PrayerTime>> call = apiInterface.gettime();
        //calling enqueue method
        call.enqueue(new Callback<List<PrayerTime>>() {
            @Override
            public void onResponse(Call<List<PrayerTime>> call, Response<List<PrayerTime>> response) {
                //populate list with responce from the network
                prayerTimeList = (ArrayList<PrayerTime>) response.body();

                setCalendar(prayerTimeList);
                //set the recyclerview
                RecyclerviewAdapterClass adapterClass = new RecyclerviewAdapterClass(getApplicationContext(), prayerTimeList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapterClass);
            }

            RecyclerviewAdapterClass adapterClass = new RecyclerviewAdapterClass(getApplicationContext(), prayerTimeList);


            @Override
            public void onFailure(Call<List<PrayerTime>> call, Throwable t) {

            }
        });
    }

    private void setCalendar(ArrayList<PrayerTime> prayerTimeList) {
        PendingIntent pendingIntent;
        Date hour1 = null;
        Date minuutes1=null;
      //  if (Build.VERSION.SDK_INT >= 23) {
        String fajrTime = prayerTimeList.get(0).getFajr();
        String fajrTimehour = fajrTime.substring(0, 2);
        String fajrTimeMinutes = fajrTime.substring(3, 5);
        //setting zuhur time
        String ZuhrTime = prayerTimeList.get(0).getZhuhr();
        String ZuhrTimehour = ZuhrTime.substring(0, 2);
        String ZuhurTimeMinutes = ZuhrTime.substring(3, 5);
        //setting asr time

            String AsrTime = prayerTimeList.get(0).getAsr();
            String AsrTimehour = AsrTime.substring(0, 2);
            String AsrTimeMinutes = AsrTime.substring(3, 5);
            //setting MaghribTime
        String MaghribTime = prayerTimeList.get(0).getMaghrib();
        String MaghribTimehour = MaghribTime.substring(0, 2);
        String MaghribTimeMinutes = MaghribTime.substring(3, 5);
        //setting isha time
        String IShaTime = prayerTimeList.get(0).getIsha();
        String IShaTimehour = IShaTime.substring(0, 2);
        String IShaTimeMinutes = IShaTime.substring(3, 5);
        //setting calendars
            Calendar calendarfajar=Calendar.getInstance();
            Calendar calendarzuhur=Calendar.getInstance();
            Calendar calendarAsr=Calendar.getInstance();
            Calendar calendarMaghrib=Calendar.getInstance();
            Calendar calendarISha=Calendar.getInstance();
            //setting fajar calendar
        calendarfajar.set(calendarfajar.get(Calendar.YEAR), calendarfajar.get(Calendar.MONTH), calendarfajar.get(Calendar.DAY_OF_MONTH),
                Integer.parseInt(fajrTimehour),Integer.parseInt(fajrTimeMinutes), 0);
        //setting zuhur calendar
        calendarzuhur.set(calendarzuhur.get(Calendar.YEAR), calendarzuhur.get(Calendar.MONTH), calendarzuhur.get(Calendar.DAY_OF_MONTH),
                Integer.parseInt(ZuhrTimehour),Integer.parseInt(ZuhurTimeMinutes), 0);
//settingAsrcalendar
            calendarAsr.set(calendarAsr.get(Calendar.YEAR), calendarAsr.get(Calendar.MONTH), calendarAsr.get(Calendar.DAY_OF_MONTH),
                 Integer.parseInt(AsrTimehour),Integer.parseInt(AsrTimeMinutes), 0);
            //setting maghrib calendar
        calendarMaghrib.set(calendarMaghrib.get(Calendar.YEAR), calendarMaghrib.get(Calendar.MONTH), calendarMaghrib.get(Calendar.DAY_OF_MONTH),
                Integer.parseInt(ZuhrTimehour),Integer.parseInt(ZuhurTimeMinutes), 0);
        //setting ISha Calendar
        calendarISha.set(calendarISha.get(Calendar.YEAR), calendarISha.get(Calendar.MONTH), calendarISha.get(Calendar.DAY_OF_MONTH),
                Integer.parseInt(IShaTimehour),Integer.parseInt(IShaTimeMinutes), 0);

            ArrayList<Calendar> calendars=new ArrayList<>();
            calendars.add(calendarfajar);
        calendars.add(calendarzuhur);
        calendars.add(calendarAsr);
        calendars.add(calendarMaghrib);
        calendars.add(calendarISha);
            setAlarm(calendars);







    }


public void setAlarm(ArrayList<Calendar> calendars){
for(int j=0;j<calendars.size();j++){
    if(System.currentTimeMillis()>calendars.get(j).getTimeInMillis()){
       calendars.get(j).add(Calendar.DATE,1);

    }
    long time= calendars.get(j).getTimeInMillis();

    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    Intent intent = new Intent(this, AlarmNotificationReciever.class);
    intent.putExtra("azan name",j);
    PendingIntent pi = PendingIntent.getBroadcast(this, j, intent, 0);
    am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi);;


}}}

