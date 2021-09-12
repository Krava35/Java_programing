package com.kravchenko.classes;

import com.kravchenko.interfaces.Action;

public enum Operation implements Action {
    MULTIPLY{
        public int action(int x, int y) {
            return x * y;
        }
    },

    DIV{
        public int action(int x, int y) {
            return x / y;
        }
    },

    MOD{
        public int action(int x, int y) {
            return x % y;
        }
    };

}
