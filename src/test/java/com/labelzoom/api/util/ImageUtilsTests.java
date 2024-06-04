package com.labelzoom.api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageUtilsTests
{
    private final float DEFAULT_TOLERANCE = 0.1f;

    @Test
    void testLabelZoomLogo_IsColorful() throws IOException
    {
        final BufferedImage image = ImageIO.read(new File("LabelZoom_Logo_f_400px.png"));
        assertTrue(ImageUtils.imageHasColorOrGray(image, DEFAULT_TOLERANCE));
    }

    @Test
    void testQrCode_IsNotColorful() throws IOException
    {
        final BufferedImage image = ImageIO.read(new File("src/test/resources/labelzoom-qr-code.png"));
        assertFalse(ImageUtils.imageHasColorOrGray(image, DEFAULT_TOLERANCE));
    }

    @Test
    void testQrCodeWithTransparentBackground_IsNotColorful() throws IOException
    {
        final BufferedImage image = ImageIO.read(new File("src/test/resources/labelzoom-qr-code-transparent.png"));
        assertFalse(ImageUtils.imageHasColorOrGray(image, DEFAULT_TOLERANCE));
    }

    @Test
    void testQrCodeGrayscale_IsColorful() throws IOException
    {
        final BufferedImage image = ImageIO.read(new File("src/test/resources/labelzoom-qr-code-grayscale.png"));
        assertTrue(ImageUtils.imageHasColorOrGray(image, DEFAULT_TOLERANCE));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "red-square-opaque.png",
            "green-square-opaque.png",
            "blue-square-opaque.png",
    })
    void testColorSquares_AreColorful(final String filename) throws IOException
    {
        final BufferedImage image = ImageIO.read(new File("src/test/resources/" + filename));
        assertTrue(ImageUtils.imageHasColorOrGray(image, DEFAULT_TOLERANCE));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "red-square-transparent.png",
            "green-square-transparent.png",
            "blue-square-transparent.png",
    })
    void testTransparentColorSquares_AreNotColorful(final String filename) throws IOException
    {
        final BufferedImage image = ImageIO.read(new File("src/test/resources/" + filename));
        assertFalse(ImageUtils.imageHasColorOrGray(image, DEFAULT_TOLERANCE));
    }
}
