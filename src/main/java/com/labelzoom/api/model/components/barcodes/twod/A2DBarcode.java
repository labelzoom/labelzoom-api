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

import com.labelzoom.api.model.components.barcodes.ABarcode;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
public abstract class A2DBarcode extends ABarcode
{
	public A2DBarcode() { this(null, false); }

	protected A2DBarcode(final A2DBarcode original, final boolean cloneData)
	{
		super(original, cloneData);
	}
}