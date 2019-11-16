package traditional.pojos;

import org.opencv.core.Point;
import traditional.enums.HsvColors;

import java.util.List;

public class ChartBarPojo {
    private List<Point> shape;
    private double height;
    private HsvColors color;

    public ChartBarPojo(List<Point> shape, double height, HsvColors color) {
        this.shape = shape;
        this.height = height;
        this.color = color;
    }

    public List<Point> getShape() {
        return shape;
    }

    public double getHeight() {
        return height;
    }

    public HsvColors getColor() {
        return color;
    }
}
