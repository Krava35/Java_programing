package com.kravchenko.Classes;

public abstract class Animal {
    private String name;
    private Color color;

    abstract void run();
    abstract void stop();
    abstract void eat();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color.getColor();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
