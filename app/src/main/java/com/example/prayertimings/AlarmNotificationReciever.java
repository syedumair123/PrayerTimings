package com.example.prayertimings;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmNotificationReciever extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        MediaPlayer azan= MediaPlayer.create(context,R.raw.azan_in_islam);
        azan.start();
        String azan_name = null;
        
int j=intent.getIntExtra("azan name",1);
switch(j){
    case 0:
        azan_name="fajar";
        break;
    case 1:
        azan_name="zuhur";
        break;
    case 2:
        azan_name="Asar";
        break;
    case 3 :
        azan_name="Maghrib";
        break;
    case 4:
        azan_name="Isha";
        break;
    
}
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("prayer Alarm")
                .setContentText("alarm for namze-e-"+azan_name);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());

        Toast.makeText(context,"Alaram for "+azan_name ,Toast.LENGTH_LONG).show();
        Log.d("MyAlarm", "Alarm just fired");}
    }