package com.labelzoom.api.model.components;

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

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CLabelTests
{
    private static CStaticText text(final String value)
    {
        final CStaticText t = new CStaticText();
        t.setText(value);
        return t;
    }

    /**
     * Regression test for the phantom-background-layer bug: calling getElements() on a not-yet-populated
     * label used to materialize a phantom default layer that then shadowed the layer addElement() wrote
     * into, silently dropping anything added afterward. getElements() must observe content added after it.
     */
    @Test
    void getElementsBeforeAddElementDoesNotDropContent()
    {
        final CLabel label = new CLabel();

        // Probe the (empty) label first, exactly as the EPL reader did.
        assertTrue(label.getElements().isEmpty());

        final CStaticText component = text("hello");
        label.addElement(component);

        assertEquals(1, label.getElements().size());
        assertSame(component, label.getElements().get(0));
    }

    @Test
    void addElementThenGetElementsStillWorks()
    {
        final CLabel label = new CLabel();
        final CStaticText component = text("hello");
        label.addElement(component);

        assertEquals(1, label.getElements().size());
        assertSame(component, label.getElements().get(0));
    }

    /**
     * getElements() must be idempotent: repeated calls (in any order relative to addElement) resolve the
     * same background layer rather than manufacturing a fresh one each time.
     */
    @Test
    void getElementsIsIdempotentAndCreatesAtMostOneBackgroundLayer()
    {
        final CLabel label = new CLabel();

        final List<AComponent> first = label.getElements();
        final List<AComponent> second = label.getElements();
        assertSame(first, second);

        label.addElement(text("a"));
        label.getElements();

        assertEquals(1, label.getLayers().size());
        assertEquals(CLabel.DEFAULT_LAYER_NAME, label.getLayers().get(0).getName());
    }

    /**
     * The list returned by getElements() must be the live, mutable backing list, so that direct mutation
     * (as several engine callers do, e.g. getElements().add(...)) actually persists on the label.
     */
    @Test
    void getElementsReturnsLiveMutableList()
    {
        final CLabel label = new CLabel();
        final CStaticText component = text("live");

        label.getElements().add(component);

        assertEquals(1, label.getElements().size());
        assertSame(component, label.getElements().get(0));
    }

    /**
     * getElements() and addElement() must resolve the same background layer as the one exposed through
     * getLayers(), regardless of the order in which they are first touched.
     */
    @Test
    void elementsAndLayersShareTheSameBackgroundLayer()
    {
        final CLabel label = new CLabel();
        label.getElements(); // touch the accessor first
        label.addElement(text("x"));

        final CLayer background = label.getLayers().get(0);
        assertSame(background.getElements(), label.getElements());
        assertEquals(1, background.getElements().size());
    }

    @Test
    void setElementsBeforeAddElementIsPreserved()
    {
        final CLabel label = new CLabel();
        final List<AComponent> seed = new ArrayList<>();
        seed.add(text("seed"));
        label.setElements(seed);

        label.addElement(text("added"));

        assertEquals(2, label.getElements().size());
    }

    /**
     * layers may legitimately be null at runtime (e.g. Jackson maps an empty {@code <layers />} element to
     * null). The element accessors must recreate the backing list rather than throwing.
     */
    @Test
    void addElementRecoversFromNullLayers()
    {
        final CLabel label = new CLabel();
        label.setLayers(null);

        assertDoesNotThrow(() -> label.addElement(text("recovered")));
        assertEquals(1, label.getElements().size());
    }
}
