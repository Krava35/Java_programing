package com.kravchenko.classes;

public abstract class Heroes {
    private String name;
    private Place location;
    private Moves move;
    private StatusHeroes status;
    private Eyes eyes;
    private Foot foot;
    private Volume body_volume;

    {
        name = "Безимянный персонаж";
        body_volume = null;
    }

    public Heroes(){};

    public Heroes(String name){
        this.name = name;
    }

    public Heroes(String name, Place location){
        this(name);
        this.location = location;
    }

    public void setName(String n){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocation(Place place) {
        this.location = place;
    }

    public void setMove(Moves move){
        this.move = move;
    }

    public String getMove(){
        return this.name + " " + this.move.getMove();
    }

    public void setBody_volume(Volume v){
        this.body_volume = v;
    }

    public Volume getBody_volume(){
        return body_volume;
    }

    public void setEyesMove(Moves move) {
        this.eyes.move = move;
    }

    public String getEyesMove() {
        return this.name + " " + this.eyes.move.getMove() + "глаза";
    }

    public void setFootMove(Moves move) {
        this.foot.move = move;
    }

    public String getFootMove() {
        return this.name + " " + this.foot.move.getMove() + "ногой";
    }

    public class Eyes{
        private Moves move;
    }

    public class Foot{
        private Moves move;
    }


    public boolean equals(Heroes hero){
        return this.name == hero.name;
    }

    public String toString(){
        return getClass().getName()+"@"+hashCode();
    }

    public int hashCode(){
        return name.length() * 100;
    }


}
