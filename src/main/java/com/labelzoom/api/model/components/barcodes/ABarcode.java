package com.labelzoom.api.model.components.barcodes;

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
import com.labelzoom.api.model.components.IDynamicField;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public abstract class ABarcode extends AComponent implements IDynamicField
{
    private String expression;

    private String fieldValue;

    private boolean blankWhenNull = false;

    @Getter
    public enum BarcodeStyle
    {
        Unknown(-1),
        Aztec(0),
        Code11(1),
        Interleaved2of5(2),
        Code39(3),
        Code49(4),
        PlanetCode(5),
        PDF417(7),
        EAN_8(8),
        UPC_E(9),
        Code93(10),
        CODABLOCK(11),
        Code128(12),
        UPSMaxiCode(13),
        EAN_13(14),
        Micro_PDF417(15),
        Industrial2of5(18),
        Standard2of5(19),
        ANSICodabar(20),
        LOGMARS(21),
        MSI(22),
        Aztec2(24),
        Plessey(25),
        QRCode(26),
        GS1Databar(27),
        UPC_EAN(28),
        UPC_A(30),
        DataMatrix(33),
        PostNet(35);

        private final int value;
        BarcodeStyle(final int value) {
            this.value = value;
        }
    }

    /**
     * Parameterless constructor
     */
    protected ABarcode() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the ABarcode to clone
     */
    protected ABarcode(final ABarcode original, final boolean cloneData)
    {
        super(original);
        if (original != null)
        {
            expression = original.expression;
            blankWhenNull = original.blankWhenNull;
            if (cloneData)
            {
                fieldValue = original.fieldValue;
            }
        }
    }

    public abstract BarcodeStyle getBarcodeStyle();

    @Override
    public String toString() { return getFieldValue(); }

    @Override
    public int getZIndex() { return 1; }
}
