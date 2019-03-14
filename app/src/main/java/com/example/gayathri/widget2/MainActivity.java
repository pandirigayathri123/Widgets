package com.example.gayathri.widget2;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static String name="com.example.gayathri.widget2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        String[] s=getResources().getStringArray(R.array.names);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,s);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg=parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                sp=getSharedPreferences(name,MODE_PRIVATE);
                editor=sp.edit();
                StringBuffer buffer=new StringBuffer();
                buffer.append(msg);
                editor.putString("gayi",buffer.toString());
                editor.apply();
                Intent i=new Intent(MainActivity.this,SampleWidget.class);
                i.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

                int[] ids=AppWidgetManager.getInstance(MainActivity.this).getAppWidgetIds
                        (new ComponentName(getApplication(),SampleWidget.class));
                i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                sendBroadcast(i);
            }
        });
    }
}
