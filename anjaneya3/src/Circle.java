import static java.lang.Math.PI;

public class Circle extends Shape2D
{
    private double radius;
    private String ShapeName = "circle";

    //constructor
    public Circle(double radius)
    {
        this.radius = radius;
    }

    //Postcondition: This function returns the name of the shape
    @Override
    public String getName()
    {
        return ShapeName;
    }

    //Postcondition: This function returns teh area of circle
    @Override
    public double getArea()
    {
        return (PI * radius * radius);
    }
}
