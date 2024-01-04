package edu.project4;

import edu.project4.fractal.AffineTransformation;
import edu.project4.fractal.Point;
import org.junit.jupiter.api.Test;
import static edu.project4.fractal.NonLinearType.DISK;
import static edu.project4.fractal.NonLinearType.HEART;
import static edu.project4.fractal.NonLinearType.POLAR;
import static edu.project4.fractal.NonLinearType.SIN;
import static edu.project4.fractal.NonLinearType.SPHERE;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointTest {
    @Test
    void shouldMakeCorrectAffineTransformation() {
        var point = new Point(2, -2);
        var affine = new AffineTransformation(0.5, 0.2, -0.7, 0.4, -0.68, -0.29, 5, 55, 110);

        var result = Point.makeAffineTransformation(point, affine);

        assertTrue(
            result.x() == affine.a() * point.x() + affine.b() * point.y() + affine.c()
                && result.y() == affine.d() * point.x() + affine.e() * point.y() + affine.f()
        );
    }

    @Test
    void shouldMakeCorrectNonLinearTransformation() {
        var point = new Point(2, -2);

        var resultSin = Point.makeNonLinearTransformation(point, SIN);
        var resultSphere = Point.makeNonLinearTransformation(point, SPHERE);
        var resultPolar = Point.makeNonLinearTransformation(point, POLAR);
        var resultHeart = Point.makeNonLinearTransformation(point, HEART);
        var resultDisk = Point.makeNonLinearTransformation(point, DISK);

        assertTrue(
            resultSin.x() == 0.9092974268256817 && resultSin.y() == -0.9092974268256817
                && resultSphere.x() == 0.25 && resultSphere.y() == -0.25
                && resultPolar.x() == -0.25 && resultPolar.y() == 1.8284271247461903
                && resultHeart.x() == -2.2505602342896007 && resultHeart.y() == 1.7131779335008779
                && resultDisk.x() == -0.12832209928926547 && resultDisk.y() == 0.21455404641720438
        );
    }

    @Test
    void shouldCorrectRotatePoint() {
        var point = new Point(-2, 0);
        var theta = Math.PI / 2;
        var delta = Math.pow(10, -15);

        var result = Point.rotate(point, theta);

        assertTrue(
            result.x() >= 0 - delta && result.x() <= 0 + delta
                && result.y() >= -2.0 - delta && result.y() <= -2.0 + delta
        );
    }
}
