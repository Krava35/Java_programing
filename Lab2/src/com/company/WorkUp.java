package com.company;
import ru.ifmo.se.pokemon.*;

class WorkUp extends StatusMove{
    public WorkUp(){};

    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.SPECIAL_ATTACK, +1);
    }

    @Override
    protected String describe(){
        return "Увеличивает специальную атаку на 1 уровень";
    }

}