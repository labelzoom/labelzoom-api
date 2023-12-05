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
public abstract class AFontComponent extends AComponent
{
    private float horizontalScaling = 1.0f;

    private float fontSize = 10.0f;

    private String font = "0";

    private boolean blankWhenNull = false;

    /**
     * Parameterless constructor
     */
    public AFontComponent() { this(null); }

    /**
     * Cloning constructor
     * @param original the AFontComponent to clone
     */
    protected AFontComponent(final AFontComponent original)
    {
    	super(original);
        if (original != null)
        {
            fontSize = original.fontSize;
            font = original.font;
            blankWhenNull = original.blankWhenNull;
            horizontalScaling = original.horizontalScaling;
        }
    }

	@Override
    public abstract AComponent clone();

    @Override
    public int getZIndex() { return 3; }
}
