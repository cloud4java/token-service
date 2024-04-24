package com.silvasoft.tokenservice.common;

public class NumberUtils {
    private NumberUtils(){}

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num % 2 == 0 && num > 2) return false;
        int i = 3;
        while (i * i <= num) {
            if (num % i == 0 && num != i) return false;
            i += 2;
        }
        return true;
    }
}
