package com.labelzoom.api.model;

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
    public int getValue() {
        return value;
    }
}
