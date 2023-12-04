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
public class CBarcodeAnsiCodabar extends ALinearBarcode implements IBarcodeWithCheckDigit, IBarcodeWithWideToNarrowRatio
{
    protected boolean checkDigitEnabled = false;

    protected char startCharacter = 'A';

    protected char stopCharacter = 'A';

    protected float wideToNarrowRatio = 3.0f;

    /**
     * Parameterless constructor
     */
    public CBarcodeAnsiCodabar() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the CBarcodeAnsiCodabar to clone
     */
    protected CBarcodeAnsiCodabar(final CBarcodeAnsiCodabar original, final boolean cloneData)
    {
        super(original, cloneData);
        barcodeStyle = BarcodeStyle.ANSICodabar;
        if (original != null)
        {
            this.checkDigitEnabled = original.checkDigitEnabled;
            this.startCharacter = original.startCharacter;
            this.stopCharacter = original.stopCharacter;
            this.wideToNarrowRatio = original.wideToNarrowRatio;
        }
    }

    @Override
    public AComponent clone() { return clone(false); }
    @Override
    public AComponent clone(boolean cloneData)
    {
        return new CBarcodeAnsiCodabar(this, cloneData);
    }
}
