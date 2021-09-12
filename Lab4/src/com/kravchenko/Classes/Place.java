package com.kravchenko.Classes;

public abstract class Place{
    final private Planet planet;
    final private String place;
    final private int MaxCapacity;
    private String status;
    private static Place instance;


    Place(Planet planet, String place, int MaxCapacity){
        this.planet = planet;
        this.place = place;
        this.MaxCapacity = MaxCapacity;
    }

    public Planet getPlanet() {
        return planet;
    }

    public int getMaxCapacity() {
        return MaxCapacity;
    }

    public String getPlace() {
        return place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static synchronized Place getInstance() {
        if (instance == null){
            instance = new Tupichok();
        }
        return instance;

    }
}
