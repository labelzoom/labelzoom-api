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

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CLayer
{
    private String name;

    private List<AComponent> elements = new ArrayList<>(); // Using ArrayList because that's what Jackson uses

    /**
     * Parameterless constructor
     */
    public CLayer() { this(null); }

    /**
     * Regular constructor
     * @param layerName the name of the layer to create
     */
    public CLayer(final String layerName)
    {
        this(null, false);
        name = layerName;
    }

    /**
     * Cloning constructor
     * @param original the CLayer to clone
     */
    protected CLayer(final CLayer original, final boolean cloneData)
    {
        super();
        if (original != null)
        {
            this.name = original.getName();

            if (original.elements != null)
            {
                for (AComponent component : original.elements)
                {
                    switch (component)
                    {
                        case IVariableField variableField -> elements.add(variableField.clone(cloneData));
                        case IDynamicField dynamicField -> elements.add(dynamicField.clone(cloneData));
                        default -> elements.add(component.clone());
                    }
                }
            }
        }
    }

    @Override
    public CLayer clone() { return clone(false); }
    public CLayer clone(final boolean cloneData)
    {
        return new CLayer(this, cloneData);
    }
}
