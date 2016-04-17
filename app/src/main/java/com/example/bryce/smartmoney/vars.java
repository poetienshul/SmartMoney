package com.example.bryce.smartmoney;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Bryce on 4/16/2016.
 */
public class vars extends Application
{
    public ArrayList<String> costlist=new ArrayList<String>();
    public  ArrayAdapter<String> costadapter ;

    public ArrayList<String> savelist=new ArrayList<String>();
    public  ArrayAdapter<String> saveadapter ;

    public String nickname;
    public double num;
    public double costs;

    public ArrayList<Double> allCosts = new ArrayList<Double>();
    public ArrayList<Double> saves = new ArrayList<Double>();

    public String one="";
    public String two="";
    public double income = 0;
}
