package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public record FractalImage(Pixel[][] data, int width, int height) {
    public void createFile(String path) {
        try {
            BufferedImage image = new BufferedImage(width, height, 1);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Pixel tempPixel = data[i][j];
                    Color newColor = new Color(tempPixel.getRed(), tempPixel.getGreen(), tempPixel.getBlue());
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File(path + ".jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("MagicNumber")
    public void logarithmicGammaCorrection(double gammaIncrease) {
        int max = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (data[row][col].getCounter() != 0) {
                    max = Math.max(max, data[row][col].getCounter());
                }
            }
        }
        double maxLog = Math.log10(max);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double gammaFactor = Math.log10(data[row][col].getCounter()) / maxLog;
                gammaFactor += gammaFactor * gammaIncrease;
                data[row][col]
                    .setRed(Math.min((int) (data[row][col].getRed() * gammaFactor), 255));
                data[row][col]
                    .setGreen(Math.min((int) (data[row][col].getGreen() * gammaFactor), 255));
                data[row][col]
                    .setBlue(Math.min((int) (data[row][col].getBlue() * gammaFactor), 255));
            }
        }
    }
}
