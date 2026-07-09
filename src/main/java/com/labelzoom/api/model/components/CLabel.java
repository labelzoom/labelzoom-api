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

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class CLabel
{
    // Fields
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private int width;

    @Getter @Setter
    private int height;

    @Getter @Setter
    private int dpi = 100;

    @Getter @Setter
    private PageOrientation orientation = PageOrientation.Portrait;

    @Getter @Setter
    private List<CLayer> layers = new ArrayList<>(); // Using ArrayList because that's what Jackson uses

    @Getter @Setter
    private CDataCommand dataCommand;

    @Getter @Setter
    private String schemaLocation;

    @Getter @Setter
    private String schemaVersion = "v1";

    @Getter @Setter
    private UUID id = UUID.randomUUID();

    @Getter @Setter
    private float marginLeft = 0;

    @Getter @Setter
    private float marginTop = 0;

    @Getter @Setter
    private float marginRight = 0;

    @Getter @Setter
    private float marginBottom = 0;

    // Static/constants
    public static final String DEFAULT_LAYER_NAME = "background";

    @Getter
    public enum PageOrientation
    {
        Landscape        (1),
        Portrait         (2),
        ReverseLandscape (3),
        ReversePortrait  (4);

        private final int value;
        PageOrientation(final int value) {
            this.value = value;
        }
    }

    /**
     * Parameterless constructor
     */
    public CLabel() { this(null, false); }

    /**
     * Cloning constructor
     * @param original the CLabel to clone
     */
    protected CLabel(final CLabel original, final boolean cloneData)
    {
        super();
        if (original != null)
        {
            name = original.getName();
            description = original.getDescription();
            width = original.getWidth();
            height = original.getHeight();
            dpi = original.getDpi();
            orientation = original.getOrientation();
            marginLeft = original.marginLeft;
            marginTop = original.marginTop;
            marginRight = original.marginRight;
            marginBottom = original.marginBottom;
            if (original.getLayers() != null)
            {
                for (final CLayer layer : original.getLayers())
                {
                    final CLayer clone = layer.clone(cloneData);
                    layers.add(clone);
                }
            }
            if (original.getDataCommand() != null)
            {
                dataCommand = original.getDataCommand().clone();
            }
            schemaLocation = original.schemaLocation;
            schemaVersion = original.schemaVersion;
            id = original.id;
        }
    }

    public boolean isHighRes() { return dpi >= 1000; }
    public void setHighRes(final boolean isHighRes) { dpi = isHighRes ? 1000 : 100; }

    public void setElements(final List<AComponent> elements) { getOrCreateBackgroundLayer().setElements(elements); } // TODO: Multi-layer support
    public List<AComponent> getElements() { return getOrCreateBackgroundLayer().getElements(); } // TODO: Multi-layer support

    /**
     * Resolves the single "background" layer that {@link #getElements()}, {@link #setElements(List)} and
     * {@link #addElement(AComponent)} all operate on, creating it (and the backing layer list, if null) when it
     * does not yet exist. The {@code layers} list is the single source of truth: routing every element accessor
     * through this one method guarantees they always target the same {@link CLayer} instance regardless of the
     * order in which they are called.
     * <p>
     * This replaces an earlier map-based cache whose lazy {@code computeIfAbsent} materialized a phantom
     * background layer that lived only in the cache. Because {@code addElement} wrote into a separately created
     * layer in {@code layers}, any element added after a {@code getElements()} call on an otherwise-empty label
     * was silently dropped.
     */
    private CLayer getOrCreateBackgroundLayer()
    {
        if (layers == null)
        {
            layers = new ArrayList<>(); // Using ArrayList because that's what Jackson uses
        }
        for (final CLayer layer : layers)
        {
            if (DEFAULT_LAYER_NAME.equals(layer.getName()))
            {
                return layer;
            }
        }
        final CLayer background = new CLayer(DEFAULT_LAYER_NAME);
        layers.add(background);
        return background;
    }

    public boolean isLandscape()
    {
        return switch (this.getOrientation())
        {
            case Landscape, ReverseLandscape -> true;
            default -> false;
        };
    }

    public boolean isInverted()
    {
        return switch (this.getOrientation())
        {
            case ReversePortrait, ReverseLandscape -> true;
            default -> false;
        };
    }

    @Override
    public CLabel clone() { return clone(false); }
    public CLabel clone(final boolean cloneData)
    {
        return new CLabel(this, cloneData);
    }

    public void addElement(final AComponent component)
    {
        getOrCreateBackgroundLayer().getElements().add(component);
    }

    /**
     * In ZPL, labels are written top to bottom. In situations where there are overlapping fields (like when a reversed
     * field sits on top of a black background, we want to make sure the black background is written to the printer
     * first, then the reversed field is layered on top. This is very similar to how the Z-index was written in
     * labelzoom-studio, but slightly different (in labelzoom-studio, the largest (by area) elements are displayed
     * first, then the smaller elements are displayed on top)
     * @return
     */
    public List<AComponent> getSortedElements()
    {
        final List<AComponent> sortedElements = new LinkedList<>(this.getElements());
        sortedElements.sort(componentSorter);
        return sortedElements;
    }

    private final Comparator<AComponent> componentSorter = (c1, c2) -> {
        if (c1.getZIndex() != c2.getZIndex()) return Integer.compare(c1.getZIndex(), c2.getZIndex());
        if (c1.getLeft() != c2.getLeft()) return Integer.compare(c1.getLeft(), c2.getLeft());
        if (c1.getTop() != c2.getTop()) return Integer.compare(c1.getTop(), c2.getTop());
        if (c1.isReverse() && !c2.isReverse()) return 1;
        else if (!c1.isReverse() && c2.isReverse()) return -1;
        return Integer.compare(this.getElements().indexOf(c1), this.getElements().indexOf(c2));
    };
}
