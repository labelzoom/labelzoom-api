package com.labelzoom.api.output;

import com.labelzoom.api.model.components.ILabel;

public interface LabelWriter
{
    void write(ILabel label);
}
