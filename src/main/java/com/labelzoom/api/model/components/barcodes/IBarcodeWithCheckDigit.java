package com.labelzoom.api.model.components.barcodes;

/**
 * <b>***NOTE***</b> When adding additional fields to barcodes, the BarcodeDeserializer must also be updated
 */
public interface IBarcodeWithCheckDigit extends IBarcode
{
    void setCheckDigitEnabled(boolean value);

    boolean isCheckDigitEnabled();
}
