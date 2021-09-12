package com.company;
import ru.ifmo.se.pokemon.*;

   public class Dodrio extends Doduo{

       public Dodrio(String name, int level){

           super(name, level);
           this.setStats(60, 110, 70, 60, 60, 110);
           this.setType(Type.FLYING);
           this.setMove(new Growl(), new SwordsDance(), new WorkUp(), new TriAttack());

       }

   }