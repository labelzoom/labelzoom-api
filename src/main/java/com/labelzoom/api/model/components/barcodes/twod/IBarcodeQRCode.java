package com.labelzoom.api.model.components.barcodes.twod;

public interface IBarcodeQRCode extends I2DBarcode
{
    int getModel();
    void setModel(int model);

    int getMagnificationFactor();
    void setMagnificationFactor(int magnificationFactor);

    char getErrorCorrection();
    void setErrorCorrection(char errorCorrection);

    int getMaskValue();
    void setMaskValue(int maskValue);
}
