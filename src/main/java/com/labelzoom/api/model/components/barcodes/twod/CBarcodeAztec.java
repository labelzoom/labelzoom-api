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
public class CBarcodeAztec extends A2DBarcode
{
	private int magnificationFactor = 2; // 1 on 150 dpi printers, 2 on 200 dpi printers, 3 on 300 dpi printers, 6 on 600 dpi printers

	private boolean ecicIndicator = false;

	private int errorControlIndicator = 0;

	private boolean menuSymbolIndicator = false;

	private int numberOfSymbolsForStructuredAppend = 1;

	private String idFieldForStructuredAppend = "";

	public CBarcodeAztec() { this(null, false); }

	protected CBarcodeAztec(final CBarcodeAztec original, final boolean cloneData)
	{
		super(original, cloneData);
		barcodeStyle = BarcodeStyle.Aztec;
		if (original != null)
		{
			rotation = original.rotation;
			magnificationFactor = original.magnificationFactor;
			ecicIndicator = original.ecicIndicator;
			errorControlIndicator = original.errorControlIndicator;
			menuSymbolIndicator = original.menuSymbolIndicator;
			numberOfSymbolsForStructuredAppend = original.numberOfSymbolsForStructuredAppend;
			idFieldForStructuredAppend = original.idFieldForStructuredAppend;
		}
	}

	@Override
	public AComponent clone() { return clone(false); }
	@Override
	public AComponent clone(boolean cloneData)
	{
		return new CBarcodeAztec(this, cloneData);
	}
}
