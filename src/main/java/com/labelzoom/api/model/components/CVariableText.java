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
public class CVariableText extends AFontComponent implements IDynamicField
{
    private String expression;

    private String fieldValue;

    private boolean autoSize = true;

    private int width = 0;

    private int height = 0;

    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.Left;

    public enum HorizontalAlignment
    {
        Left, Center, Right
    }

    /**
     * Parameterless constructor
     */
    public CVariableText() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the CVariableText to clone
     */
    protected CVariableText(final CVariableText original, final boolean cloneData)
    {
    	super(original);
        if (original != null)
        {
            expression = original.expression;
            autoSize = original.autoSize;
            width = original.width;
            height = original.height;
            horizontalAlignment = original.horizontalAlignment;
            if (cloneData)
            {
                fieldValue = original.fieldValue;
            }
        }
    }
    
    @Override
    public String toString()
    {
    	return getFieldValue();
    }

	@Override
	public AComponent clone() { return clone(false); }
	public AComponent clone(final boolean cloneData)
    {
        return new CVariableText(this, cloneData);
    }
}
