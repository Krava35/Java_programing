package com.kravchenko.Classes;

public enum Color {
    WHITE("белый"),
    BLACK("чёрный"),
    GREY("серый"),
    BLUE("синий"),
    RED("красный"),
    YELLOW("жёлтый"),
    GREEN("зелёный"),
    ORANGE("оранжевый"),
    PURPLE("фиолетовый"),
    BROWN("коричневый"),
    PINK("розовый");

    private String color;

    Color (String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
