package com.kravchenko.Classes;

public abstract class Heroes {
    private String name;
    private String status;
    private Tupichok place;

    {
        this.name = "неизвестно";
        this.status = "неизвестно";
        this.place = new Tupichok();
    }

    public Heroes(){};

    public Heroes(String name) {
        this.name = name;
    }

    public Heroes(String name, Tupichok place){
        this(name);
        this.place = place;
    }

    public Heroes(String name, Tupichok place, String status){
        this(name, place);
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    abstract public void sleep();

    public Place getPlace() {
        return place;
    }

    public void setPlace(Tupichok place) {
        this.place = place;
    }

    public void phrase(String phrase){
        System.out.println(phrase + " - сказал " + getName() );
    }

    public class Eyes{
        private boolean open;

        {
            open = true;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }
    }
}
