package edu.project4;

public final class Main {
    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        FractalInferno fractalInferno = new FractalInferno();
        var image = fractalInferno.multiThreadRender(
            1080,
            1920,
            100,
            10,
            500_000,
            3,
            NonLinearType.SPHERE,
            NonLinearType.HEART
        );
        image.logarithmicGammaCorrection(0.2);
        image.createFile("src/main/java/edu/project4/images/Fractal_Inferno_attempt2");
    }
}
