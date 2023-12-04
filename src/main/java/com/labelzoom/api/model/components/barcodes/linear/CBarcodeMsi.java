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
import com.labelzoom.api.model.components.barcodes.IBarcodeWithWideToNarrowRatio;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public class CBarcodeMsi extends ALinearBarcode implements IBarcodeWithWideToNarrowRatio
{
    private char checkDigit = 'B';

    private boolean checkDigitOnHumanReadable = false;

    private float wideToNarrowRatio = 3.0f;

    /**
     * Parameterless constructor
     */
    public CBarcodeMsi() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the CBarcodeMsi to clone
     */
    protected CBarcodeMsi(final CBarcodeMsi original, final boolean cloneData)
    {
        super(original, cloneData);
        barcodeStyle = BarcodeStyle.MSI;
        if (original != null)
        {
            this.checkDigit = original.checkDigit;
            this.checkDigitOnHumanReadable = original.checkDigitOnHumanReadable;
            this.wideToNarrowRatio = original.wideToNarrowRatio;
        }
    }

    @Override
    public AComponent clone() { return clone(false); }
    @Override
    public AComponent clone(boolean cloneData)
    {
        return new CBarcodeMsi(this, cloneData);
    }
}
