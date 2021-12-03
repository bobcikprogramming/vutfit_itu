package com.bobcikprogramming.genertorhesla.controllers;

public class ManualPattern {

    private int firstOption, secondOption, thirdOption;
    private int firstOptionSetting, secondOptionSetting, thirdOptionSetting;

    public ManualPattern(){
        this.firstOption = 0;
        this.secondOption = 0;
        this.thirdOption = 0;

        this.firstOptionSetting = -1;
        this.secondOptionSetting = -1;
        this.thirdOptionSetting = -1;
    }

    public int getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(int firstOption) {
        this.firstOption = firstOption;
    }

    public int getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(int secondOption) {
        this.secondOption = secondOption;
    }

    public int getThirdOption() {
        return thirdOption;
    }

    public void setThirdOption(int thirdOption) {
        this.thirdOption = thirdOption;
    }

    public int getFirstOptionSetting() {
        return firstOptionSetting;
    }

    public void setFirstOptionSetting(int firstOptionSetting) {
        this.firstOptionSetting = firstOptionSetting;
    }

    public int getSecondOptionSetting() {
        return secondOptionSetting;
    }

    public void setSecondOptionSetting(int secondOptionSetting) {
        this.secondOptionSetting = secondOptionSetting;
    }

    public int getThirdOptionSetting() {
        return thirdOptionSetting;
    }

    public void setThirdOptionSetting(int thirdOptionSetting) {
        this.thirdOptionSetting = thirdOptionSetting;
    }
}
