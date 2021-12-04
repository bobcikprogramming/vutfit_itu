package com.bobcikprogramming.genertorhesla.controllers;

/**
 * Soubor:      Convertor
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Convertor {

    public Convertor(){}

    /**
     * Pomocná metoda pro převod hodnoty px na dp
     * @param px hodnota px
     * @param context View context
     * @return hodnotu dp
     *
     * Metoda byla inspirována z:
     * Zdroj:   Stack Overflow
     * Dotaz:   https://stackoverflow.com/q/20761611
     * Odpověď: https://stackoverflow.com/a/20761703
     * Autor:   Shayan Pourvatan
     * Autor:   https://stackoverflow.com/users/2580975/shayan-pourvatan
     * Datum:   24. prosince 2013
     */
    public int getDp(int px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int dp = px / (metrics.densityDpi / 160);
        return dp;
    }
}
