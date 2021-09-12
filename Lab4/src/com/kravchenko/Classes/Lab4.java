package com.kravchenko.Classes;

public class Lab4 {

    public static void main(String[] args) {
        Act act = new Act();
        act.addTravelers(new Traveler("Незнайка"), new Traveler("Пончик"));
        act.addKorotishka(new Korotishki("Покладистый"));
        act.setPlanedTravelers(new Traveler[48]);
        act.setDaysInMonth(30);
        act.setMonthsInYear(12);
        act.start();


    }
}
