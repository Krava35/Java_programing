package com.company;
import ru.ifmo.se.pokemon.*;

class RockSlide extends PhysicalMove{
    public RockSlide(){
        super(Type.ROCK, 75, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        final Effect rockslide = new Effect().chance(0.3).turns(0).condition(Status.PARALYZE);
        p.addEffect(rockslide);
    }

    @Override
    protected String describe(){
        return "Заставляет цель вздогнуть с вероятностью 30%";
    }
}