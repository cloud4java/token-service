package com.silvasoft.tokenservice.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberUtilsTest {

    @Test
    void isPrime() {
        Assertions.assertTrue(NumberUtils.isPrime(9533));
    }

    @Test
    void isNotPrime() {
        Assertions.assertFalse(NumberUtils.isPrime(9532));
    }
}