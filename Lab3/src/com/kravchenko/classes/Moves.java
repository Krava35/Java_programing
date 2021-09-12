package com.kravchenko.classes;

public enum Moves{
    STOP("стоит"),
    RUN_AWAY("отбегает"),
    GET_OUT("вылезает"),
    CLIMB("лезет"),
    LIE_ON("лежит"),
    FOLLOW_ADVICE("следует совету"),
    EATING("едят"),
    REMEMBER("вспоминает"),
    CALCULATE("рассчитывает"),
    MULTIPLIES("умножает числа"),
    DIV("нацело делит числа"),
    MOD("определяет остаток от числа"),
    OPENS("открывает"),
    PULL_BACK("отдёргивает"),
    PULL_OUT("вытягивает"),
    SEE("видит"),
    SNIFF("обнюхивает"),
    PEEPS("поглядывает"),
    LOOK_AROUND("осматревается"),
    NOTICE("замечает"),
    UNDERSTAND("понял"),
    MUTTER("бормочет");

    final private String move;

    Moves(String move){
        this.move = move;
    }

    public String getMove() {
        return move;
    }
}
