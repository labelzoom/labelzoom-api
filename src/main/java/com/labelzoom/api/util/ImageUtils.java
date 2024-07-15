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
    private static final int TRANSPARENT = new Color(255, 255, 255, 0).getRGB();

    private ImageUtils() {}

    public static BufferedImage cloneImage(final BufferedImage image)
    {
        // Clone original image
        final ColorModel cm = image.getColorModel();
        final boolean isAlphaPreMultiplied = cm.isAlphaPremultiplied();
        final WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPreMultiplied, null);
    }

    /**
     * Thanks, ChatGPT!
     * @param image the image to desaturate
     * @param luminanceThreshold luminance threshold, 0.0 to 1.0
     * @param alphaThreshold alpha threshold, 0.0 to 1.0
     */
    public static void desaturateWithDithering(final BufferedImage image, final float luminanceThreshold, final float alphaThreshold)
    {
        if (luminanceThreshold < 0 || luminanceThreshold > 1) throw new IllegalArgumentException("luminanceThreshold must be between 0.0 and 1.0 (inclusive)");
        if (alphaThreshold < 0 || alphaThreshold > 1) throw new IllegalArgumentException("alphaThreshold must be between 0.0 and 1.0 (inclusive)");
        final int luminanceByteThreshold = Math.round(255 * luminanceThreshold);
        final int alphaByteThreshold = Math.round(255 * alphaThreshold);

        // Apply dithering
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int pixel = image.getRGB(x, y);
                final int alpha = (pixel >> 24) & 0xff;
                final int red = (pixel >> 16) & 0xff;
                final int green = (pixel >> 8) & 0xff;
                final int blue = pixel & 0xff;

                // Convert to grayscale (or use other method to choose between color1 and color2)
                final int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue);
                final int newPixel;
                if (alpha >= alphaByteThreshold)
                {
                     newPixel = gray <= luminanceByteThreshold ? BLACK : WHITE;
                }
                else
                {
                    newPixel = TRANSPARENT;
                }

                final int error = gray - ((newPixel == BLACK) ? 0 : 255);

                // Distribute the error to neighboring pixels
                if (x < width - 1) image.setRGB(x + 1, y, applyError(image.getRGB(x + 1, y), Math.round(error * ERROR_SEVEN_SIXTEENTHS)));
                if (x > 0 && y < height - 1) image.setRGB(x - 1, y + 1, applyError(image.getRGB(x - 1, y + 1), Math.round(error * ERROR_THREE_SIXTEENTHS)));
                if (y < height - 1) image.setRGB(x, y + 1, applyError(image.getRGB(x, y + 1), Math.round(error * ERROR_FIVE_SIXTEENTHS)));
                if (x < width - 1 && y < height - 1) image.setRGB(x + 1, y + 1, applyError(image.getRGB(x + 1, y + 1), Math.round(error * ERROR_ONE_SIXTEENTH)));

                image.setRGB(x, y, newPixel);
            }
        }
    }

    private static int applyError(int pixel, int error) {
        int red = Math.min(Math.max(((pixel >> 16) & 0xff) + error, 0), 255);
        int green = Math.min(Math.max(((pixel >> 8) & 0xff) + error, 0), 255);
        int blue = Math.min(Math.max((pixel & 0xff) + error, 0), 255);
        return (pixel & 0xff000000) | (red << 16) | (green << 8) | blue;
    }

    /**
     *
     * @param image the image to desaturate
     * @param luminanceThreshold luminance threshold, 0.0 to 1.0
     * @param alphaThreshold alpha threshold, 0.0 to 1.0
     */
    public static void desaturateWithHardCut(final BufferedImage image, final float luminanceThreshold, final float alphaThreshold)
    {
        if (luminanceThreshold < 0 || luminanceThreshold > 1) throw new IllegalArgumentException("luminanceThreshold must be between 0.0 and 1.0 (inclusive)");
        if (alphaThreshold < 0 || alphaThreshold > 1) throw new IllegalArgumentException("alphaThreshold must be between 0.0 and 1.0 (inclusive)");
        final int luminanceByteThreshold = Math.round(100 * luminanceThreshold);
        final int alphaByteThreshold = Math.round(255 * alphaThreshold);

        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                final Color pixelColor = new Color(image.getRGB(x, y), true);
                final float luminance = HSLColor.fromRGB(pixelColor)[2];
                if (pixelColor.getAlpha() >= alphaByteThreshold)
                {
                    image.setRGB(x, y, luminance <= luminanceByteThreshold ? BLACK : WHITE);
                }
                else
                {
                    image.setRGB(x, y, TRANSPARENT);
                }
            }
        }
    }

    /**
     *
     * @param originalImage the image to desaturate
     * @param luminanceThreshold luminance threshold, 0.0 to 1.0
     * @param alphaThreshold alpha threshold, 0.0 to 1.0
     * @return the bi-tonal image
     */
    public static BufferedImage toBlackAndWhite(final BufferedImage originalImage, final float luminanceThreshold, final float alphaThreshold)
    {
        if (luminanceThreshold < 0 || luminanceThreshold > 1) throw new IllegalArgumentException("luminanceThreshold must be between 0.0 and 1.0 (inclusive)");
        if (alphaThreshold < 0 || alphaThreshold > 1) throw new IllegalArgumentException("alphaThreshold must be between 0.0 and 1.0 (inclusive)");
        final int luminanceByteThreshold = Math.round(255 * luminanceThreshold);
        final int alphaByteThreshold = Math.round(255 * alphaThreshold);

        final BufferedImage binaryImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        for (int y = 0; y < originalImage.getHeight(); y++)
        {
            for (int x = 0; x < originalImage.getWidth(); x++)
            {
                final Color pixelColor = new Color(originalImage.getRGB(x, y), true);
                final float luminance = HSLColor.fromRGB(pixelColor)[2];
                if (pixelColor.getAlpha() >= alphaByteThreshold && luminance <= luminanceByteThreshold)
                {
                    binaryImage.setRGB(x, y, BLACK);
                }
                else
                {
                    binaryImage.setRGB(x, y, WHITE);
                }
            }
        }
        return binaryImage;
    }

    /**
     * Returns true or false depending on whether the image has color or gray. This can be used for selective dithering
     * @param image the image to scan
     * @param toleranceFactor tolerance, 0.0 to 1.0
     * @return true if image contains colors or grays, false if image is black and white
     */
    public static boolean imageHasColorOrGray(final BufferedImage image, final float toleranceFactor)
    {
        if (toleranceFactor < 0 || toleranceFactor >= 1) throw new IllegalArgumentException("tolerance must be between 0.0 (inclusive) and 1.0 (exclusive)");
        final int tolerance = Math.round(255 * toleranceFactor);
        long colorPixels = 0;
        final long totalPixels = (long) image.getWidth() * image.getHeight();

        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                final int pixel = image.getRGB(x, y);
                final int alpha = (pixel >> 24) & 0xff;
                final float alphaFactor = alpha / 255f;
                final int red = blendWithWhite(alphaFactor, (pixel >> 16) & 0xff);
                final int green = blendWithWhite(alphaFactor, (pixel >> 8) & 0xff);
                final int blue = blendWithWhite(alphaFactor, pixel & 0xff);

                final int max = Math.max(Math.max(red, green), blue);
                final int min = Math.min(Math.min(red, green), blue);
                final boolean isGrayscale = (max - min) <= tolerance;

                if (!isGrayscale)
                {
                    colorPixels++;
                }
                else
                {
                    if (min >= tolerance && max <= (255 - tolerance))
                    {
                        colorPixels++;
                    }
                }
            }
        }

        // Define your criteria for "colorful"
        return colorPixels > totalPixels * 0.05; // Example: more than 5% color pixels
    }

    /**
     * Blend ARGB pixel with white
     * @param alphaFactor tolerance, 0.0 to 1.0
     * @param color ARGB color value
     * @return RGB color value
     */
    private static int blendWithWhite(final float alphaFactor, final int color)
    {
        return (int)(alphaFactor * color + (1 - alphaFactor) * 255);
    }
}
