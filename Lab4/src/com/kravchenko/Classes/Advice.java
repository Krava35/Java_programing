package com.kravchenko.Classes;

public class Advice {
    private String information;
    private Korotishki from;

    public Advice(String information, Korotishki korotishka) {
        this.information = information;
        this.from = korotishka;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getFrom() {
        return from.getName();
    }

    public void setFrom(Korotishki from) {
        this.from = from;
    }
}
