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
import com.labelzoom.api.model.components.barcodes.IBarcodeWithCheckDigit;
import com.labelzoom.api.model.components.barcodes.IBarcodeWithWideToNarrowRatio;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public class CBarcodeInterleaved2of5 extends ALinearBarcode implements IBarcodeWithCheckDigit, IBarcodeWithWideToNarrowRatio
{
    private boolean checkDigitEnabled = false;

    private float wideToNarrowRatio = 3.0f;

    /**
     * Parameterless constructor
     */
    public CBarcodeInterleaved2of5() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the CBarcodeInterleaved2of5 to clone
     */
    protected CBarcodeInterleaved2of5(final CBarcodeInterleaved2of5 original, final boolean cloneData)
    {
        super(original, cloneData);
        setBarcodeStyle(BarcodeStyle.Interleaved2of5);
        if (original != null)
        {
            this.checkDigitEnabled = original.checkDigitEnabled;
            this.wideToNarrowRatio = original.wideToNarrowRatio;
        }
    }

    @Override
    public AComponent clone() { return clone(false); }
    @Override
    public AComponent clone(boolean cloneData)
    {
        return new CBarcodeInterleaved2of5(this, cloneData);
    }
}
