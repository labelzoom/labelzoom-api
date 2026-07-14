package com.labelzoom.api.model;

/* Copyright (C) 2026, LabelZoom, a subsidiary of RJF Technology Solutions LLC.
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

/**
 * Ink color of a line/box component. White is not a no-op: label languages use white shapes to erase
 * (paint over) previously drawn content, e.g. ZPL's <code>^GB...,W</code>.
 */
public enum LineColor
{
    Black,
    White
}
