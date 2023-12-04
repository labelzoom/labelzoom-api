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

import com.labelzoom.api.model.PositioningMode;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class AComponent implements Cloneable {

    @Setter
    protected int left;

    @Setter
    protected int top;

    protected float rotation = 0;
    public void setRotation(float rotation) { this.rotation = rotation % 360; }

    @Setter
    protected boolean reverse = false;

    @Setter
    protected Justification justification = Justification.Left;

    @Setter
    protected PositioningMode positioningMode = PositioningMode.Origin;

    /**
     * Parameterless constructor
     */
    public AComponent() { this(null); }

    /**
     * Cloning constructor
     * @param original the AComponent to clone
     */
    protected AComponent(final AComponent original)
    {
        super();
        if (original != null)
        {
            this.left = original.left;
            this.top = original.top;
            this.rotation = original.rotation;
            this.reverse = original.reverse;
            this.justification = original.justification;
            this.positioningMode = original.positioningMode;
        }
    }

    public boolean isRotated()
    {
        return (rotation % 180) != 0;
    }

    public boolean isInverted()
    {
        return (Math.abs(rotation) > 90 && Math.abs(rotation) < 270);
    }

    @Override
    public abstract AComponent clone();

    public abstract int getZIndex();
}
