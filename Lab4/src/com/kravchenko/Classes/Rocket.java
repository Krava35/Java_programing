package com.kravchenko.Classes;

public class Rocket extends Place {
    private static Rocket instance;

    Rocket(){
        super(Planet.MOON, "Рокета", 50);
    }

    public static synchronized Rocket getInstance() {
        if (instance == null){
            instance = new Rocket();
        }
        return instance;

    }
}
