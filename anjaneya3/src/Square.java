public class Square extends Shape2D
{
    private double side;
    private String ShapeName = "square";

    //constructor
    public Square(double side)
    {
        this.side = side;
    }

    //Postcondition: This method returns the name of the shape
    @Override
    public String getName()
    {
        return ShapeName;
    }

    //Postcondtion: This method returns the area of square
    @Override
    public double getArea()
    {
        return (side * side);
    }
}
