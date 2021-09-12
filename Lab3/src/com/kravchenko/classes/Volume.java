package com.kravchenko.classes;

public enum Volume {
    SKINNY("тощий/тощая"),
    LEAN("худой/худая"),
    NORMALLY("нормальный/нормальная"),
    PLUMP("тостый/толстая"),
    FAT("жирный/жирная");

    final private String volume;

    Volume(String volume){
        this.volume = volume;
    }

    public String getVolume(){
        return volume;
    }

}
