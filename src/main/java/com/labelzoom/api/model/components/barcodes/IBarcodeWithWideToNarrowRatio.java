package com.labelzoom.api.model.components.barcodes;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
public interface IBarcodeWithWideToNarrowRatio
{
    void setWideToNarrowRatio(float value);

    float getWideToNarrowRatio();
}
