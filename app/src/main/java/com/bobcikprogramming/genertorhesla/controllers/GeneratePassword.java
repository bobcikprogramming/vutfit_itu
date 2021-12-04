package com.bobcikprogramming.genertorhesla.controllers;

/**
 * Soubor:      GeneratePassword
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import android.content.Context;

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.model.AppDatabase;
import com.bobcikprogramming.genertorhesla.model.PatternEntity;

import java.util.ArrayList;
import java.util.List;

/** Třída oblusující generování hesla. Příjmá vstupy obdržené od View, zpracováváje a volá požadované funkce společného backendu.*/
public class GeneratePassword{

    private boolean letter, capLetter, number, symbol;
    private PatternSetting patternSetting;
    private PatternGenerator pattern;
    private Context context;
    private AppDatabase db;

    public GeneratePassword(Context context){
        this.patternSetting = null;
        this.pattern = new PatternGenerator();
        this.context = context;
        this.db = AppDatabase.getDbInstance(context);
    }

    public void setterForRandomPatternValues(boolean letter, boolean capLetter, boolean number, boolean symbol){
        this.letter = letter;
        this.capLetter = capLetter;
        this.number = number;
        this.symbol = symbol;
    }

    public void createRandomPatternSetting(){
        patternSetting = pattern.generatePattern(letter, capLetter, number, symbol);
    }

    /**
     * Metoda na základě vytvořeného vzoru vrátí ukázku vzoru na slově Motocykl.
     * @return textový řetězec s ukázkou vzoru, v případě že neexistuje vzor vrací 'Vzor nenastaven'
     */
    public String getPatternExample(){
        String pattern;
        if(patternSetting == null){
            pattern = context.getString(R.string.emptyPattern);
        }else {
            PasswordGenerator generatorPattern = new PasswordGenerator(context.getString(R.string.examplePattern), patternSetting, context);
            pattern = generatorPattern.genereta();
        }

        return pattern;
    }

    /**
     * Metoda na základě vytvořeného vzoru vrátí heslo vytvořeného z textového řetězce phrase.
     * @param phrase textový řetězec obsahující slovo, frázi nebo větu pro vygenerování hesla
     * @return vygenerované heslo, nebo prázdný řětezec, je-li phrase prázdný řetězec nebo neobsahuje-li žádný podporovaný znak
     */
    public String getPassword(String phrase){
        String password;
        if(patternSetting == null){
            password = "";
        }else {

            PasswordGenerator generatorPassword = new PasswordGenerator(phrase, patternSetting, context);
            if (!generatorPassword.editPhrase()) {
                password = "";
            } else {
                password = generatorPassword.genereta();
            }
        }

        return password;
    }

    /**
     * Metoda na základě vytvořeného vzoru vrátí ukázku vzoru na slově Motocykl (u vlastního vzoru).
     * @return textový řetězec s ukázkou vzoru, v případě že neexistuje vzor vrací 'Vzor nenastaven'
     */
    public String showManualPattern(int firstOption, int secondOption, int thirdOption, int firstOptionSetting, int secondOptionSetting, int thirdOptionSetting){
        String pattern;
        if(firstOptionSetting != -1 && secondOptionSetting != -1 && thirdOptionSetting != -1 ){
            patternSetting = getPatternSetting(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting);
            pattern = getPatternExample();
        }else{
            pattern = context.getString(R.string.emptyPattern);
        }
        return pattern;
    }

    /**
     * Metoda pro získání vzoru dle zadaných hodnot obdržených na vstupu.
     * @return vytvořený vzor
     */
    private PatternSetting getPatternSetting(int firstOption, int secondOption, int thirdOption, int firstOptionSetting, int secondOptionSetting, int thirdOptionSetting){
        if(firstOptionSetting != -1 && secondOptionSetting != -1 && thirdOptionSetting != -1 ){
            return pattern.manualSetting(firstOption, firstOptionSetting, secondOption, secondOptionSetting, thirdOption, thirdOptionSetting);
        }else{
            return null;
        }
    }

    /**
     * Metoda pro získání popisu u vlastního vzoru.
     * @return pomocnou třídu obsahující popis vytvořeného vlastního vzoru
     */
    public PatternSettingManualValues getManualSettingValues(){
        PatternSettingManualValues manualSettingValues = new PatternSettingManualValues();
        if(patternSetting.getFirstOption() == 0){
            manualSettingValues.setFirstOption(context.getString(R.string.letter));
            manualSettingValues.setFirstOptionSetting(getManualOptionSetting(patternSetting.getFirstOption(), patternSetting.getFirstOptionSetting()));
        }else if(patternSetting.getFirstOption() == 1){
            manualSettingValues.setFirstOption(context.getString(R.string.number));
            manualSettingValues.setFirstOptionSetting(getManualOptionSetting(patternSetting.getFirstOption(), patternSetting.getFirstOptionSetting()));
        }else{
            manualSettingValues.setFirstOption(context.getString(R.string.symbol));
            manualSettingValues.setFirstOptionSetting(getManualOptionSetting(patternSetting.getFirstOption(), patternSetting.getFirstOptionSetting()));
        }

        if(patternSetting.getSecondOption() == 0){
            manualSettingValues.setSecondOption(context.getString(R.string.letter));
            manualSettingValues.setSecondOptionSetting(getManualOptionSetting(patternSetting.getSecondOption(), patternSetting.getSecondOptionSetting()));
        }else if(patternSetting.getSecondOption() == 1){
            manualSettingValues.setSecondOption(context.getString(R.string.number));
            manualSettingValues.setSecondOptionSetting(getManualOptionSetting(patternSetting.getSecondOption(), patternSetting.getSecondOptionSetting()));
        }else{
            manualSettingValues.setSecondOption(context.getString(R.string.symbol));
            manualSettingValues.setSecondOptionSetting(getManualOptionSetting(patternSetting.getSecondOption(), patternSetting.getSecondOptionSetting()));
        }

        if(patternSetting.getThirdOption() == 0){
            manualSettingValues.setThirdOption(context.getString(R.string.letter));
            manualSettingValues.setThirdOptionSetting(getManualOptionSetting(patternSetting.getThirdOption(), patternSetting.getThirdOptionSetting()));
        }else if(patternSetting.getThirdOption() == 1){
            manualSettingValues.setThirdOption(context.getString(R.string.number));
            manualSettingValues.setThirdOptionSetting(getManualOptionSetting(patternSetting.getThirdOption(), patternSetting.getThirdOptionSetting()));
        }else{
            manualSettingValues.setThirdOption(context.getString(R.string.symbol));
            manualSettingValues.setThirdOptionSetting(getManualOptionSetting(patternSetting.getThirdOption(), patternSetting.getThirdOptionSetting()));
        }

        return manualSettingValues;
    }

    /**
     * Pomocná metoda pro získání popisu vlastního vzoru.
     * @param option číselná hodnota reprezentující zvolenou možnost
     * @param setting číselná hodnota reprezentující zvolené nastavení možnosti
     * @return textový řetězec odpovídajícího popisu dle zvolených číselných hodnot
     */
    public String getManualOptionSetting(int option, int setting){
        if(option == 0){
            if(setting == 0){
                return context.getString(R.string.letterFirst);
            }else if(setting == 1){
                return context.getString(R.string.letterSecond);
            }else if(setting == 2){
                return context.getString(R.string.letterThird);
            }else{
                return context.getString(R.string.letterFourth);
            }
        }else if(option == 1){
            if(setting == 0){
                return context.getString(R.string.numberFirst);
            }else if(setting == 1){
                return context.getString(R.string.numberSecond);
            }else if(setting == 2){
                return context.getString(R.string.numberThird);
            }else{
                return context.getString(R.string.numberFourth);
            }
        }else{
            if(setting == 0){
                return context.getString(R.string.symbolFirst);
            }else if(setting == 1){
                return context.getString(R.string.symbolSecond);
            }else if(setting == 2){
                return context.getString(R.string.symbolThird);
            }else{
                return context.getString(R.string.symbolFourth);
            }
        }
    }

    public PatternSetting getPatternSetting() {
        return patternSetting;
    }

    public void setPatternSetting(PatternSetting patternSetting) {
        this.patternSetting = patternSetting;
    }

    /**
     * Metoda sloužící k uložení vzoru do databáze
     * @param name název vzoru
     */
    public void savePatternToDatabase(String name){
        if(name.isEmpty()){
            name = "Nepojmenováno";
        }

        PatternEntity pattern = new PatternEntity();

        pattern.name = name;
        pattern.example = getPassword("Motocykl");
        pattern.firstOption = patternSetting.getFirstOption();
        pattern.firstOptionSetting = patternSetting.getFirstOptionSetting();
        pattern.secondOption = patternSetting.getSecondOption();
        pattern.secondOptionSetting = patternSetting.getSecondOptionSetting();
        pattern.thirdOption = patternSetting.getThirdOption();
        pattern.thirdOptionSetting = patternSetting.getThirdOptionSetting();
        db.databaseDao().insertPattern(pattern);
    }

    /**
     * Metoda pro získání vzorů z databáze.
     * @return list vzorů
     */
    public List<PatternEntity> loadPatternListFromDatabase(){
        return db.databaseDao().getPattern();
    }

    /**
     * Metoda pro získání vzoru z databáze podle id.
     * @param id id vzoru
     * @return získaný vzor nebo NULL, neobsahuje-li databáze záznam s hledaným id
     */
    public PatternSetting getPatternSettingFromDatabaseById(String id){
        PatternEntity patternEntity = db.databaseDao().getPatternById(id);
        if(patternEntity == null){
            return null;
        }else{
            return getPatternSetting(patternEntity.firstOption, patternEntity.secondOption, patternEntity.thirdOption, patternEntity.firstOptionSetting, patternEntity.secondOptionSetting, patternEntity.thirdOptionSetting);
        }
    }

    /**
     * Metoda vrací list obsahující vzory, jejichž jméno obsahuje substring name
     * @param name hledaný výraz
     * @param array prohledávaný list
     * @return list obsahující název s hledanýám výzarem
     */
    public List <PatternEntity> searchForPatternByName(String name, List<PatternEntity> array){
        List<PatternEntity> result = new ArrayList<>();
        for (PatternEntity toShow : array) {
            if (toShow.name.toLowerCase().contains(name.toLowerCase())) {
                result.add(toShow);
            }
        }
        return result;
    }

    /**
     * Metoda pro smazání vzoru z databáze podle id.
     * @param id id vzoru
     */
    public void removePatternById(long id){
        db.databaseDao().deletePatternById(String.valueOf(id));
    }

    /**
     * Metoda pro navrácení vzoru do databáze.
     * @param pattern vzor
     */
    public void restorePatternInDatabase(PatternEntity pattern){
        db.databaseDao().insertPattern(pattern);
    }
}
