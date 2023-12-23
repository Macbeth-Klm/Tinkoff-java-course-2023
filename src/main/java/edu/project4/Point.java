package edu.project4;

public record Point(double x, double y) {
    public static Point makeAffineTransformation(Point point, AffineTransformation affine) {
        var x = point.x;
        var y = point.y;
        double newX = affine.a() * x + affine.b() * y + affine.c();
        double newY = affine.d() * x + affine.e() * y + affine.f();
        return new Point(newX, newY);
    }

    public static Point makeNonLinearTransformation(Point point, NonLinearType type) {
        var x = point.x;
        var y = point.y;
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

    public static Point rotate(Point point, double theta) {
        var x = point.x;
        var y = point.y;
        double xRotated = x * Math.cos(theta) - y * Math.sin(theta);
        double yRotated = x * Math.sin(theta) + y * Math.cos(theta);
        return new Point(xRotated, yRotated);
    }
}
