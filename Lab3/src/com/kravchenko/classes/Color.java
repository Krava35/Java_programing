package com.kravchenko.classes;

public enum Color {
    GREY("серый/серая"),
    WHITE("белый/белая"),
    BLACK("чёрный/чёрная");

    final private String color;

    Color (String color){
        this.color = color;
    }
}
