package com.labelzoom.api.model.components;

import java.awt.image.RenderedImage;

public interface IImage extends IComponent
{
    IImageWrapper getImage();
    void setImage(IImageWrapper imageWrapper);

    float getHorizontalScaling();
    void setHorizontalScaling(float horizontalScaling);

    float getVerticalScaling();
    void setVerticalScaling(float verticalScaling);

    interface IImageWrapper
    {
        RenderedImage getImage();
    }
}
