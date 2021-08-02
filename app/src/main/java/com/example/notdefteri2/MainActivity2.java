package com.example.notdefteri2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button kaydet;
    Button iptal;
    EditText baslik;
    EditText icerik;
    TextView DateAndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        kaydet= (Button) findViewById(R.id.kaydet);
        iptal= (Button) findViewById(R.id.iptal);
        baslik= (EditText) findViewById(R.id.baslik);
        icerik= (EditText) findViewById(R.id.icerik);
        DateAndTime = (TextView) findViewById(R.id.DateAndTime);
        kaydet.setOnClickListener(this);
        iptal.setOnClickListener(this);

        if(MainActivity.cameFromCardview){
            baslik.setText(MainActivity.item.str1);
            icerik.setText(MainActivity.item.str2);
            DateAndTime.setText(MainActivity.item.str3);
            MainActivity.cameFromCardview = false;
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==kaydet.getId()){
            save();
            Toast.makeText(this,"Kaydedildi",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity2.this,MainActivity.class);
            startActivity(i);
        }
        else if(view.getId()==iptal.getId()){
            cancel();
        }
    }

    public void save(){
        Not not= new Not();
        String baslik_str = baslik.getText().toString();
        String icerik_str = icerik.getText().toString();
        not.setBaslik(baslik_str);
        not.setIcerik(icerik_str);

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedUTC = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Asia/Istanbul"));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = zonedIST.format(format);
        DateAndTime.setText(formatDateTime);
        not.setDateAndTime(formatDateTime);

        MainActivity.notlar.add(not);
        MainActivity.itemList.add(new ExampleItem(baslik_str,icerik_str,formatDateTime));

    }

    public void cancel(){
        Intent i = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(i);
    }

}
