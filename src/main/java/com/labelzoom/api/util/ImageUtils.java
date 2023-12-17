package com.labelzoom.api.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImageUtils
{
    private static final float ERROR_ONE_SIXTEENTH = 1f/16f;
    private static final float ERROR_THREE_SIXTEENTHS = 3f/16f;
    private static final float ERROR_FIVE_SIXTEENTHS = 5f/16f;
    private static final float ERROR_SEVEN_SIXTEENTHS = 7f/16f;
    private static final int BLACK = Color.BLACK.getRGB();
    private static final int WHITE = Color.WHITE.getRGB();

    /**
     * Thanks, ChatGPT!
     * @param image the source image
     * @param luminanceThreshold luminance threshold
     * @return the dithered 2-color image
     */
    public static BufferedImage desaturateWithDithering(final BufferedImage image, int luminanceThreshold)
    {
        // Clone original image
        final ColorModel cm = image.getColorModel();
        final boolean isAlphaPreMultiplied = cm.isAlphaPremultiplied();
        final WritableRaster raster = image.copyData(null);
        final BufferedImage out = new BufferedImage(cm, raster, isAlphaPreMultiplied, null);

        // Apply dithering
        final int width = out.getWidth();
        final int height = out.getHeight();

        // Two colors for dithering, typically black and white

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int pixel = out.getRGB(x, y);
                final int alpha = (pixel >> 24) & 0xff;
                final int red = (pixel >> 16) & 0xff;
                final int green = (pixel >> 8) & 0xff;
                final int blue = pixel & 0xff;

                // Convert to grayscale (or use other method to choose between color1 and color2)
                final int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue);
                final int newPixel = gray < luminanceThreshold ? BLACK : WHITE;

                final int error = gray - ((newPixel == BLACK) ? 0 : 255);

                // Distribute the error to neighboring pixels
                if (x < width - 1) out.setRGB(x + 1, y, applyError(out.getRGB(x + 1, y), Math.round(error * ERROR_SEVEN_SIXTEENTHS)));
                if (x > 0 && y < height - 1) out.setRGB(x - 1, y + 1, applyError(out.getRGB(x - 1, y + 1), Math.round(error * ERROR_THREE_SIXTEENTHS)));
                if (y < height - 1) out.setRGB(x, y + 1, applyError(out.getRGB(x, y + 1), Math.round(error * ERROR_FIVE_SIXTEENTHS)));
                if (x < width - 1 && y < height - 1) out.setRGB(x + 1, y + 1, applyError(out.getRGB(x + 1, y + 1), Math.round(error * ERROR_ONE_SIXTEENTH)));

                out.setRGB(x, y, newPixel | (alpha << 24));
            }
        }
        return out;
    }

    private static int applyError(int pixel, int error) {
        int red = Math.min(Math.max(((pixel >> 16) & 0xff) + error, 0), 255);
        int green = Math.min(Math.max(((pixel >> 8) & 0xff) + error, 0), 255);
        int blue = Math.min(Math.max((pixel & 0xff) + error, 0), 255);
        return (pixel & 0xff000000) | (red << 16) | (green << 8) | blue;
    }

    public static BufferedImage desaturateWithHardCut(final BufferedImage image, final int luminanceThreshold, final int alphaThreshold)
    {
        final BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                final Color pixelColor = new Color(image.getRGB(x, y), true);
                final float luminance = HSLColor.fromRGB(pixelColor)[2];
                if (pixelColor.getAlpha() >= alphaThreshold && luminance <= luminanceThreshold)
                {
                    out.setRGB(x, y, BLACK);
                }
                else
                {
                    out.setRGB(x, y, WHITE);
                }
            }
        }
        return out;
    }
}
