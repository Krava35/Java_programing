package com.kravchenko.classes;

public enum StatusHeroes {
    CATCH_A_COLD("простужен"),
    SUFFOCATE("задыхается"),
    SCARED("испуган"),
    ANXIETY("тревожен"),
    IN_SAFETY("в безопасности"),
    NOT_IN_SAFETY("не в безопасности"),
    QUITE("тихий"),
    TORMENTED_BY_NIGHTMARES("терзают кошмары"),
    GROANS("стонет"),
    HUMS("мычит"),
    SCREAMS("вскрикивает"),
    SHUDDER("вздрагивает"),
    FEEL_BAD("плохо"),
    DOES_NOT_PAY_ATTENTION("не обращает внимания"),
    IN_CALM("в спокойствие"),
    SLEEP("спит"),
    ANGRY("сердит");


    final private String status;

    StatusHeroes(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
