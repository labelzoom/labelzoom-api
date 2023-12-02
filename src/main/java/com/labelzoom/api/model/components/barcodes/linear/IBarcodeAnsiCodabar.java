package com.labelzoom.api.model.components.barcodes.linear;

import com.labelzoom.api.model.components.barcodes.IBarcodeWithCheckDigit;
import com.labelzoom.api.model.components.barcodes.IBarcodeWithWideToNarrowRatio;

public interface IBarcodeAnsiCodabar extends ILinearBarcode, IBarcodeWithCheckDigit, IBarcodeWithWideToNarrowRatio
{
    char getStartCharacter();
    void setStartCharacter(char character);

    char getStopCharacter();
    void setStopCharacter(char character);
}
