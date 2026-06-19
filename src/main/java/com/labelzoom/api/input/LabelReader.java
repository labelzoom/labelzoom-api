package com.labelzoom.api.input;

import com.labelzoom.api.model.components.CLabel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface LabelReader<E> extends GenericReader<Iterable<CLabel>, E>
{
    default List<CLabel> read(E in)
    {
        return StreamSupport.stream(readAsync(in).spliterator(), false).collect(Collectors.toList());
    }

    Iterable<CLabel> readAsync(E in);
}
