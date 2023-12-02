package com.labelzoom.api.model.components.barcodes.linear;

import com.labelzoom.api.model.BarcodeStyle;
import com.labelzoom.api.model.components.barcodes.IBarcode;

public interface ILinearBarcode extends IBarcode
{
    enum BarcodeSize
    {
        Smallest(1),
        Smaller(2),
        Normal(3),
        Large(4),
        XLarge(5),
        XXLarge(6),
        XXXLarge(7),
        XXXXLarge(8),
        XXXXXLarge(9);

        private int value;
        BarcodeSize(final int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    enum HumanReadableStyle
    {
        Above,
        Below
    }

    BarcodeSize getBarcodeSize();
    void setBarcodeSize(BarcodeSize size);

    int getHeight();
    void setHeight(int height);

    boolean isHumanReadableEnabled();
    void setHumanReadableEnabled();

    HumanReadableStyle getHumanReadablePosition();
    void setHumanReadablePosition(HumanReadableStyle position);
}
