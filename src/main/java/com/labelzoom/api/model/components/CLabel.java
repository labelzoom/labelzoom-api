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

import java.util.*;

public class CLabel
{
    // Fields
    @Getter @Setter
    private int width;

    @Getter @Setter
    private int height;

    @Getter @Setter
    private boolean isHighRes = false;

    @Getter @Setter
    private PageOrientation orientation = PageOrientation.Portrait;

    @Getter @Setter
    private List<CLayer> layers = new ArrayList<>(); // Using ArrayList because that's what Jackson uses

    private final Map<String, CLayer> layerMap = new TreeMap<>();

    @Getter @Setter
    private CDataCommand dataCommand;

    @Getter @Setter
    private String schemaLocation;

    @Getter @Setter
    private String schemaVersion = "v1";

    @Getter @Setter
    private UUID id = UUID.randomUUID();

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
            width = original.getWidth();
            height = original.getHeight();
            isHighRes = original.isHighRes();
            orientation = original.getOrientation();
            if (original.getLayers() != null)
            {
                for (final CLayer layer : original.getLayers())
                {
                    final CLayer clone = layer.clone(cloneData);
                    layers.add(clone);
                    layerMap.put(clone.getName(), clone);
                }
            }
            else
            {
                layers = null;
                layerMap.clear();
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

    public void setElements(final List<AComponent> elements) { getLayerMap().get(DEFAULT_LAYER_NAME).setElements(elements); } // TODO: Multi-layer support
    public List<AComponent> getElements() { return getLayerMap().get(DEFAULT_LAYER_NAME).getElements(); } // TODO: Multi-layer support

    /**
     * Keeps the layer map in sync with the layer list. The underlying data structure of the layers must be a list
     * to be compatible with JAXB (returning Map.values() from getLayers() doesn't work because we need to return a
     * reference to a list object, not the abstract collection object. I'm under the impression that JAXB uses the
     * list reference returned from the getLayers() method to add or remove items)
     */
    private Map<String, CLayer> getLayerMap()
    {
        if (layers != null)
        {
            if (layers.size() != layerMap.size())
            {
                this.layerMap.clear();
                for (CLayer layer : layers)
                {
                    layerMap.put(layer.getName(), layer);
                }
            }
        }
        if (!layerMap.containsKey(DEFAULT_LAYER_NAME))
        {
            layerMap.put(DEFAULT_LAYER_NAME, new CLayer(DEFAULT_LAYER_NAME));
        }
        return layerMap;
    }

    public boolean isLandscape()
    {
        switch (this.getOrientation())
        {
            case Landscape:
            case ReverseLandscape:
                return true;
            default:
                return false;
        }
    }

    public boolean isInverted()
    {
        switch (this.getOrientation())
        {
            case ReversePortrait:
            case ReverseLandscape:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public CLabel clone() { return clone(false); }
    public CLabel clone(final boolean cloneData)
    {
        return new CLabel(this, cloneData);
    }

    public void addElement(final AComponent component)
    {
        if (layers.isEmpty())
        {
            final CLayer mainContainer = new CLayer();
            mainContainer.setName(DEFAULT_LAYER_NAME);
            layers.add(mainContainer);
        }
        layers.get(0).getElements().add(component);
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
