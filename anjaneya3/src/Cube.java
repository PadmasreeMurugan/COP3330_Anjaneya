public class Cube extends Shape3D
{
    private double side;
    private String ShapeName = "cube";

    //constructor
    public Cube(double side)
    {
        this.side = side;
    }

    //Postcondition: This function returns the name of the shape
    @Override
    public String getName()
    {
        return ShapeName;
    }

    //Postcondition: This function returns the area of cube
    @Override
    public double getArea()
    {
        return (6.0 * side * side);
    }

    //Postcondition: This function returns the volume of cube
    @Override
    public double getVolume()
    {
        return (side * side * side);
    }
}

