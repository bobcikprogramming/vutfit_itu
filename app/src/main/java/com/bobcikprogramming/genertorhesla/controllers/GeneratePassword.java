package com.bobcikprogramming.genertorhesla.controllers;

import android.content.Context;

import com.bobcikprogramming.genertorhesla.R;

public class GeneratePassword{

    private boolean letter, capLetter, number, symbol;
    private PatternSetting patternSetting;
    private PatternGenerator pattern;
    private Context context;

    public GeneratePassword(Context context){
        patternSetting = null;
        pattern = new PatternGenerator();
        this.context = context;
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

    public String showPattern(int firstOption, int secondOption, int thirdOption, int firstOptionSetting, int secondOptionSetting, int thirdOptionSetting){
        String pattern;
        if(firstOptionSetting != -1 && secondOptionSetting != -1 && thirdOptionSetting != -1 ){
            patternSetting = getPatternSetting(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting);
            if(patternSetting == null){
                pattern = context.getString(R.string.emptyPattern);
            }else {
                PasswordGenerator generatorPattern = new PasswordGenerator(context.getString(R.string.examplePattern), patternSetting, context);
                pattern = generatorPattern.genereta();
            }
        }else{
            pattern = context.getString(R.string.emptyPattern);
        }
        return pattern;
    }

    private PatternSetting getPatternSetting(int firstOption, int secondOption, int thirdOption, int firstOptionSetting, int secondOptionSetting, int thirdOptionSetting){
        if(firstOptionSetting != -1 && secondOptionSetting != -1 && thirdOptionSetting != -1 ){
            return pattern.manualSetting(firstOption, firstOptionSetting, secondOption, secondOptionSetting, thirdOption, thirdOptionSetting);
        }else{
            return null;
        }
    }

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
}
