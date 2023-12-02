package com.labelzoom.api.model;

public enum BarcodeStyle
{
    Unknown(-1),
    Aztec(0),
    Code11(1),
    Interleaved2of5(2),
    Code39(3),
    Code49(4),
    PlanetCode(5),
    PDF417(7),
    EAN_8(8),
    UPC_E(9),
    Code93(10),
    CODABLOCK(11),
    Code128(12),
    UPSMaxiCode(13),
    EAN_13(14),
    Micro_PDF417(15),
    Industrial2of5(18),
    Standard2of5(19),
    ANSICodabar(20),
    LOGMARS(21),
    MSI(22),
    Aztec2(24),
    Plessey(25),
    QRCode(26),
    GS1Databar(27),
    UPC_EAN(28),
    UPC_A(30),
    DataMatrix(33),
    PostNet(35);

    private int value;
    BarcodeStyle(final int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
