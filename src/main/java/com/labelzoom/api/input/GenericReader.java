package com.labelzoom.api.input;

public interface GenericReader<O, I> {
    O read(I stream);
}
