package traditional.enums;

import org.opencv.core.Scalar;

public enum HsvColors {
    RED(new Scalar(170, 50, 50), new Scalar(180, 255, 255)),
    BLUE(new Scalar(100, 50, 50), new Scalar(130, 255, 255)),
    YELLOW(new Scalar(20, 50, 100), new Scalar(30, 255, 255));

    private final Scalar lower;
    private final Scalar upper;

    HsvColors(Scalar lower, Scalar upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public Scalar getLower() {
        return lower;
    }

    public Scalar getUpper() {
        return upper;
    }
}
