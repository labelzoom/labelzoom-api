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
        XXXXXLarge(9),
        /* ZPL's ^BY accepts a module width of up to 10, and real-world printer output honours even larger
         * out-of-spec values rather than clamping, so the scale continues past the original 9.
         */
        Width10(10),
        Width11(11),
        Width12(12),
        Width13(13),
        Width14(14),
        Width15(15);

        private final int value;

        ZebraBarcodeSize(final int value) {
            this.value = value;
        }
    }

    ZebraBarcodeSize getBarcodeSize();
    void setBarcodeSize(ZebraBarcodeSize size);

    default float getModuleWidthInMm(final int dpi)
    {
        return (float)this.getBarcodeSize().getValue() / dpi * 25.4f;
    }
}
