package com.company;
import ru.ifmo.se.pokemon.*;

public class Clefable extends Clefairy {

    public Clefable (String name, int level){

        super(name, level);
        this.setStats(95, 70, 73, 95, 90, 60);
        this.setType(Type.FAIRY);
        this.setMove(new Sing(), new Psychic(), new Pound(), new Facade());

    }

}