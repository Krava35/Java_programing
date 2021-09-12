package com.company;
import ru.ifmo.se.pokemon.*;

   public class Doduo extends Pokemon {

       public Doduo (String name, int level){

           super(name, level);
           this.setStats(35, 85, 45, 35, 35, 75);
           this.setType(Type.FLYING);
           this.setMove(new Growl(), new SwordsDance(), new WorkUp());

    }

 }