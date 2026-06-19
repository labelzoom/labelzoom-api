package com.labelzoom.api.input;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface GenericReader<O, I> {
    /**
     * Reads the given input as a lazily-evaluated {@link Stream} of output elements. Elements are
     * produced on demand rather than being materialized up front, which keeps memory usage low when
     * processing large inputs.
     * <p>
     * The returned stream may hold open I/O resources and should therefore be consumed within a
     * try-with-resources block (or otherwise {@link Stream#close() closed}) to ensure those resources
     * are released.
     *
     * @param in the input to read
     * @return a lazily-evaluated stream of output elements
     */
    Stream<O> stream(I in);

    /**
     * Eagerly reads all output elements into a {@link List}.
     *
     * @param in the input to read
     * @return all output elements
     */
    default List<O> read(I in)
    {
        try (final Stream<O> stream = stream(in))
        {
            return stream.collect(Collectors.toList());
        }
    }
}
