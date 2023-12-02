package com.labelzoom.api.model.components;

import com.labelzoom.api.model.Justification;
import com.labelzoom.api.model.PositioningMode;

public interface IComponent extends Cloneable
{
    int getLeft();
    void setLeft(int left);

    int getTop();
    void setTop(int top);

    float getRotation();
    void setRotation(float rotation);

    boolean isReverse();
    void setReverse(boolean reverse);

    Justification getJustification();
    void setJustification(Justification justification);

    PositioningMode getPositioningMode();
    void setPositioningMode(PositioningMode positioningMode);

    int getZIndex();
}
