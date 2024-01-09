package com.labelzoom.api.model.components.barcodes.linear;

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
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public class CBarcodeStandard2of5 extends ALinearBarcode
{
    private float wideToNarrowRatio = 3.0f;

    /**
     * Parameterless constructor
     */
    public CBarcodeStandard2of5() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the CBarcodeStandard2of5 to clone
     */
    protected CBarcodeStandard2of5(final CBarcodeStandard2of5 original, final boolean cloneData)
    {
        super(original, cloneData);
        if (original != null)
        {
            this.wideToNarrowRatio = original.wideToNarrowRatio;
        }
    }

    @Override
    public BarcodeStyle getBarcodeStyle() { return BarcodeStyle.Standard2of5; }

    @Override
    public AComponent clone() { return clone(false); }
    @Override
    public AComponent clone(boolean cloneData)
    {
        return new CBarcodeStandard2of5(this, cloneData);
    }
}
