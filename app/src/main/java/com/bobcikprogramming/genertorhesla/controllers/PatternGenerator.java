package com.bobcikprogramming.genertorhesla.controllers;

import java.util.ArrayList;
import java.util.Random;

public class PatternGenerator {

    public PatternGenerator(){ }

    /**
     * Metoda slouží k inicializování pole s nastavením pro generování hesla.
     * @return Pole s nastavením pro generování hesla.
     */
    private ArrayList<Integer> setUpGenerating(boolean hasLetter, boolean hasNumber, boolean hasSymbol){
        ArrayList<Integer> patternSetting = new ArrayList<>();

        if(hasLetter){
            patternSetting.add(0);
        }
        if(hasNumber){
            patternSetting.add(1);
        }
        if(hasSymbol){
            patternSetting.add(2);
        }

        return patternSetting;
    }

    /***
     * Metoda slouží pro automatické vygenerování vzoru.
     * Na vstupu příjmá pouze 4 parametry. Tři udávají výskyt písmen/číslic/znaků v hesle a jeden udává povinný výskyt velkých písmen.
     * @param hasLetter heslo bude obsahovat písmena.
     * @param hasUpponLetter heslo bude obsahovat velká písmena.
     * @param hasNumber heslo bude obsahovat čísla.
     * @param hasSymbol heslo bude obsahovat znaky.
     * @return vygenerovaný náhodný vzor nebo null v případě, že nebyla zvolena ani jedna možnost výskytu.
     */
    public PatternSetting generatePattern(boolean hasLetter, boolean hasUpponLetter, boolean hasNumber, boolean hasSymbol){
        PatternSetting pattern = new PatternSetting();
        ArrayList<Integer> patternSetting = setUpGenerating(hasLetter, hasNumber, hasSymbol);

        if(patternSetting.isEmpty()){
            return null;
        }

        Random rand = new Random();
        for(int i = 0; i < 3; i++){
            int options = patternSetting.size();

            int option = patternSetting.get(rand.nextInt(options));

            if(options == (3-i)){
                patternSetting.remove(Integer.valueOf(option));
            }

            switch(i){
                case 0:
                    pattern.setFirstOption(option);
                    if(hasUpponLetter){
                        pattern.setFirstOptionSetting(rand.nextInt(3));
                    }else {
                        pattern.setFirstOptionSetting(rand.nextInt(4));
                    }
                    break;
                case 1:
                    pattern.setSecondOption(option);
                    pattern.setSecondOptionSetting(rand.nextInt(4));
                    break;
                case 2:
                    pattern.setThirdOption(option);
                    pattern.setThirdOptionSetting(rand.nextInt(4));
                    break;
            }

        }

        return pattern;
    }

    /***
     * Metoda slouží pro manuální nastavní vzoru.
     * Na vstupu příjmá informaci udávající, co se bude vyskytovat na dané pozici v trojici (zdali písmeno, číslo nebo znak) a jakou hodnotu budou mít odpovídající symboly.
     * @param firstOption jaký symbol bude na první pozici (písmeno/číslo/znak).
     * @param firstOptionSetting jakou hodnotu bude mít symbol na první pozici (viz. malá písmena, číslo dle pořadí v abecedě, atd.).
     * @param secondOption jaký symbol bude na druhé pozici.
     * @param secondOptionSetting jakou hodnotu bude mít symbol na druhé pozici.
     * @param thirdOption jaký symbol bude na třetí pozici.
     * @param thirdOptionSetting jakou hodnotu bude mít symbol na třetí pozici.
     * @return vytvořený vzor.
     */
    public PatternSetting manualSetting(int firstOption, int firstOptionSetting, int secondOption, int secondOptionSetting, int thirdOption, int thirdOptionSetting){
        PatternSetting pattern = new PatternSetting();

        pattern.setFirstOption(firstOption);
        pattern.setFirstOptionSetting(firstOptionSetting);

        pattern.setSecondOption(secondOption);
        pattern.setSecondOptionSetting(secondOptionSetting);

        pattern.setThirdOption(thirdOption);
        pattern.setThirdOptionSetting(thirdOptionSetting);

        return pattern;
    }


}
