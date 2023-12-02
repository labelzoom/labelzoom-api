package com.labelzoom.api.model.components.barcodes.twod;

public interface IBarcodeDataMatrix extends I2DBarcode
{
    int getSymbolHeight();
    void setSymbolHeight(int symbolHeight);

    int getQuality();
    void setQuality(int quality);

    int getColumns();
    void setColumns(int columns);

    int getRows();
    void setRows(int rows);

    int getFormatId();
    void setFormatId(int formatId);

    char getEscapeSequenceCharacter();
    void setEscapeSequenceCharacter(char escapeSequenceCharacter);

    int getAspectRatio();
    void setAspectRatio(int aspectRatio);
}
