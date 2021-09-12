package com.company;
import ru.ifmo.se.pokemon.*;

class Waterfall extends PhysicalMove {
    public Waterfall(){
        super(Type.NORMAL, 80, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        final Effect waterfall = new Effect().chance(0.2).turns(0).condition(Status.PARALYZE);
        p.addEffect(waterfall);
    }

    @Override
    protected String describe(){
        return "Заставляет цель вздогнуть с вероятностью 20%";
    }

}