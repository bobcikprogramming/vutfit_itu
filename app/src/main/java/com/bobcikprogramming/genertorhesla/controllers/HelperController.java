package com.bobcikprogramming.genertorhesla.controllers;

/**
 * Soubor:      HelperController
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import android.content.Context;

import com.bobcikprogramming.genertorhesla.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pomocná třída pro práci s View pro nápovědu.
 */

public class HelperController {

    private ArrayList<HelperHolder> help;
    private boolean logged;
    private Context context;

    public HelperController(Context context){
        this.help = new ArrayList<>();
        this.context = context;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * Načtení textového pole s nápovědou pro nepřihlášeného uživatele.
     * @return pole pomocné třídy HelperHolder obsahující nápovědu
     */
    public ArrayList<HelperHolder> getUnloggedHelp(){
        List<String> helpHeadlineList = Arrays.asList(context.getResources().getStringArray(R.array.mainHelpHeadline));
        List<String> helpTextList = Arrays.asList(context.getResources().getStringArray(R.array.mainHelpText));

        help = new ArrayList<>();

        for(int i = 0; i < helpHeadlineList.size(); i++){
            HelperHolder helperHolder = new HelperHolder();
            helperHolder.setHeadline(helpHeadlineList.get(i));
            helperHolder.setText(helpTextList.get(i));
            help.add(helperHolder);
        }

        return help;
    }

    /**
     * Načtení textového pole s nápovědou pro přihlášeného uživatele.
     * @return pole pomocné třídy HelperHolder obsahující nápovědu
     */
    public ArrayList<HelperHolder> getLoggedHelp(){
        List<String> helpHeadlineList = Arrays.asList(context.getResources().getStringArray(R.array.loggedHelpHeadline));
        List<String> helpTextList = Arrays.asList(context.getResources().getStringArray(R.array.loggedHelpText));

        help = new ArrayList<>();

        for(int i = 0; i < helpHeadlineList.size(); i++){
            HelperHolder helperHolder = new HelperHolder();
            helperHolder.setHeadline(helpHeadlineList.get(i));
            helperHolder.setText(helpTextList.get(i));
            help.add(helperHolder);
        }

        return help;
    }

    /**
     * Pomocná metoda pro získání nápovědy (informací) pro jednotlívé možnosti u vlastního vzoru.
     * @param option číslo možnosti
     * @param position číslo nastavení možnosti
     * @return pomocná třída HelperHolder nesoucí hodnotu nápovědy (informace)
     */
    public HelperHolder getInformation(int option, int position){
        HelperHolder information = new HelperHolder();

        if(option == 0){
            information.setHeadline(Arrays.asList(context.getResources().getStringArray(R.array.letterHelpHeadline)).get(position));
            information.setText(Arrays.asList(context.getResources().getStringArray(R.array.letterHelpText)).get(position));
        }else if(option == 1){
            information.setHeadline(Arrays.asList(context.getResources().getStringArray(R.array.numberHelpHeadline)).get(position));
            information.setText(Arrays.asList(context.getResources().getStringArray(R.array.numberHelpText)).get(position));
        }else{
            information.setHeadline(Arrays.asList(context.getResources().getStringArray(R.array.symbolHelpHeadline)).get(position));
            information.setText(Arrays.asList(context.getResources().getStringArray(R.array.symbolHelpText)).get(position));
        }

        return information;
    }
}
