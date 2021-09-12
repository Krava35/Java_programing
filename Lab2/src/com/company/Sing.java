package com.company;
import ru.ifmo.se.pokemon.*;

class Sing extends StatusMove {
    public Sing(){
        super(Type.NORMAL, 0, 55);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        final Effect sing = new Effect().chance(1).turns(2).condition(Status.SLEEP);
        p.addEffect(sing);
    }

    @Override
    protected String describe(){
        return "Заставляет цель заснуть";
    }

}