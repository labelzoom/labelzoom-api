package com.labelzoom.api.output;

public interface GenericWriter<I> {
    void write(I item);

    default void write(Iterable<I> items) {
        for (final I item : items) {
            write(item);
        }
    }
}
