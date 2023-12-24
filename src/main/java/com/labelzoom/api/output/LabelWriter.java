package com.labelzoom.api.output;

import com.labelzoom.api.model.components.CLabel;

public interface LabelWriter extends GenericWriter<CLabel>
{
    void write(CLabel label);
}
