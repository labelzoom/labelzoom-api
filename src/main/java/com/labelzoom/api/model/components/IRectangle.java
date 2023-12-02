package com.labelzoom.api.model.components;

public interface IRectangle extends IComponent
{
    int getWidth();
    void setWidth(int width);

    int getHeight();
    void setHeight(int height);

    int getThickness();
    void setThickness(int thickness);
}
