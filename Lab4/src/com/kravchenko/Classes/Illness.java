package com.kravchenko.Classes;

public enum Illness {
    COLD("простуда"),
    COUGHT("кашель");

    private String name;

    Illness(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
