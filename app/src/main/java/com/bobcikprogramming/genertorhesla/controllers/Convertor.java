package com.bobcikprogramming.genertorhesla.controllers;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Convertor {

    public Convertor(){}

    // https://stackoverflow.com/a/20761703
    public int getDp(int px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int dp = px / (metrics.densityDpi / 160);
        return dp;
    }
}
