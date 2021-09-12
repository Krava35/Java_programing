package com.company;
import ru.ifmo.se.pokemon.*;

  public class Lab2 {

          public static void main(String[] args){

              Battle battle = new Battle();

              battle.addAlly(new Arceus("Arceus", 1) );
              battle.addAlly(new Doduo("Doduo", 50) );
              battle.addAlly(new Dodrio("Dodrio", 100) );
              battle.addFoe(new Cleffa("Cleffa", 1) );
              battle.addFoe(new Clefairy("Clefairy", 50) );
              battle.addFoe(new Clefable("Clefable", 100) );
              battle.go();

          }


}