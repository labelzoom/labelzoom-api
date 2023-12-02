package com.labelzoom.api.model.components;

public interface IFontComponent extends IComponent, ISuppressible
{
    String getFont();
    void setFont(String font);

    float getFontSize();
    void setFontSize(float fontSize);

    float getHorizontalScaling();
    void setHorizontalScaling(float horizontalScaling);
}
