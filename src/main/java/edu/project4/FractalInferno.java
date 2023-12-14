package edu.project4;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public final class FractalInferno {
    private static final int MAX_COLOR_VALUE = 255;
    private static final double COEF_MIN_VALUE = -1;
    private static final double COEF_MAX_VALUE = 1;
    private static final double X_MIN = -1;
    private static final double X_MAX = 1;
    private static final double Y_MIN = -1;
    private static final double Y_MAX = 1;
    private Pixel[][] pixels;

    public FractalInferno() {
    }

    public FractalImage singleThreadRender(
        int imageHeight,
        int imageWidth,
        int pointsCount,
        int affineCount,
        int iterationsCount,
        int symmetry,
        NonLinearType... types
    ) {
        if (symmetry <= 0
            || imageHeight <= 0
            || imageWidth <= 0
            || pointsCount <= 0
            || affineCount <= 0
            || iterationsCount <= 0) {
            throw new IllegalArgumentException();
        }
        pixels = new Pixel[imageHeight][imageWidth];
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        AffineTransformation[] affines = createAffineTransformation(affineCount);
        for (int n = 0; n < pointsCount; n++) {
            render(imageHeight, imageWidth, iterationsCount, symmetry, affines, types);
        }
        return new FractalImage(pixels, imageWidth, imageHeight);
    }

    @SuppressWarnings("MagicNumber")
    public FractalImage multiThreadRender(
        int imageHeight,
        int imageWidth,
        int pointsCount,
        int affineCount,
        int iterationsCount,
        int symmetry,
        NonLinearType... types
    ) {
        if (symmetry <= 0
            || imageHeight <= 0
            || imageWidth <= 0
            || pointsCount <= 0
            || affineCount <= 0
            || iterationsCount <= 0) {
            throw new IllegalArgumentException();
        }
        pixels = new Pixel[imageHeight][imageWidth];
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        AffineTransformation[] affines = createAffineTransformation(affineCount);
        try (var executorService = Executors.newFixedThreadPool(4)) {
            for (int n = 0; n < pointsCount; n++) {
                executorService.execute(() -> render(
                    imageHeight,
                    imageWidth,
                    iterationsCount,
                    symmetry,
                    affines,
                    types
                ));
            }
        }
        return new FractalImage(pixels, imageWidth, imageHeight);
    }

    private void render(
        int imageHeight,
        int imageWidth,
        int iterationsCount,
        int symmetry,
        AffineTransformation[] affines,
        NonLinearType... types
    ) {
        var x = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
        var y = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);
        Point point = new Point(x, y);
        var type = types[ThreadLocalRandom.current().nextInt(0, types.length)];
        for (int tempIter = 0; tempIter < iterationsCount; tempIter++) {
            var affine = affines[ThreadLocalRandom.current().nextInt(0, affines.length)];
            point = Point.makeAffineTransformation(point, affine);
            point = Point.makeNonLinearTransformation(point, type);
            var theta = 0.0;
            for (int s = 0; s < symmetry; s++) {
                theta += (Math.PI * 2) / symmetry;
                var rotated = Point.rotate(point, theta);
                x = rotated.x();
                y = rotated.y();
                if (X_MIN <= x && x <= X_MAX && Y_MIN <= y && y <= Y_MAX) {
                    int x1 = (int) (imageHeight - Math.floor(((X_MAX - x) / (X_MAX - X_MIN)) * imageHeight));
                    int y1 = (int) (imageWidth - Math.floor(((Y_MAX - y) / (Y_MAX - Y_MIN)) * imageWidth));
                    if (x1 < imageHeight && y1 < imageWidth) {
                        synchronized (pixels[x1][y1]) {
                            if (pixels[x1][y1].getCounter() == 0) {
                                pixels[x1][y1].setRed(affine.red());
                                pixels[x1][y1].setGreen(affine.green());
                                pixels[x1][y1].setBlue(affine.blue());
                            } else {
                                pixels[x1][y1].setRed((pixels[x1][y1].getRed() + affine.red()) / 2);
                                pixels[x1][y1].setGreen((pixels[x1][y1].getGreen() + affine.green()) / 2);
                                pixels[x1][y1].setBlue((pixels[x1][y1].getBlue() + affine.blue()) / 2);
                            }
                            pixels[x1][y1].incrementCounter();
                        }
                    }
                }
            }
        }
    }

    private AffineTransformation[] createAffineTransformation(int affineCount) {
        AffineTransformation[] result = new AffineTransformation[affineCount];
        for (int i = 0; i < affineCount; i++) {
            double a;
            double b;
            double d;
            double e;
            double firstExpression;
            double secondExpression;
            double thirdExpression;
            double fourthExpression;
            do {
                a = ThreadLocalRandom.current().nextDouble(COEF_MIN_VALUE, COEF_MAX_VALUE);
                b = ThreadLocalRandom.current().nextDouble(COEF_MIN_VALUE, COEF_MAX_VALUE);
                d = ThreadLocalRandom.current().nextDouble(COEF_MIN_VALUE, COEF_MAX_VALUE);
                e = ThreadLocalRandom.current().nextDouble(COEF_MIN_VALUE, COEF_MAX_VALUE);
                firstExpression = Math.pow(a, 2) + Math.pow(d, 2);
                secondExpression = Math.pow(b, 2) + Math.pow(e, 2);
                thirdExpression = firstExpression + secondExpression;
                fourthExpression = 1 + Math.pow(a * e - b * d, 2);
            } while (
                firstExpression >= 1
                    || secondExpression >= 1
                    || thirdExpression >= fourthExpression
            );
            double c = ThreadLocalRandom.current().nextDouble(COEF_MIN_VALUE, COEF_MAX_VALUE);
            double f = ThreadLocalRandom.current().nextDouble(COEF_MIN_VALUE, COEF_MAX_VALUE);
            int red = ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE);
            int green = ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE);
            int blue = ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE);
            result[i] = new AffineTransformation(a, b, c, d, e, f, red, green, blue);
        }
        return result;
    }
}
