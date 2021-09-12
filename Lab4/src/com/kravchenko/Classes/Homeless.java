package com.kravchenko.Classes;

import com.kravchenko.Interfaces.Nightmare;

import java.util.ArrayList;

public class Homeless extends Korotishki {
    private int TrumpsQuantity;
    private ArrayList<Illness> illnesses = new ArrayList<Illness>() ;


    public void getIllnesses() {
        System.out.println();
        for (Illness illness : illnesses){
            System.out.println(illness.getName() + " ");
        }
    }

    public void addIllnesses(Illness illness) {
        this.illnesses.add(illness);
    }

    @Override
    public void sleep() {
        Nightmare nightmare = new Nightmare() {
            @Override
            public void tormented() {
                if (illnesses.size() >= 2){
                    System.out.println("Бездомных коротышек во сне терзают кошмары");
                }
                else{
                    System.out.println("Бездомные коротышки спят без кошмаров");
                }
            }
        };
        nightmare.tormented();

    }

    public int getTrumpsQuantity() {
        return TrumpsQuantity;
    }

    public void setTrumpsQuantity(int trumpsQuantity) {
        TrumpsQuantity = trumpsQuantity;
    }



    public void moan(){
        System.out.println("Бездомные коротышки стонят");
    }

    public void moo(){
        System.out.println("Бездомные коротышки мычят");
    }

    public void scream(){
        System.out.println("Бездомные коротышки вскрикивают");
    }
}
