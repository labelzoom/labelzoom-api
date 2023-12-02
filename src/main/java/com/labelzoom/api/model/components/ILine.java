package com.labelzoom.api.model.components;

import com.labelzoom.api.model.Orientation;

public interface ILine extends IComponent
{
    Orientation getOrientation();
    void setOrientation(Orientation orientation);

    int getThickness();
    void setThickness(int thickness);

    int getLength();
    void setLength(int length);
}
