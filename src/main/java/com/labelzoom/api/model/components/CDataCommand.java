package com.labelzoom.api.model.components;

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

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CDataCommand implements Cloneable
{
	private String language;

	private String dataCommand;

	/**
	 * Parameterless constructor
	 */
	public CDataCommand() { this(null); }

	/**
	 * Cloning constructor
	 * @param original the CDataCommand to clone
	 */
	protected CDataCommand(final CDataCommand original)
	{
		super();
		if (original != null)
		{
			language = original.language;
			dataCommand = original.dataCommand;
		}
	}
	
	@Override
	public CDataCommand clone()
	{
		return new CDataCommand(this);
	}
}
