package com.kravchenko.classes;

public enum Place {
    TUPICHOK(Planet.MOON, "в ночлежке 'Тупичок'", 50),
    ROCKET(Planet.MOON,"в Рокете", 50);

    final private Planet planet;
    final private String place;
    final private int capacity;
    private StatusPlace statusPlace[];

    Place(Planet planet, String place, int capacity){
        this.planet = planet;
        this.place = place;
        this.capacity = capacity;
    }


    public Planet getPlanet(){
        return planet;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setStatusPlace(StatusPlace statusPlace, int i) {
        this.statusPlace[i] = statusPlace;
    }

    public String getStatusPlace(int i) {
        return this.getPlace() + " " + this.statusPlace[i];
    }

    public String getPlace(){
        return place;
    }

    public class Shelf{
        private int quantity;
        private int occupied;

        public void setQuantity(int quantity){
            this.quantity = quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setOccupied(int occupied){
            this.occupied = occupied;
        }

        public int getOccupied() {
            return occupied;
        }
    }



}
