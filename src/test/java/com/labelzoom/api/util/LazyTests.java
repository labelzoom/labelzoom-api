package com.labelzoom.api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LazyTests
{
    @Test
    void testLazy()
    {
        final Lazy<?> lazy = Lazy.of(() -> "myValue");
        assertEquals("myValue", lazy.get());
    }
}
