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
public class CStaticText extends AFontComponent
{
    protected String text;

    /**
     * Parameterless constructor
     */
    public CStaticText() { this(null); }

    /**
     * Cloning constructor
     * @param original the CStaticText to clone
     */
    protected CStaticText(final CStaticText original)
    {
    	super(original);
        if (original != null)
        {
            text = original.text;
        }
    }
    
    @Override
    public String toString()
    {
    	return getText();
    }

	@Override
	public AComponent clone() {
		return new CStaticText(this);
	}
}
