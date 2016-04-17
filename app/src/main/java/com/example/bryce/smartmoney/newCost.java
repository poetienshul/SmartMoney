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

public class newCost extends AppCompatActivity {
    private ListView list;
    ArrayList<String> listItems;
    protected ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cost);
        listItems=((vars) getApplicationContext()).costlist;
        adapter=( ((vars) getApplicationContext()).costadapter);


        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Double> costs = ((vars) getApplicationContext()).allCosts;
                    EditText name = (EditText)findViewById(R.id.nametext);
                    EditText cost = (EditText)findViewById(R.id.costtext);
                    ToggleButton butt = (ToggleButton)findViewById(R.id.toggleButton);
                double num = Double.parseDouble(cost.getText().toString());
                    if(butt.getText().toString().equals("Month"))
                        num/=4;
                costs.add(num);
                listItems.add(name.getText().toString()+ "  $" + cost.getText().toString() + " / " + butt.getText().toString() );
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
