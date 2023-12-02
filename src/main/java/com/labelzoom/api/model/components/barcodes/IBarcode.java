package com.labelzoom.api.model.components.barcodes;

import com.labelzoom.api.model.BarcodeStyle;
import com.labelzoom.api.model.components.IComponent;
import com.labelzoom.api.model.components.IDynamicField;
import com.labelzoom.api.model.components.ISuppressible;

public interface IBarcode extends IComponent, IDynamicField, ISuppressible
{
    BarcodeStyle getBarcodeStyle();
    void setBarcodeStyle(BarcodeStyle barcodeStyle);
}
