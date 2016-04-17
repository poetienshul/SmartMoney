package com.example.bryce.smartmoney;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class income extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        EditText one = (EditText)findViewById(R.id.editText2);
        EditText two = (EditText)findViewById(R.id.editText);
       one.setText((((vars) getApplicationContext()).one));
        two.setText((((vars) getApplicationContext()).two));



        Button can = (Button) findViewById(R.id.can);
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EditText one = (EditText)findViewById(R.id.editText2);
        EditText two = (EditText)findViewById(R.id.editText);
        (((vars)getApplicationContext()).one) = one.getText().toString();
        (((vars)getApplicationContext()).two) = two.getText().toString();
        (((vars)getApplicationContext()).income) = Double.parseDouble(one.getText().toString())/Double.parseDouble(two.getText().toString());
    }
}
