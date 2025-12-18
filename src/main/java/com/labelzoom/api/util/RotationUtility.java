package com.labelzoom.api.util;

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

import com.labelzoom.api.model.components.AComponent;
import com.labelzoom.api.model.components.AFontComponent;
import com.labelzoom.api.model.components.CLabel;
import com.labelzoom.api.model.components.barcodes.linear.ALinearBarcode;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class RotationUtility
{
    private RotationUtility() {}

    public static int normalizeRotation(float degrees) { return normalizeRotation(Math.round(degrees)); }

    public static int normalizeRotation(int degrees)
    {
        degrees = degrees % 360;
        if (degrees < 0) degrees += 360;
        return degrees;
    }

    public static void rotate(final CLabel label, final int degrees)
    {
        switch (degrees)
        {
            case -90:
                rotateCounterClockwise(label);
                return;
            case 0:
                return;
            case 90:
                rotateClockwise(label);
                break;
            case 180:
                rotateClockwise(rotateClockwise(label));
                break;
            case 270:
                rotateClockwise(rotateClockwise(rotateClockwise(label)));
                break;
        }
    }

    private static CLabel rotateClockwise(final CLabel label)
    {
        for (AComponent component : label.getElements())
        {
            final int newX = label.getWidth() - component.getTop();
            final int newY = component.getLeft();
            component.setLeft(newX);
            component.setTop(newY);
        }

        // TODO: Do we really want to do this?
        final int newWidth = label.getHeight();
        final int newHeight = label.getWidth();
        label.setWidth(newWidth);
        label.setHeight(newHeight);

        if (label.getOrientation() == CLabel.PageOrientation.Portrait)
        {
            label.setOrientation(CLabel.PageOrientation.Landscape);
        }
        else if (label.getOrientation() == CLabel.PageOrientation.Landscape)
        {
            label.setOrientation(CLabel.PageOrientation.ReversePortrait);
        }
        else if (label.getOrientation() == CLabel.PageOrientation.ReversePortrait)
        {
            label.setOrientation(CLabel.PageOrientation.ReverseLandscape);
        }
        else
        {
            label.setOrientation(CLabel.PageOrientation.Portrait);
        }
        return label;
    }

    /**
     * TODO: Counter clockwise rotation
     * @param label the label to rotate
     * @return the rotated label
     */
    private static CLabel rotateCounterClockwise(final CLabel label)
    {
        for (AComponent component : label.getElements())
        {
            int yOffset = 0;
            if (component instanceof ALinearBarcode)
            {
                ALinearBarcode linearBarcode = (ALinearBarcode)component;
                yOffset = -linearBarcode.getHeight();
            }
            else if (component instanceof AFontComponent)
            {
                AFontComponent fontComponent = (AFontComponent)component;
                yOffset = -Math.round(fontComponent.getFontSize());
            }
            final int newX = component.getTop();
            final int newY = label.getWidth() - component.getLeft() + yOffset;
            component.setLeft(newX);
            component.setTop(newY);
        }

        // TODO: Do we really want to do this?
        //final int newWidth = label.getHeight();
        //final int newHeight = label.getWidth();
        //label.setWidth(newWidth);
        //label.setHeight(newHeight);

        return label;
    }

    /**
     *
     * @param image the image to rotate
     * @param degrees number of degrees to rotate
     * @return the rotated image
     */
    public static BufferedImage rotateImage(final BufferedImage image, int degrees)
    {
        final double rads = Math.toRadians(degrees);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int newWidth = (int) Math.ceil(image.getWidth() * cos + image.getHeight() * sin);
        final int newHeight = (int) Math.ceil(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(newWidth * 0.5d, newHeight * 0.5d);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() * 0.5d, -image.getHeight() * 0.5d);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        return rotatedImage;
    }

    /**
     *
     * @param image the image to rotate
     * @param degrees number of degrees to rotate
     * @return the rotated image
     */
    public static BufferedImage rotateImageAndPreserveColorModel(final BufferedImage image, int degrees)
    {
        final double rads = Math.toRadians(degrees);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int newWidth = (int) Math.ceil(image.getWidth() * cos + image.getHeight() * sin);
        final int newHeight = (int) Math.ceil(image.getHeight() * cos + image.getWidth() * sin);
        final ColorModel cm = image.getColorModel();
        final boolean isAlphaPreMultiplied = cm.isAlphaPremultiplied();
        final WritableRaster raster = cm.createCompatibleWritableRaster(newWidth, newHeight);
        final BufferedImage rotatedImage = new BufferedImage(cm, raster, isAlphaPreMultiplied, null);
        final AffineTransform at = new AffineTransform();
        at.translate(newWidth * 0.5d, newHeight * 0.5d);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() * 0.5d, -image.getHeight() * 0.5d);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        return rotatedImage;
    }
}
