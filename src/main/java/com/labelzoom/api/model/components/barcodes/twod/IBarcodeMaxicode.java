package com.labelzoom.api.model.components.barcodes.twod;

public interface IBarcodeMaxicode extends I2DBarcode
{
    int getMode();
    void setMode(int mode);

    int getSymbolNumber();
    void setSymbolNumber(int symbolNumber);

    int getTotalSymbols();
    void setTotalSymbols(int totalSymbols);
}
