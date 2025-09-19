package com.labelzoom.api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckDigitUtilsTests
{
    @Test
    void testAxiconExample() { assertEquals(4, CheckDigitUtils.getCheckDigit("501234576421", CheckDigitUtils.CheckDigitType.MOD10)); }

    @Test
    void testSsccExample() { assertEquals(8, CheckDigitUtils.getCheckDigit("0000123456000000001", CheckDigitUtils.CheckDigitType.MOD10)); }

    @Test
    void testCode128Example() { assertEquals(8, CheckDigitUtils.getCheckDigit("0008100887950411637", CheckDigitUtils.CheckDigitType.MOD10)); }

    @Test
    void testZeroCase() { assertEquals(0, CheckDigitUtils.getCheckDigit("0028179150205445698", CheckDigitUtils.CheckDigitType.MOD10)); }
}
