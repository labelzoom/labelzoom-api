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

    /**
     * <p>
     * <a href="https://www.axicon.com/checkdigitcalculator.html">MOD10 Check Digit Calculator</a>
     * </p>
     *
     * <p>
     * How to calculate your check digit yourself
     * </p>
     *
     * <p>
     * Example barcode number: 501234576421
     * </p>
     *
     * <p>
     * Step 1: add together all alternate numbers starting from the right<br>
     * <code>5 0 1 2 3 4 5 7 6 4 2 1</code><br>
     * <code>0 + 2 + 4 + 7 + 4 + 1 = 18</code>
     * </p>
     *
     * <p>
     * Step 2: multiply the answer by 3<br>
     * <code>18 x 3 = 54</code>
     * </p>
     *
     * <p>
     * Step 3: now add together the remaining numbers<br>
     * <code>5 0 1 2 3 4 5 7 6 4 2 1</code><br>
     * <code>5 + 1 + 3 + 5 + 6 + 2 = 22</code>
     * </p>
     *
     * <p>
     * Step 4: add step 2 and 3 together<br>
     * <code>54 + 22 = 76</code>
     * </p>
     *
     * <p>
     * Step 5: the difference between step 4 and the next 10th number:<br>
     * <code>76 + 4 = 80</code><br>
     * Check digit = <code>4</code>
     * </p>
     *
     * @param barcode the barcode data
     * @return the check digit
     */
    private static int getMod10CheckDigit(final String barcode)
    {
        char[] digits = barcode.toCharArray();
        /* Sum odds and evens separately so that we only perform one multiplication. In practice, one large
         * multiplication was faster than several small multiplications
         */
        int evens = 0;
        int odds = 0;
        boolean isEven = false; // Use alternating boolean variable rather than modular division (e.g., i % 2)
        for (int i = digits.length - 1; i >= 0; i--)
        {
            if (isEven = !isEven) // Invert and update value as we read it. Ignore IntelliJ, it doesn't appreciate my l33tness
            {
                evens += Character.getNumericValue(digits[i]);
            }
            else
            {
                odds += Character.getNumericValue(digits[i]);
            }
        }
        return 10 - (((evens * 3) + odds) % 10);
    }
}
