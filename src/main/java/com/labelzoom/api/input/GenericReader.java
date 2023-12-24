package com.labelzoom.api.input;

public interface GenericReader<T, E> {
    T read(E stream);
}
