package com.kravchenko.interfaces;

import com.kravchenko.classes.Operation;

public interface Arithmetics_Operation {
    int multy(Operation m, int x, int y);
    int div(Operation d, int x, int y);
    int mod(Operation m, int x, int y);
}