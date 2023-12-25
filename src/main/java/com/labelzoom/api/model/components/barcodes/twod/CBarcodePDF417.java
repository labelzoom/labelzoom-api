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
import com.labelzoom.api.model.components.barcodes.IBarcodeWithModuleWidth;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public class CBarcodePDF417 extends A2DBarcode implements IBarcodeWithModuleWidth
{
	private int rowHeight = 1;

	private int securityLevel = 0;

	private int columns = 1;

	private int rows = 3;

	private boolean truncateRightRow = false;

	private ZebraBarcodeSize barcodeSize = ZebraBarcodeSize.Normal;

	public CBarcodePDF417() { this(null, false); }

	protected CBarcodePDF417(final CBarcodePDF417 original, final boolean cloneData)
	{
		super(original, cloneData);
		setBarcodeStyle(BarcodeStyle.PDF417);
		if (original != null)
		{
			rowHeight = original.rowHeight;
			securityLevel = original.securityLevel;
			columns = original.columns;
			rows = original.rows;
			truncateRightRow = original.truncateRightRow;
			barcodeSize = original.barcodeSize;
		}
	}

	@Override
	public AComponent clone() { return clone(false); }
	@Override
	public AComponent clone(boolean cloneData)
	{
		return new CBarcodePDF417(this, cloneData);
	}
}
