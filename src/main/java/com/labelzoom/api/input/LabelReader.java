package com.labelzoom.api.input;

import com.labelzoom.api.model.components.CLabel;

public interface LabelReader<I> extends GenericReader<Iterable<CLabel>, I>
{
    Iterable<CLabel> read(I in);
}
