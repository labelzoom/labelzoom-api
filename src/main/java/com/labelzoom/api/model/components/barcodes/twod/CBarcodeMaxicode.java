package com.labelzoom.api.model.components.barcodes.twod;

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
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public class CBarcodeMaxicode extends A2DBarcode
{
	/* 2 = structured carrier message: numeric postal code (U.S.)
	 * 3 = structured carrier message: alphanumeric postal code (non-U.S.)
	 * 4 = standard symbol, secretary
	 * 5 = full EEC
	 * 6 = reader program, secretary
	 */
	private int mode = 2; // 1 (original) and 2 (enhanced – recommended)

	private int symbolNumber = 1; // 1 to 8 can be added in a structured document

	private int totalSymbols = 1; // 1 to 8, representing the total number of symbols in this sequence

	public CBarcodeMaxicode() { this(null, false); }

	protected CBarcodeMaxicode(final CBarcodeMaxicode original, final boolean cloneData)
	{
		super(original, cloneData);
		barcodeStyle = BarcodeStyle.UPSMaxiCode;
		rotation = 0; // ZPL doesn't support rotating QR codes
		if (original != null)
		{
			mode = original.mode;
			symbolNumber = original.symbolNumber;
			totalSymbols = original.totalSymbols;
		}
	}

	@Override
	public AComponent clone() { return clone(false); }
	@Override
	public AComponent clone(boolean cloneData)
	{
		return new CBarcodeMaxicode(this, cloneData);
	}
}
