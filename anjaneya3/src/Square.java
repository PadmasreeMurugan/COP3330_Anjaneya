public class Square extends Shape2D
{
    private double side;
    private String ShapeName = "square";

    public Square(double side)
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
        return (side * side);
    }
}
