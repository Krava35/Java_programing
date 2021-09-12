package com.kravchenko.classes;

public class Advice {
    final private Korotishki from;
    final private Korotishki to;
    final private String advice;

    public Advice(Korotishki f, Korotishki t, String a){
        this.from = f;
        this.to = t;
        this.advice = a;
    }

    public Korotishki getFrom(){
        return from;
    }

    public Korotishki getTo(){
        return to;
    }

    public String getAdvice() {
        return advice;
    }

}
