package com.labelzoom.api.model.components.barcodes.linear;

import com.labelzoom.api.model.components.barcodes.IBarcodeWithWideToNarrowRatio;

public interface IBarcodeMsi extends ILinearBarcode, IBarcodeWithWideToNarrowRatio
{
    char getCheckDigit();
    void setCheckDigit(char checkDigit);

    boolean isCheckDigitOnHumanReadable();
    void setCheckDigitOnHumanReadable(boolean checkDigitOnHumanReadable);
}
