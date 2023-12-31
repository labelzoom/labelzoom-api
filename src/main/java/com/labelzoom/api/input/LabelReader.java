package com.labelzoom.api.input;

import com.labelzoom.api.model.components.CLabel;

import java.util.List;

public interface LabelReader<E> extends GenericReader<List<CLabel>, E>
{
    List<CLabel> read(E in);
}
