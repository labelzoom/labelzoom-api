package com.labelzoom.api.services;

import com.labelzoom.api.model.components.barcodes.IBarcode;
import org.w3c.dom.Document;

import java.awt.image.BufferedImage;

public interface BarcodeRenderingService
{
    BufferedImage renderBarcodeToRaster(IBarcode barcode);

    Document renderBarcodeToVector(IBarcode barcode);
}
