package com.example.gayathri.widget2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class SampleWidget extends AppWidgetProvider {
    SharedPreferences preferences;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int mywidgetid:appWidgetIds){
            preferences=context.getSharedPreferences(MainActivity.name,Context.MODE_PRIVATE);
            String s=preferences.getString("gayi","no data");
                Intent intent=new Intent(context,MainActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(context,1,intent,0);
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.design);
                views.setTextViewText(R.id.textview,s);
                views.setOnClickPendingIntent(R.id.textview,pendingIntent);
                appWidgetManager.updateAppWidget(mywidgetid, views);




        }
    }
}
