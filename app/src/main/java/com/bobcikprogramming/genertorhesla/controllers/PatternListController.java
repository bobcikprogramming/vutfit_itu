package com.bobcikprogramming.genertorhesla.controllers;

/**
 * Soubor:      PatternListController
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import com.bobcikprogramming.genertorhesla.model.PatternEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Pomocná třída pro seznam vzorů.
 */
public class PatternListController {

    private List<PatternEntity> dataFromDatabase;
    private List<PatternEntity> patternListToShow;
    private PatternEntity restoreDataOfPattern;
    private boolean newManual;
    private int positionInDataFromDb;

    public PatternListController(){
        patternListToShow = new ArrayList<>();
        restoreDataOfPattern = null;
    }

    public List<PatternEntity> getDataFromDatabase() {
        return dataFromDatabase;
    }

    public void setDataFromDatabase(List<PatternEntity> dataFromDatabase) {
        this.dataFromDatabase = dataFromDatabase;
    }

    public List<PatternEntity> getPatternListToShow() {
        return patternListToShow;
    }

    public void setPatternListToShow(List<PatternEntity> patternListToShow) {
        this.patternListToShow = patternListToShow;
    }

    public boolean isNewManual() {
        return newManual;
    }

    public void setNewManual(boolean newManual) {
        this.newManual = newManual;
    }

    /**
     * Metoda pro vytvoření kopie listu, obsahujícího vzory z databáze, do listu, který se bude zobrazovat
     * a nad kterým se bude provádět filtrování.
     */
    public void copyDataFromDbListToPattrnList(){
        this.patternListToShow.clear();
        this.patternListToShow.addAll(dataFromDatabase);
    }

    public PatternEntity getRestoreDataOfPattern() {
        return restoreDataOfPattern;
    }

    /**
     * Metoda vykonávající akci při swipnutí položky recycler view.
     * Dojde k smazání dané položky z listu a z databáze.
     * Smazaná položka je uložena do proměnné restoreDataOfPattern pro případ navrácení změny.
     * @param position pozice položky v listu
     * @param generate reference na třídu GeneratePassword
     */
    public void removeOnSwipe(int position, GeneratePassword generate){
        this.restoreDataOfPattern = patternListToShow.remove(position);
        this.positionInDataFromDb = dataFromDatabase.indexOf(restoreDataOfPattern);
        this.dataFromDatabase.remove(positionInDataFromDb);
        generate.removePatternById(restoreDataOfPattern.uidPattern);
    }

    /**
     * Metoda pro obnovení smazané položky.
     * Vzor je navrácen do listů i do databáze.
     * @param generate reference na třídu GeneratePassword
     */
    public void restoreData(GeneratePassword generate){
        patternListToShow.add(restoreDataOfPattern);
        dataFromDatabase.add(positionInDataFromDb, restoreDataOfPattern);
        generate.restorePatternInDatabase(restoreDataOfPattern);
    }
}
