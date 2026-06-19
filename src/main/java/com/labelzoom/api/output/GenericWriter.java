package com.labelzoom.api.output;

import lombok.NonNull;

import java.util.stream.Stream;

public interface GenericWriter<I> {
    void write(I item);

    /**
     * Writes every item produced by the given stream, consuming it lazily so that items can be
     * written as they are produced without materializing them all in memory. This pairs naturally
     * with {@link com.labelzoom.api.input.GenericReader#stream(Object)} to form an end-to-end
     * streaming pipeline.
     * <p>
     * The supplied stream is {@link Stream#close() closed} after it has been consumed.
     *
     * @param items the items to write
     */
    default void writeAll(@NonNull final Stream<? extends I> items)
    {
        try (items)
        {
            items.forEach(this::write);
        }
    }

    /**
     * Writes every item in the given iterable.
     *
     * @param items the items to write
     */
    default void writeAll(@NonNull final Iterable<? extends I> items)
    {
        items.forEach(this::write);
    }
}
