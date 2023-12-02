package com.labelzoom.api.input;

import com.labelzoom.api.model.ILabel;

public interface LabelReader<E>
{
    ILabel read(E in);
}
