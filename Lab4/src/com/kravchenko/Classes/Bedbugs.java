package com.kravchenko.Classes;

public class Bedbugs extends Animal {
    {
        this.setName("клоппы");
    }

    @Override
    void eat() {
        System.out.print(getName() + " едят");
    }

    @Override
    void run() {
        System.out.println(getName() + " бегут");
    }

    @Override
    void stop() {
        System.out.println(getName() + " остановились");
    }
}
