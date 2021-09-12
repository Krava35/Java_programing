package com.kravchenko.classes;

public enum Planet {
    EARTH("на земле"),
    MOON("на луне");

    final private String planet;

    Planet(String planet){
        this.planet = planet;
    }

    public String getPlanet(){
        return planet;
    }
}
