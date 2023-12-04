package com.labelzoom.api.services;

import com.labelzoom.api.model.components.barcodes.ABarcode;
import org.w3c.dom.Document;

import java.awt.image.BufferedImage;

public interface BarcodeRenderingService
{
    BufferedImage renderBarcodeToRaster(ABarcode barcode);

    Document renderBarcodeToVector(ABarcode barcode);
}
