package com.labelzoom.api.model.components.barcodes.linear;

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

import com.labelzoom.api.model.components.AComponent;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
public class CBarcodePlanetCode extends ALinearBarcode
{
    /**
     * Parameterless constructor
     */
    public CBarcodePlanetCode() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the CBarcodePlanetCode to clone
     */
    protected CBarcodePlanetCode(final CBarcodePlanetCode original, final boolean cloneData)
    {
        super(original, cloneData);
        barcodeStyle = BarcodeStyle.PlanetCode;
    }

    @Override
    public AComponent clone() { return clone(false); }
    @Override
    public AComponent clone(boolean cloneData)
    {
        return new CBarcodePlanetCode(this, cloneData);
    }
}
