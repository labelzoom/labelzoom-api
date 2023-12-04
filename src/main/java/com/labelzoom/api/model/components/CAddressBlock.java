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
public class CAddressBlock extends AFontComponent
{
    protected boolean useLocalizedAddressFormat = true;

    protected int staticAddressFormat = 1;

    protected boolean showContactName = false;

    protected boolean showCountry = false;

    protected String addressId;

    /**
     * Parameterless constructor
     */
	public CAddressBlock() { this(null); }

    /**
     * Cloning constructor
     * @param original the CAddressBlock to clone
     */
	protected CAddressBlock(final CAddressBlock original)
    {
    	super(original);
        if (original != null)
        {
            useLocalizedAddressFormat = original.useLocalizedAddressFormat;
            staticAddressFormat = original.staticAddressFormat;
            showContactName = original.showContactName;
            showCountry = original.showCountry;
            addressId = original.addressId;
        }
    }

	@Override
	public AComponent clone() {
		return new CAddressBlock(this);
	}

    /**
     * We want to return null to signify an empty value, because Address Blocks will always be converted to a series of
     * Static Text objects when filling the label with data. If this conversion doesn't happen, we want to print "null"
     * @return
     */
    @Override
    public String toString()
    {
        return null;
    }
}
