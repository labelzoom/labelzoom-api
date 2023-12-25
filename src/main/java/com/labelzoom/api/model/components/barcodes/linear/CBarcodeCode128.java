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
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public class CBarcodeCode128 extends ALinearBarcode implements IBarcodeWithCheckDigit
{
    private boolean checkDigitEnabled = false;

    private char uccCaseMode = 'N';

    /**
     * Parameterless constructor
     */
    public CBarcodeCode128() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the CBarcodeCode128 to clone
     */
    protected CBarcodeCode128(final CBarcodeCode128 original, final boolean cloneData)
    {
        super(original, cloneData);
        if (original != null)
        {
            this.checkDigitEnabled = original.checkDigitEnabled;
            this.uccCaseMode = original.uccCaseMode;
        }
    }

    @Override
    public BarcodeStyle getBarcodeStyle() { return BarcodeStyle.Code128; }

    @Override
    public AComponent clone() { return clone(false); }
    @Override
    public AComponent clone(boolean cloneData)
    {
        return new CBarcodeCode128(this, cloneData);
    }
}
