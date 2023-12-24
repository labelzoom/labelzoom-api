package com.labelzoom.api.output;

public interface GenericWriter<T> {
    void write(T item);
}
