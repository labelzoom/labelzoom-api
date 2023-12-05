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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class AContainer extends AComponent
{
    private final List<AComponent> children = new ArrayList<>(); // Using ArrayList because that's what Jackson uses

    /**
     * Parameterless constructor
     */
    public AContainer() { this(null); }

    /**
     * Cloning constructor
     * @param original the AContainer to clone
     */
    protected AContainer(final AContainer original)
    {
        super(original);

        // Clone children
        for (final AComponent component : original.children)
        {
            children.add(component.clone());
        }
    }
}
