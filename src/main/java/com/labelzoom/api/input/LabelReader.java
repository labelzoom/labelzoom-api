package com.labelzoom.api.input;

import com.labelzoom.api.model.components.ILabel;

public interface LabelReader<E>
{
    ILabel read(E in);
}
