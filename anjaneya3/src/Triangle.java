public class Triangle extends Shape2D
{
    private double base;
    private double height;
    private String ShapeName = "triangle";

    public Triangle(double base, double height)
    {
        this.base = base;
        this.height = height;
        this.ShapeName = ShapeName;
    }

    @Override
    public String getName() {
        return ShapeName;
    }

    @Override
    public double getArea() {
        return (base * height)/2.0;
    }
}
