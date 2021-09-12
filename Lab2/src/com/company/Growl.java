package com.company;
import ru.ifmo.se.pokemon.*;

  class Growl extends StatusMove{
      public Growl(){
          super(Type.NORMAL, 0, 100);
      }

      @Override
      protected void applyOppEffects(Pokemon p){
          p.setMod(Stat.ATTACK, -1);
      }

      @Override
      protected String describe(){
          return "Уменьшает атаку цели на 1 уровень";
      }

  }
