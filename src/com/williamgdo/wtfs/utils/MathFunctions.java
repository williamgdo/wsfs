package com.williamgdo.wtfs.utils;

public final class MathFunctions {
    public static short roundUpLongDivision(long a, long b) {
        return (short) (a / b + ((a % b == 0) ? 0 : 1));
    }
}
