package com.example.bryce.smartmoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class newSave extends AppCompatActivity {
    private ListView list;
    ArrayList<String> listItems;
    protected ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_save);

        listItems=((vars) getApplicationContext()).savelist;
        adapter=( ((vars) getApplicationContext()).saveadapter);


        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Double> saves = ((vars) getApplicationContext()).saves;
                EditText name = (EditText)findViewById(R.id.nametext);
                EditText cost = (EditText)findViewById(R.id.costtext);
                ToggleButton butt = (ToggleButton)findViewById(R.id.toggleButton);
                if(butt.getText().equals("%")) {
                    listItems.add(name.getText().toString() + "  " + cost.getText().toString() + butt.getText().toString());
                    saves.add(( ((vars) getApplicationContext()).income) * (Double.parseDouble(cost.getText().toString())/100));
                }
                else {
                    listItems.add(name.getText().toString() + "  " + butt.getText().toString() + cost.getText().toString());
                    saves.add(Double.parseDouble(cost.getText().toString()));
                }
                adapter.notifyDataSetChanged();
                finish();
            }
        });

        Button can = (Button) findViewById(R.id.can);
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
