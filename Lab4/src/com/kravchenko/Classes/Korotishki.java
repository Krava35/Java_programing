package com.kravchenko.Classes;

import com.kravchenko.Interfaces.Nightmare;

import java.util.ArrayList;

public class Korotishki extends Heroes {
    private boolean nightmare;
    private Advice advice;

    public Korotishki(){
    }

    public Korotishki(String name){
        super(name);
    }

    public Korotishki(String name, Tupichok place){
        super(name, place);
    }

    public Korotishki(String name, Tupichok place, String status){
        super(name, place, status);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void giveAdvice(String information, Korotishki korotishka) {
        Advice advice = new Advice(information, korotishka);
        korotishka.setAdvice(advice);
    }

    public boolean getAdvice(){
        if (advice != null) {
            return true;
        }
        else return false;
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " спит");

    }
}
