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

@Getter @Setter
public class CRectangle extends AComponent
{
    private int width;

    private int height;

    private int thickness;

    /**
     * Parameterless constructor
     */
    public CRectangle() { this(null); }

    /**
     * Cloning constructor
     * @param original the CRectangle to clone
     */
    protected CRectangle(final CRectangle original)
    {
        super(original);
        if (original != null)
        {
            width = original.width;
            height = original.height;
            thickness = original.thickness;
        }
    }

    @Override
    public AComponent clone() {
        return new CRectangle(this);
    }

    @Override
    public int getZIndex() { return 2; }
}
