package com.labelzoom.api.model.components;

import com.labelzoom.api.model.HorizontalAlignment;

public interface IVariableText extends IFontComponent, IDynamicField
{
    boolean isAutoSize();
    void setAutoSize(boolean autoSize);

    int getWidth();
    void setWidth(int width);

    int getHeight();
    void setHeight(int height);

    HorizontalAlignment getHorizontalAlignment();
    void setHorizontalAlignment(HorizontalAlignment alignment);
}
