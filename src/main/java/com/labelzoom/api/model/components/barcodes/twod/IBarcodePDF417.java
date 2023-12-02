package com.labelzoom.api.model.components.barcodes.twod;

public interface IBarcodePDF417 extends I2DBarcode
{
    int getRowHeight();
    void setRowHeight(int rowHeight);

    int getSecurityLevel();
    void setSecurityLevel(int securityLevel);

    int getColumns();
    void setColumns(int columns);

    int getRows();
    void setRows(int rows);

    boolean isTruncateRightRow();
    void setTruncateRightRow(boolean truncateRightRow);
}
