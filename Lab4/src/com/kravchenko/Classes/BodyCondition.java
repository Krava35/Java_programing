package com.kravchenko.Classes;

public enum BodyCondition {
    SKINNY("худой/худая"),
    NORMAL("нормальный/нормальная"),
    FAT("тостый/толстая");

    private String condition;

    BodyCondition (String condition){
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
