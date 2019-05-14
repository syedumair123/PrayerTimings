package com.example.prayertimings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetTimeFormat extends AppCompatActivity {
Button twentyfourFormat,TwelveFormat;
SharedPreferences format;
SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_format);
        twentyfourFormat=(Button) findViewById(R.id.twentyfour);
        TwelveFormat=(Button) findViewById(R.id.twelve_hr);
        format=getSharedPreferences("timeformat",MODE_PRIVATE);
        editor=format.edit();

        twentyfourFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            editor.putInt("format=",24);
            editor.commit();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

            }
        });
        TwelveFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("format=",12);
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
