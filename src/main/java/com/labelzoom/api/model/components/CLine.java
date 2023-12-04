package com.labelzoom.api.model.components;

/* Copyright (C) 2021, LabelZoom, a subsidiary of RJF Technology Solutions, Inc.
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

@Getter @Setter
public class CLine extends AComponent
{
    public enum Orientation
    {
        Horizontal, Vertical;
    }

    protected Orientation orientation;

    protected int thickness;

    protected int length;

    /**
     * Parameterless constructor
     */
    public CLine() { this(null); }

    /**
     * Cloning constructor
     * @param original the CLine to clone
     */
    protected CLine(final CLine original)
    {
        super(original);
        if (original != null)
        {
            orientation = original.getOrientation();
            thickness = original.getThickness();
            length = original.getLength();
        }
    }

    @Override
    public AComponent clone() {
        return new CLine(this);
    }

    @Override
    public int getZIndex() { return 2; }
}
