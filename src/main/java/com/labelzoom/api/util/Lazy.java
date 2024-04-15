package com.labelzoom.api.util;

import java.util.function.Supplier;

public class Lazy<E>
{
    private final Supplier<? extends E> initializer;

    private E value;

    private Lazy(final Supplier<? extends E> initializer)
    {
        this.initializer = initializer;
    }

    public E get()
    {
        if (value == null)
        {
            value = initializer.get();
        }
        return value;
    }

    public static <T> Lazy<T> of(final Supplier<? extends T> initializer)
    {
        return new Lazy<>(initializer);
    }
}
