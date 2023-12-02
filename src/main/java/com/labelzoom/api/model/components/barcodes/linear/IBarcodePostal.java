package com.labelzoom.api.model.components.barcodes.linear;

import com.labelzoom.api.model.components.barcodes.IBarcodeWithWideToNarrowRatio;

public interface IBarcodePostal extends ILinearBarcode, IBarcodeWithWideToNarrowRatio
{
    char getPostalCodeType();
    void setPostalCodeType(char postalCodeType);
}
