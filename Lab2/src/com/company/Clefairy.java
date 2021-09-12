package com.company;
import ru.ifmo.se.pokemon.*;

public class Clefairy extends Cleffa {

    public Clefairy (String name, int level){

        super(name, level);
        this.setStats(70, 45, 48, 60, 65, 35);
        this.setType(Type.FAIRY);
        this.setMove(new Sing(), new Psychic(), new Pound());

    }

}