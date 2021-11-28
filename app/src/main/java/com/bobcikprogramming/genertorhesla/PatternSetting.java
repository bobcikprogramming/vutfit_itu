package com.bobcikprogramming.genertorhesla;

/**
 * Nastavení parametrů pro generování hesla
 * Option:
 *  0 = písmena
 *  1 = čísla
 *  2 = znaky
 *
 * OptionSetting
 *  Pro písmena
 *  0 = Lichá písmena jsou velká
 *  1 = Sudá písmena jsou velká
 *  2 = První písmeno je velké
 *  3 = Všechna písmena malá
 *
 *  Pro čísla
 *  0 = Pořadí písmena v abecedě
 *  1 = Pořadí písmena ve slově
 *  2 = Počet znaků písmena v morseovce
 *  3 = Počet teček písmena v morseovce
 *
 *  Pro znaky
 *  0 = Pvní znak písmena v morseovce
 *  1 = Poslední znak písmena v morseovce
 *  2 = Znak v morseovce na pozici písmena ve slově
 *  3 = Písmeno v morseovce
 */

public class PatternSetting {
    private int firstOption, secondOption, thirdOption;
    private int firstOptionSetting, secondOptionSetting, thirdOptionSetting;

    public PatternSetting(){}

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
