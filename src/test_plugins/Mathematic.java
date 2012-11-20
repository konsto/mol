package test_plugins;

import java.lang.Math;

public class Mathematic {

    public int abs(int number) {
        if (number >= 0) {
            return number;
        } else {
            return -number;
        }
    }

    public double sqrt(int liczba) {
        return Math.sqrt(liczba);
    }
    
    public int testFunction(int a) {
        return a;
    }
}
