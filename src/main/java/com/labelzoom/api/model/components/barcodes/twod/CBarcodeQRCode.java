package com.labelzoom.api.model.components.barcodes.twod;

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
import com.labelzoom.api.model.components.barcodes.ABarcode;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public class CBarcodeQRCode extends A2DBarcode
{
	private int model = 2; // 1 (original) and 2 (enhanced – recommended)

	private int magnificationFactor = 2; // 1 on 150 dpi printers, 2 on 200 dpi printers, 3 on 300 dpi printers, 6 on 600 dpi printers

	private char errorCorrection = 'Q'; // H = ultra-high reliability level, Q = high reliability level, M = standard level, L = high density leve

	private int maskValue = 7; // 0 to 7

	public CBarcodeQRCode() { this(null, false); }

	protected CBarcodeQRCode(final CBarcodeQRCode original, final boolean cloneData)
	{
		super(original, cloneData);
		if (original != null)
		{
			model = original.model;
			magnificationFactor = original.magnificationFactor;
			errorCorrection = original.errorCorrection;
			maskValue = original.maskValue;
		}
	}

	@Override
	public float getRotation() { return 0; } // ZPL doesn't support rotating QR codes

	@Override
	public BarcodeStyle getBarcodeStyle() { return BarcodeStyle.QRCode; }

	@Override
	public AComponent clone() { return clone(false); }
	@Override
	public AComponent clone(boolean cloneData)
	{
		return new CBarcodeQRCode(this, cloneData);
	}
}
