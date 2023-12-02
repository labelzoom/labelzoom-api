package com.labelzoom.api.output;

import com.labelzoom.api.model.ILabel;

public interface LabelWriter<E>
{
    E write(ILabel label);
}
