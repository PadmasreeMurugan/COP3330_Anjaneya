import static java.lang.Math.PI;

public class Circle extends Shape2D
{
    private double radius;
    private String ShapeName = "circle";

    public Circle(double radius)
    {
        this.radius = radius;
        this.ShapeName = ShapeName;
    }

    @Override
    public String getName() {
        return ShapeName;
    }

    @Override
    public double getArea() {
        return (PI * radius * radius);
    }
}
