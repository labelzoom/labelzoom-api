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

import com.labelzoom.api.model.components.barcodes.ABarcode;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
@Getter @Setter
public abstract class ALinearBarcode extends ABarcode
{
	private ZebraBarcodeSize barcodeSize;

	private int height;

	private boolean humanReadableEnabled;

	private HumanReadableStyle humanReadablePosition;

	@Getter
	public enum ZebraBarcodeSize
	{
		Smallest(1),
		Smaller(2),
		Normal(3),
		Large(4),
		XLarge(5),
		XXLarge(6),
		XXXLarge(7),
		XXXXLarge(8),
		XXXXXLarge(9);

		private final int value;
		ZebraBarcodeSize(final int value) {
			this.value = value;
		}
	}

	public enum HumanReadableStyle
	{
		Above,
		Below
	}

	/**
	 * Parameterless constructor
	 */
	public ALinearBarcode() { this(null, false); }

	/**
	 * Cloning constructor
	 * @param original the ALinearBarcode to clone
	 */
	protected ALinearBarcode(final ALinearBarcode original, final boolean cloneData)
	{
		super(original, cloneData);
		if (original != null)
		{
			barcodeSize = original.barcodeSize;
			height = original.height;
			humanReadableEnabled = original.humanReadableEnabled;
			humanReadablePosition = original.humanReadablePosition;
		}
	}

	public ZebraBarcodeSize getBarcodeSize()
	{
		if (this.barcodeSize != null)
		{
			return this.barcodeSize;
		}
		return ZebraBarcodeSize.Normal;
	}

	public HumanReadableStyle getHumanReadablePosition()
	{
		if (this.humanReadablePosition != null)
		{
			return this.humanReadablePosition;
		}
		return HumanReadableStyle.Below;
	}
}
