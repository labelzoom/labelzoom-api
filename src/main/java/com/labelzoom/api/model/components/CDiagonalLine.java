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

import com.labelzoom.api.model.LineColor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CDiagonalLine extends AComponent
{
    /**
     * Orientation of the diagonal line.
     * <ul>
     *   <li>{@code Forward} – visually "/" (lower-left to upper-right; ZPL {@code R})</li>
     *   <li>{@code Backward} – visually "\" (upper-left to lower-right; ZPL {@code L})</li>
     * </ul>
     */
    public enum DiagonalOrientation
    {
        Forward, Backward;
    }

    private DiagonalOrientation orientation;

    private int width;

    private int height;

    private int thickness;

    private LineColor color = LineColor.Black;

    /**
     * Parameterless constructor
     */
    public CDiagonalLine() { this(null); }

    /**
     * Cloning constructor
     * @param original the CDiagonalLine to clone
     */
    protected CDiagonalLine(final CDiagonalLine original)
    {
        super(original);
        if (original != null)
        {
            orientation = original.getOrientation();
            width = original.getWidth();
            height = original.getHeight();
            thickness = original.getThickness();
            color = original.getColor();
        }
    }

    @Override
    public AComponent clone() {
        return new CDiagonalLine(this);
    }

    @Override
    public int getZIndex() { return 2; }
}
