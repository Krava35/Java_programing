package com.company;
import ru.ifmo.se.pokemon.*;

public class Arceus extends Pokemon {

       public Arceus(String name, int level){

           super(name, level);
           this.setStats(120, 120, 120, 120, 120, 120);
           this.setType(Type.NORMAL);
           this.setMove(new Waterfall(), new DragonClaw(), new RockSlide(), new XScissor());

       }

   }