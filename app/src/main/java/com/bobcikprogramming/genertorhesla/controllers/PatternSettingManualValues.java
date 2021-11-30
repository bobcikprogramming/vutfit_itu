package com.bobcikprogramming.genertorhesla.controllers;

import java.io.Serializable;

public class PatternSettingManualValues implements Serializable {
    private String firstOption, secondOption, thirdOption;
    private String firstOptionSetting, secondOptionSetting, thirdOptionSetting;

    public PatternSettingManualValues(){}

    public String getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(String firstOption) {
        this.firstOption = firstOption;
    }

    public String getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(String secondOption) {
        this.secondOption = secondOption;
    }

    public String getThirdOption() {
        return thirdOption;
    }

    public void setThirdOption(String thirdOption) {
        this.thirdOption = thirdOption;
    }

    public String getFirstOptionSetting() {
        return firstOptionSetting;
    }

    public void setFirstOptionSetting(String firstOptionSetting) {
        this.firstOptionSetting = firstOptionSetting;
    }

    public String getSecondOptionSetting() {
        return secondOptionSetting;
    }

    public void setSecondOptionSetting(String secondOptionSetting) {
        this.secondOptionSetting = secondOptionSetting;
    }

    public String getThirdOptionSetting() {
        return thirdOptionSetting;
    }

    public void setThirdOptionSetting(String thirdOptionSetting) {
        this.thirdOptionSetting = thirdOptionSetting;
    }
}
