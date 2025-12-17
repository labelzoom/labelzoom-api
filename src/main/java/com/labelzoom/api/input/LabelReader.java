package com.labelzoom.api.input;

import com.labelzoom.api.model.components.CLabel;

public interface LabelReader<E> extends GenericReader<Iterable<CLabel>, E>
{
    Iterable<CLabel> read(E in);
}
