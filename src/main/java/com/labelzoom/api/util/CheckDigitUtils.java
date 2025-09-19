package com.labelzoom.api.util;

public class CheckDigitUtils
{
    public enum CheckDigitType
    {
        MOD10,
    }

    public static int getCheckDigit(final String barcode, final CheckDigitType type)
    {
        switch (type)
        {
            case MOD10: return getMod10CheckDigit(barcode);
            default: throw new IllegalArgumentException("Invalid check digit type");
        }
    }

    private static int getMod10CheckDigit(final String barcode)
    {
        final char[] digits = barcode.toCharArray();
        int sumOdd = 0;
        int sumEven = 0;
        boolean oddPosition = true; // rightmost digit is position 1 (odd)

        for (int i = digits.length - 1; i >= 0; i--)
        {
            final int n = Character.getNumericValue(digits[i]);
            if (oddPosition) {
                sumOdd += n;
            } else {
                sumEven += n;
            }
            oddPosition = !oddPosition;
        }

        final int sum = sumOdd * 3 + sumEven;
        return (10 - (sum % 10)) % 10;
    }
}
