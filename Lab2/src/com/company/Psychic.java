package com.company;
import ru.ifmo.se.pokemon.*;

class Psychic extends SpecialMove {
    public Psychic(){
        super(Type.PSYCHIC, 0, 55);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        final Effect psychic = new Effect().chance(0.1).turns(2).stat(Stat.SPECIAL_ATTACK, -1);
        p.addEffect(psychic);
    }

    @Override
    protected String describe(){
        return "Заставляет цель заснуть на 2 хода с вероятностью 10%";
    }

}