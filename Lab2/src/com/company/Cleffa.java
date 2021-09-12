package com.company;
import ru.ifmo.se.pokemon.*;

public class Cleffa extends Pokemon {

    public Cleffa (String name, int level){

        super(name, level);
        this.setStats(50, 25, 28, 45, 55, 15);
        this.setType(Type.FAIRY);
        this.setMove(new Sing(), new Psychic());

    }

}