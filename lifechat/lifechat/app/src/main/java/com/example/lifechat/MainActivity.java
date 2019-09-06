package com.example.lifechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lifechat.analysis.analy_main;
import com.example.lifechat.chatbot.chatbot_main;
import com.example.lifechat.datainput.datainput_main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button inputdatabt_main=(Button)findViewById(R.id.inputdata_main);
        inputdatabt_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),datainput_main.class);
                startActivity(intent);
            }
        });
        Button analybt_main=(Button)findViewById(R.id.analy_main);
        analybt_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),analy_main.class);
                startActivity(intent);
            }
        });
        Button chatbotbt_main=(Button)findViewById(R.id.chatbot_main);
        chatbotbt_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),chatbot_main.class);
                startActivity(intent);
            }
        });
    }
}
