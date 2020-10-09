public class Cube extends Shape3D
{
    private double side;
    private String ShapeName = "cube";

    public Cube(double side)
    {
        this.side = side;
        this.ShapeName = ShapeName;
    }

    @Override
    public String getName() {
        return ShapeName;
    }

    @Override
    public double getArea() {
        return (6 * side * side);
    }

    @Override
    public double getVolume() {
        return (side * side * side);
    }
}

