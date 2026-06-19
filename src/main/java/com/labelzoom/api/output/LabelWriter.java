package com.labelzoom.api.output;

import com.labelzoom.api.model.components.CLabel;

public interface LabelWriter extends GenericWriter<CLabel>
{
    void write(CLabel label);

    default void writeAll(final Iterable<CLabel> labels)
    {
        labels.forEach(this::write);
    }
}
