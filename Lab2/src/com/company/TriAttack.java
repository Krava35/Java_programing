package com.company;
import ru.ifmo.se.pokemon.*;

class TriAttack extends SpecialMove {
    public TriAttack(){
        super(Type.NORMAL, 80, 100);
    }


    @Override
    protected void applyOppEffects(Pokemon p){

        final Effect first_attack = new Effect().chance(0.2).turns(0).condition(Status.PARALYZE);
        final Effect second_attack = new Effect().chance(0.2).turns(0).condition(Status.BURN);
        final Effect third_attack = new Effect().chance(0.2).turns(0).condition(Status.FREEZE);

        switch ((int) (Math.random()*2)){
            case 0: p.addEffect(first_attack);
            case 1: p.addEffect(second_attack);
            case 2: p.addEffect(third_attack);
            default: break;
        }

    }
    @Override
    protected String describe(){
        return "Накладывает эффект парализации,восламинения или заморзки с вероятностью 20%";
    }

}