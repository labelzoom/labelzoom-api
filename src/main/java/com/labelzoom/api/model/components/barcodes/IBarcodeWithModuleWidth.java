package com.labelzoom.api.model.components.barcodes;

import lombok.Getter;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
public interface IBarcodeWithModuleWidth
{
    @Getter
    enum ZebraBarcodeSize
    {
        Smallest(1),
        Smaller(2),
        Normal(3),
        Large(4),
        XLarge(5),
        XXLarge(6),
        XXXLarge(7),
        XXXXLarge(8),
        XXXXXLarge(9);

        private final int value;

        ZebraBarcodeSize(final int value) {
            this.value = value;
        }
    }

    ZebraBarcodeSize getBarcodeSize();
    void setBarcodeSize(ZebraBarcodeSize size);

    default float getModuleWidthInMm(final int dpi)
    {
        return (float)this.getBarcodeSize().getValue() / (float)dpi * 25.4f;
    }
}
