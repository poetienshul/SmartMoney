package com.example.bryce.smartmoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reimaginebanking.api.java.NessieClient;
import com.reimaginebanking.api.java.NessieException;
import com.reimaginebanking.api.java.NessieResultsListener;
import com.reimaginebanking.api.java.models.Customer;
import com.reimaginebanking.api.java.models.Account;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("Example", "onCreate");
        getIntent().setAction("Already created");

        Button can = (Button) findViewById(R.id.can);
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onResume();
            }
        });

        //establishing connection
        final NessieClient nessieClient = NessieClient.getInstance();
        nessieClient.setAPIKey("3801f80ce11d48e9e13d6f8020303381");

        //nessieClient.getAccount("5712d00601c7065b0fceb533", new NessieResultsListener() {
        nessieClient.getAccount("571339ea8a710f8e123248e3", new NessieResultsListener() {
            @Override
            public void onSuccess(Object result, NessieException e) {
                ((vars)getApplicationContext()).nickname = ((Account) result).getNickname();
                TextView name = (TextView)findViewById(R.id.Title);
                name.setText("Hello " + ((vars)getApplicationContext()).nickname);
                ((vars)getApplicationContext()).num = ((Account) result).getBalance();
                DecimalFormat df = new DecimalFormat("###,###,##0.00");
                TextView t = (TextView)findViewById(R.id.cDisp);
                double mrow = ((vars) getApplicationContext()).num;
                t.setText("Current Balance: $" + df.format(mrow));
                ((vars)getApplicationContext()).costs = 0;
                for(Double a : (((vars) getApplicationContext()).allCosts))
                {
                    ((vars)getApplicationContext()).costs += a;
                }
                for(Double a : (((vars) getApplicationContext()).saves))
                {
                    ((vars)getApplicationContext()).costs += a;
                }
                TextView v = (TextView)findViewById(R.id.dispIncome);
                double temp = ((((vars) getApplicationContext()).income) - ((vars) getApplicationContext()).costs);
                v.setText("Disposable Income: $ " + df.format(temp));

                double bubble = 0;
                for(Double a : (((vars) getApplicationContext()).saves))
                {
                   bubble+= a;
                }
                TextView estimate = (TextView)findViewById(R.id.Estimate);
                estimate.setText("Estimated Balance: $ " + df.format((((vars) getApplicationContext()).num) + temp +bubble));


                //System.out.println(num - costs);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_save){
            startActivity(new Intent(this, Savings.class));
        } else if (id == R.id.nav_bill) {
            startActivity(new Intent(this, Costs.class));


        } else if (id == R.id.nav_inc) {

            startActivity(new Intent(this,income.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    @Override
     protected void  onResume()
    {
        Log.v("Example", "onResume");

        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Log.v("Example", "Force restart");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }
}
