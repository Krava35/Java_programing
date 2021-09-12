package com.kravchenko.Classes;

import java.util.Random;

public class Tupichok extends Place {
    private static Tupichok instance;
    private Shelf[] shelfs;

    Tupichok(){
        super(Planet.MOON, "Тупичок", 50);
        this.shelfs = new Shelf[getMaxCapacity()];
        this.mixed();
    }

    public static synchronized Tupichok getInstance() {
        if (instance == null){
            instance = new Tupichok();
        }
        return instance;

    }


    public void mixed(){
        Random random = new Random();
        for (int i = 0; i < shelfs.length; i++){
            shelfs[i] = new Shelf();
            shelfs[i].setTaken(random.nextBoolean());
        }
    }


    public boolean findFreeShelf(){
        for (Shelf shelf : shelfs){
            if (shelf.isTaken() == false){
                shelf.setTaken(true);
                return true;
            }
        }
        return false;
    }


    private class Shelf{
        private boolean taken;

        public boolean isTaken() {
            return taken;
        }

        public void setTaken(boolean taken) {
            this.taken = taken;
        }

    }
}
