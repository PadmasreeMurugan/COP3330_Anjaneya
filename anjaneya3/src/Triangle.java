public class Triangle extends Shape2D
{
    private double base;
    private double height;
    private String ShapeName = "triangle";

    //constructor
    public Triangle(double base, double height)
    {
        this.base = base;
        this.height = height;
    }

    //Postcondition: This function returns the name of the shape
    @Override
    public String getName()
    {
        return ShapeName;
    }

    //Postcondition: This function returns the area of triangle
    @Override
    public double getArea()
    {
        return ((base * height)/2.0);
    }
}
