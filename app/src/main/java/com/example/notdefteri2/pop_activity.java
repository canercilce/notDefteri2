package com.example.notdefteri2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pop_activity extends AppCompatActivity implements View.OnClickListener {

    Button geri;
    static Button sil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_activity);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));

        geri= (Button) findViewById(R.id.button);
        sil= (Button) findViewById(R.id.button2);

       geri.setOnClickListener(this);
       sil.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button){
            finish();
        }
        else if(view.getId()==R.id.button2){
            finish();
            MainActivity.removeItem(MainActivity.baslik1,MainActivity.icerik1);

        }
    }
}