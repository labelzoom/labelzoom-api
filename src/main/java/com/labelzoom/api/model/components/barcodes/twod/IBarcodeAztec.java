package com.labelzoom.api.model.components.barcodes.twod;

public interface IBarcodeAztec extends I2DBarcode
{
    int getMagnificationFactor();
    void setMagnificationFactor(int magnificationFactor);

    boolean isEcicIndicator();
    void setEcicIndicator(boolean ecicIndicator);

    int getErrorControlIndicator();
    void setErrorControlIndicator(int errorControlIndicator);

    boolean isMenuSymbolIndicator();
    void setMenuSymbolIndicator(boolean menuSymbolIndicator);

    int getNumberOfSymbolsForStructuredAppend();
    void setNumberOfSymbolsForStructuredAppend(int numberOfSymbolsForStructuredAppend);

    String getIdFieldForStructuredAppend();
    void setIdFieldForStructuredAppend(String idFieldForStructuredAppend);
}
