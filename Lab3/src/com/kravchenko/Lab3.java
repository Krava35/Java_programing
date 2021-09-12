package com.kravchenko;

import com.kravchenko.classes.*;

public class Lab3 {

    public static void main(String[] args) {

        Act NeznaikaNaLune = new Act();
        NeznaikaNaLune.addTravelers(new Korotishki("Незнайка", Place.TUPICHOK),
                new Korotishki("Пончик", Place.ROCKET));
        NeznaikaNaLune.setPlanedTravelers(new Korotishki[48]);
        NeznaikaNaLune.setDaysInMonth(30);
        NeznaikaNaLune.setMonthsInYear(12);
        NeznaikaNaLune.Start();
        Operation.MULTIPLY.action(1, 2);

    }
}
