package com.company;

import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {

        long[] d = new long[7];
        for (int i = 0; i < d.length; i++) {
            d[i] = 17 - i * 2;
        }


        double[] x = new double[13];
        for (int i = 0; i < x.length; i++) {
            x[i] = random() * (7 + 12) - 12;
        }


        double[][] D = new double[7][13];
        for (int i = 0; i < D.length; i++ ) {
            for (int j = 0; j < D[i].length; j++) {
                if (d[i] == 9) {
                    D[i][j] = log(pow(sin(cos(sin(x[j]))), 2));
                }
                else if (d[i] >= 7 && d[i] <= 13) {
                    D[i][j] = cos(pow(2 * atan((x[j] - 2.5) / 19), asin((x[j] - 2.5) / 19)));
                }
                else {
                    D[i][j] = log((acos((x[j] - 2.5) / 19) + 1) / 5);
                }

            }
        }


        for (int i = D.length - 1; i >= 0; i--) {
            System.out.println();
            for (int j = 0; j < D[i].length; j++ ) {
                if (j < D[i].length - 1) {
                    System.out.format(" %7.4f |", D[i][j]);
                }
                else {
                    System.out.format(" %7.4f ", D[i][j]);
                }

            }
        }
    }
}

