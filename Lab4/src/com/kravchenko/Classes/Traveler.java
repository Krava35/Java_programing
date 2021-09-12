package com.kravchenko.Classes;

import com.kravchenko.Interfaces.ArithmeticsOperation;
import com.kravchenko.Interfaces.Climb;

public class Traveler extends Korotishki implements ArithmeticsOperation, Climb {

    public Traveler(){};

    public Traveler(String name) {
        super(name);
    }

    public Traveler(String name, Tupichok place){
        super(name, place);
    }

    @Override
    public int multy(int x, int y) {
        System.out.println(getName() + " умножает числа");
        return x * y;
    }

    @Override
    public int div(int x, int y) {
        System.out.println(getName() + " делит нацело");
        return x / y;
    }

    @Override
    public int mod(int x, int y) {
        System.out.println(getName() + " определяет остаток");
        return x % y;
    }

    @Override
    public void climb() {
        System.out.println(this.getName() + " залез на полку");
    }



}
