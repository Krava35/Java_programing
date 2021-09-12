package com.company;
import ru.ifmo.se.pokemon.*;

public final class SwordsDance extends StatusMove{
    public SwordsDance() {

    };

    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.ATTACK, +1);
    }

    protected String describe(){
        return "Увеличивает атаку на 2 уровня";
    }

}