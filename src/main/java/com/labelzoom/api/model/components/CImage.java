package com.labelzoom.api.model.components;

/* Copyright (C) 2024, LabelZoom, a subsidiary of RJF Technology Solutions LLC.
 * All rights reserved.  Proprietary and confidential.
 *
 * This file is subject to the license terms found at
 * https://www.labelzoom.net/end-user-license-agreement/
 *
 * The methods and techniques described herein are considered
 * confidential and/or trade secrets.
 * No part of this file may be copied, modified, propagated,
 * or distributed except as authorized by the license.
 */

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter @Setter
public class CImage extends AComponent
{
    private BufferedImage image;

    private float horizontalScaling = 1;

    private float verticalScaling = 1;

    /**
     * Parameterless constructor
     */
    public CImage()
    {
        this(null);
    }

    /**
     * Cloning constructor
     * @param orig the CImage to clone
     */
    protected CImage(CImage orig)
    {
        super(orig);
        if (orig != null)
        {
            // Clone fields
            image = orig.image;
            horizontalScaling = orig.horizontalScaling;
            verticalScaling = orig.verticalScaling;
        }
    }

    @Override
    public AComponent clone() { return new CImage(this); }

    @Override
    public int getZIndex() { return 0; }

    /**
     * Converts an Image (or RenderedImage) into a BufferedImage
     * @param img the image to convert
     * @return a BufferedImage
     */
    public static BufferedImage getBufferedImage(final Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        final BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = bufferedImage.createGraphics();
        /* Normally we'd use a "try-with-resources" here, but Graphics2D is not an AutoCloseable. Regardless, I felt the
         * need to put the dispose() method inside a finally block
         */
        try
        {
            g.drawImage(img, 0, 0, null);
        }
        finally
        {
            g.dispose();
        }
        return bufferedImage;
    }
}
