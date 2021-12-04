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

    public void copyDataFromDbListToPattrnList(){
        this.patternListToShow.clear();
        this.patternListToShow.addAll(dataFromDatabase);
    }

    public PatternEntity getRestoreDataOfPattern() {
        return restoreDataOfPattern;
    }

    public void removeOnSwipe(int position, GeneratePassword generate){
        this.restoreDataOfPattern = patternListToShow.remove(position);
        this.positionInDataFromDb = dataFromDatabase.indexOf(restoreDataOfPattern);
        this.dataFromDatabase.remove(positionInDataFromDb);
        generate.removePatternById(restoreDataOfPattern.uidPattern);
    }

    public void restoreData(GeneratePassword generate){
        patternListToShow.add(restoreDataOfPattern);
        dataFromDatabase.add(positionInDataFromDb, restoreDataOfPattern);
        generate.restorePatternInDatabase(restoreDataOfPattern);
    }
}
