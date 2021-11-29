package com.bobcikprogramming.genertorhesla.controllers;

import android.content.Context;

import org.json.*;
import java.io.IOException;
import java.io.InputStream;

public class PasswordGenerator {

    private String phrase;
    private PatternSetting patternSetting;
    private Context context;
    private JSONArray jsonArray = null;
    private static final int PATTERNLENGTH = 3;

    public PasswordGenerator(String phrase, PatternSetting patternSetting, Context context){
        this.phrase = phrase.toLowerCase();
        this.patternSetting = patternSetting;
        this.context = context;
    }

    /**
     * Metoda upraví obdržené slovo/frázi. Nastaví jej na malé písmena, redukuje diaktiriku a odstraní výskyt všech přebytečných znaků (všechny kromě malých písmen).
     * @return false v případě neplatného slova/fráze (nevyskytuje žádné písmeno). True v případě, že byly přebytečné znaky odstraněny a string obsahuje písmena ke zpracování na heslo.
     */
    public boolean editPhrase(){
        phrase = phrase.toLowerCase();
        phrase = phrase.replaceAll("[á]", "a");
        phrase = phrase.replaceAll("[č]", "c");
        phrase = phrase.replaceAll("[ď]", "d");
        phrase = phrase.replaceAll("[ěé]", "e");
        phrase = phrase.replaceAll("[í]", "i");
        phrase = phrase.replaceAll("[ň]", "n");
        phrase = phrase.replaceAll("[ó]", "o");
        phrase = phrase.replaceAll("[ř]", "r");
        phrase = phrase.replaceAll("[š]", "s");
        phrase = phrase.replaceAll("[ť]", "t");
        phrase = phrase.replaceAll("[ůú]", "u");
        phrase = phrase.replaceAll("[ý]", "y");
        phrase = phrase.replaceAll("[ž]", "z");
        phrase = phrase.replaceAll("[^a-z]", "");

        if(phrase.isEmpty()){
            return false;
        }
        return true;
    }

    /***
     * Metoda k vygenerování hesla dle daných parametrů.
     * @return vygenerované heslo.
     */
    public String genereta(){
        initJsonArray();

        String password = "";
        for (int i = 0; i < phrase.length(); i++){
            switch(i % PATTERNLENGTH){
                case 0:
                    password += getStringBySelectedOption(patternSetting.getFirstOption(), patternSetting.getFirstOptionSetting(), i);
                    break;
                case 1:
                    password += getStringBySelectedOption(patternSetting.getSecondOption(), patternSetting.getSecondOptionSetting(), i);
                    break;
                case 2:
                    password += getStringBySelectedOption(patternSetting.getThirdOption(), patternSetting.getThirdOptionSetting(), i);
                    break;
            }
        }

        return password;
    }

    /***
     * Metoda získá odpovídající písmeno/číslo/znak dle daných parametrů pro písmeno dané pozice.
     * @param option udává, zdali se jedná o písmeno, číslo nebo znak
     * @param optinSetting udává nastavení option (velikost písma, hodnota čísla, typ znaku)
     * @param position pozice písmena, jenž je zpracováváno
     * @return zpracované písmeno
     */
    private String getStringBySelectedOption(int option, int optinSetting, int position){
        String result = "";
        String searchingChar = String.valueOf(phrase.charAt(position));

        switch(option){
            /** Jedná-li se o písmena */
            case 0:
                switch(optinSetting){
                    /** Velká lichá písmena */
                    case 0:
                        if(isEven(position)){
                            result = String.valueOf(phrase.charAt(position));
                        }else{
                            result = String.valueOf(phrase.charAt(position)).toUpperCase();
                        }
                        break;
                    /** Velká sudá písmena */
                    case 1:
                        if(isEven(position)){
                            result = String.valueOf(phrase.charAt(position)).toUpperCase();
                        }else{
                            result = String.valueOf(phrase.charAt(position));
                        }
                        break;
                    /** Velké písmeno v první trojici */
                    case 2:
                        if(position < PATTERNLENGTH){
                            result = String.valueOf(phrase.charAt(position)).toUpperCase();
                        }else{
                            result = String.valueOf(phrase.charAt(position));
                        }
                        break;
                    /** malá písmena */
                    case 3:
                        result = String.valueOf(phrase.charAt(position));
                        break;
                }
                break;
            /** Jedná-li se o čísla */
            case 1:
                int alphabetPosition = 0;
                String morse = "";
                int numberOfPoints = 0;

                for(int i = 0; i < jsonArray.length(); i++){
                    try {
                        if(jsonArray.getJSONObject(i).getString("char").equals(searchingChar)){
                            alphabetPosition = jsonArray.getJSONObject(i).getInt("position");
                            morse = jsonArray.getJSONObject(i).getString("morse");
                            numberOfPoints = jsonArray.getJSONObject(i).getInt("numberOfPoints");
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                switch(optinSetting){
                    /** Pořadí písmena v abecedě */
                    case 0:
                        result = String.valueOf(alphabetPosition);
                        break;
                    /** Pořadí písmena ve slově/frázi */
                    case 1:
                        result = String.valueOf(position+1);
                        break;
                    /** Počet znaků v morseovce daného písmena */
                    case 2:
                        result = String.valueOf(morse.length());
                        break;
                    /** Počet teček v morseovce daného písmena */
                    case 3:
                        result = String.valueOf(numberOfPoints);
                        break;
                }
                break;
            /** Jedná-li se o znaky */
            case 2:
                String morseSymbols = "";

                for(int i = 0; i < jsonArray.length(); i++){
                    try {
                        if(jsonArray.getJSONObject(i).getString("char").equals(searchingChar)){
                            morseSymbols = jsonArray.getJSONObject(i).getString("morse");
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                switch(optinSetting){
                    /** První znak v morsovce písmena */
                    case 0:
                        result = String.valueOf(morseSymbols.charAt(0));
                        break;
                    /** Poslední znak v morsovce písmena */
                    case 1:
                        result = String.valueOf(morseSymbols.charAt(morseSymbols.length()-1));
                        break;
                    /** Znak v morsovce písmena na pořadí dle výskytu písmena ve slově/frázi */
                    case 2:
                        result = String.valueOf(morseSymbols.charAt(position % morseSymbols.length()));
                        break;
                    /** Písmeno v morseovce */
                    case 3:
                        result = morseSymbols;
                        break;
                }
                break;
        }

        return result;
    }

    /***
     * Metoda pro zjištění, zdali se jedná o sudý či lichý výskyt.
     * @param position pozice písmena
     * @return true, jedná-li se o sudý výskyt. False v případě lichého.
     */
    private boolean isEven(int position){
        return ((position / PATTERNLENGTH)+1) % 2 == 0;
    }

    /**
     * Metoda pro inicializování jsonArray ze .json souboru.
     */
    private void initJsonArray(){
        try {
            jsonArray = new JSONArray(jsonDataFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * Metoda pro získání obsahu souboru .json.
     * @return obsah souboru .json.
     */
    private String jsonDataFromAsset() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("char.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
