package com.kravchenko.classes;

import com.kravchenko.interfaces.Arithmetics_Operation;
import com.kravchenko.interfaces.Status;

public class Korotishki extends Heroes implements Status, Arithmetics_Operation {

    private StatusHeroes[] status;
    private boolean dressed;
    private Eyes eyes;

    {
        dressed = true;
    }

    public Korotishki() {};

    public Korotishki(String name) {
        super(name);
    }

    public Korotishki(String name, Place location) {
        super(name, location);
    }


    public void setStatus(StatusHeroes status, int i) {
        this.status[i] = status;
    }

    @Override
    public String getStatus(int i) {
        return this.getName() + " " + this.status[i].getStatus();
    }

    @Override
    public int multy(Operation m, int x, int y) {
        this.setMove(Moves.MULTIPLIES);
        System.out.println(this.getMove());
        return (x * y);
    }

    @Override
    public int div(Operation d, int x, int y) {
        this.setMove(Moves.DIV);
        System.out.println(this.getMove());
        return (x / y);
    }

    @Override
    public int mod(Operation m, int x, int y) {
        this.setMove(Moves.MOD);
        System.out.println(this.getMove());
        return (x % y);
    }

    public void setDressed(boolean b){
        this.dressed = b;
    }

    public boolean isDressed(){
        return dressed;
    }



}


