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

import com.labelzoom.api.model.components.*;
import com.labelzoom.api.model.components.barcodes.ABarcode;
import com.labelzoom.api.model.components.barcodes.linear.ALinearBarcode;

import java.util.List;

public class DpiUtility
{
    public static final float POINTS_TO_INCHES = 1.0f / 72.0f; // 1 pt = 1/72 inch
    public static final int INCHES_TO_POINTS = 72;

    private final double scalingFactor;
    private final double fontScalingFactor;

    public DpiUtility(final int sourceDpi, final int targetDpi, final int sourceFontDpi, final int targetFontDpi)
    {
        this.scalingFactor = (double)targetDpi / (double)sourceDpi;
        this.fontScalingFactor = (double)targetFontDpi / (double)sourceFontDpi;
    }

    private float applyResize(final float sourceValue)
    {
        return (float)(((double)sourceValue) * scalingFactor);
    }

    private int applyResize(final int sourceValue)
    {
        return Math.round(applyResize((float)sourceValue));
    }

    private float applyFontResize(final float sourceValue)
    {
        return (float)(((double)sourceValue) * fontScalingFactor);
    }

    public CLabel resizeLabel(final CLabel label)
    {
        final CLabel clone = label.clone(true);
        clone.setHeight(applyResize(label.getHeight()));
        clone.setWidth(applyResize(label.getWidth()));
        shiftElements(clone.getElements());
        return clone;
    }

    private void shiftElements(final List<AComponent> elements)
    {
        for (final AComponent component : elements)
        {
            component.setTop(applyResize(component.getTop()));
            component.setLeft(applyResize(component.getLeft()));
            if (component instanceof AFontComponent)
            {
                final AFontComponent fontComponent = (AFontComponent)component;
                fontComponent.setFontSize(applyFontResize(fontComponent.getFontSize()));
                if (fontComponent instanceof CVariableText)
                {
                    final CVariableText variableText = (CVariableText) fontComponent;
                    if (!variableText.isAutoSize())
                    {
                        variableText.setWidth(applyResize(variableText.getWidth()));
                        variableText.setHeight(applyResize(variableText.getHeight()));
                    }
                }
            }
            else if (component instanceof ABarcode)
            {
                if (component instanceof ALinearBarcode)
                {
                    final ALinearBarcode linearBarcode = (ALinearBarcode)component;
                    linearBarcode.setHeight(applyResize(linearBarcode.getHeight()));
                }
            }
            else if (component instanceof CLine)
            {
                final CLine line = (CLine)component;
                line.setLength(applyResize(line.getLength()));
                line.setThickness(Math.max(applyResize(line.getThickness()), 1)); // We don't ever want thickness to be less than 1, otherwise it won't show up in the design tab
            }
            else if (component instanceof CRectangle)
            {
                final CRectangle rectangle = (CRectangle) component;
                rectangle.setWidth(applyResize(rectangle.getWidth()));
                rectangle.setHeight(applyResize(rectangle.getHeight()));
                rectangle.setThickness(applyResize(rectangle.getThickness()));
            }
            else if (component instanceof CImage)
            {
                final CImage image = (CImage)component;
                image.setHorizontalScaling(applyResize(image.getHorizontalScaling()));
                image.setVerticalScaling(applyResize(image.getVerticalScaling()));
            }
        }
    }
}
