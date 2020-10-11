import static java.lang.Math.PI;

public class Sphere extends Shape3D
{
    private double radius;
    private String ShapeName = "sphere";

    //constructor
    public Sphere(double radius)
    {
        this.radius = radius;
    }

    //Postcondition: This function returns the name of the shape
    @Override
    public String getName()
    {
        return ShapeName;
    }

    //Postcondition: This function returns the area of the sphere
    @Override
    public double getArea()
    {
        return (4.0 * PI * radius * radius);
    }

    //Postcondition: This function returns the volume of the sphere
    @Override
    public double getVolume()
    {
        return ((4.0/3.0) * (PI) * (radius * radius * radius));
    }
}
