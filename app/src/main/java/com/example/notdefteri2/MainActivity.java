package com.example.notdefteri2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notdefteri2.MainActivity2;
import com.example.notdefteri2.R;
import com.example.notdefteri2.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton button;
    RecyclerView recyclerView;
    static RecyclerAdapter myAdapter;
    CardView cardView;
    static Boolean cameFromCardview = false;
    TextView textView;
    ImageView imageView;
    static TextView baslik1;
    static TextView icerik1;
    static ArrayList<ExampleItem> itemList= new ArrayList<>();
    static ExampleItem item;
    static ArrayList<Not> notlar= new ArrayList<Not>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        button = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        textView = (TextView) findViewById(R.id.textView);
        cardView = (CardView) findViewById(R.id.cardView);
        imageView = (ImageView) findViewById(R.id.imageView);
        button.setOnClickListener(this);

        System.out.println(notlar.size());

        myAdapter = new RecyclerAdapter(this,itemList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(notlar.size()>0){
            textView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.imageView){
            ConstraintLayout constraintLayout = (ConstraintLayout) view.getParent();
            baslik1= (TextView) constraintLayout.getChildAt(0);
            icerik1= (TextView) constraintLayout.getChildAt(1);
            Intent intent = new Intent(MainActivity.this,pop_activity.class);
            startActivity(intent);

        }
        else {
            if(view.getId()==R.id.cardView){
                cameFromCardview = true;
                ConstraintLayout child = (ConstraintLayout) ((ViewGroup) view).getChildAt(0);
                TextView baslik_txt = (TextView) ((ViewGroup) child).getChildAt(0);
                TextView icerik_txt = (TextView) ((ViewGroup) child).getChildAt(1);
                item = getExampleItem(baslik_txt.getText().toString(),icerik_txt.getText().toString());

            }
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
    }

    static void removeItem(TextView baslik1,TextView icerik1) {
        for (int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).str1.equals(baslik1.getText()) && itemList.get(i).str2.equals(icerik1.getText())){
                itemList.remove(i);
                notlar.remove(i);
                myAdapter.notifyDataSetChanged();
            }
        }
    }
    public ExampleItem getExampleItem(String baslik, String icerik){
        for (int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).str1.equals(baslik) && itemList.get(i).str2.equals(icerik)){
                return itemList.get(i);
            }
        }
        return null;
    }
}