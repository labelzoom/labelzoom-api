package com.labelzoom.api.model.components;

import java.awt.image.RenderedImage;

public interface IImage<E extends IImage.IImageWrapper> extends IComponent
{
    E getImage();
    void setImage(E imageWrapper);

    float getHorizontalScaling();
    void setHorizontalScaling(float horizontalScaling);

    float getVerticalScaling();
    void setVerticalScaling(float verticalScaling);

    interface IImageWrapper
    {
        RenderedImage getImage();
    }
}
