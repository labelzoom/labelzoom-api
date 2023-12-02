package com.labelzoom.api.model.components.barcodes.linear;

import com.labelzoom.api.model.components.barcodes.IBarcodeWithCheckDigit;

public interface IBarcodeCode128 extends ILinearBarcode, IBarcodeWithCheckDigit
{
    char getUccCaseMode();
    void setUccCaseMode(char uccCaseMode);
}
