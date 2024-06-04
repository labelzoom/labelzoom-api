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
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public class CBarcodeDataMatrix extends A2DBarcode
{
	private int symbolHeight = 1; // 1 to width of label

	private int quality = 0; // 0, 50, 80, 100, 140, 200

	private int columns = 9; // 9 to 49

	private int rows = 9; // 9 to 49

	private int formatId = 6;

	private char escapeSequenceCharacter = '~';

	private int aspectRatio = 1;

	public CBarcodeDataMatrix() { this(null, false); }

	protected CBarcodeDataMatrix(final CBarcodeDataMatrix original, final boolean cloneData)
	{
		super(original, cloneData);
		if (original != null)
		{
			symbolHeight = original.symbolHeight;
			quality = original.quality;
			columns = original.columns;
			rows = original.rows;
			formatId = original.formatId;
			escapeSequenceCharacter = original.escapeSequenceCharacter;
			aspectRatio = original.aspectRatio;
		}
	}

	@Override
	public BarcodeStyle getBarcodeStyle() { return BarcodeStyle.DataMatrix; }

	@Override
	public AComponent clone() { return clone(false); }
	@Override
	public AComponent clone(boolean cloneData)
	{
		return new CBarcodeDataMatrix(this, cloneData);
	}
}
