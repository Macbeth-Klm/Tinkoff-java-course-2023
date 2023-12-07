package edu.project4;

public final class NonLinearTransformator {
    private NonLinearTransformator() {

    }

    public static Point getPoint(double x, double y, NonLinearType type) {
        var r = Math.pow(x, 2) + Math.pow(y, 2);
        var k = Math.sqrt(r);
        var arcTan = Math.atan2(y, x);
        return switch (type) {
            case SIN -> new Point(Math.sin(x), Math.sin(y));
            case SPHERE -> new Point(x / r, y / r);
            case POLAR -> new Point(arcTan / Math.PI, k - 1);
            case HEART -> new Point(k * Math.sin(k * arcTan), -k * Math.cos(k * arcTan));
            case DISK -> new Point(
                1 / Math.PI * arcTan * Math.sin(Math.PI * k),
                1 / Math.PI * arcTan * Math.cos(Math.PI * k)
            );
        };
    }

    public enum NonLinearType {
        SIN,
        SPHERE,
        POLAR,
        HEART,
        DISK
    }
}
