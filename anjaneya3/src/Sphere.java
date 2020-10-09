import static java.lang.Math.PI;

public class Sphere extends Shape3D
{
    private double radius;
    private String ShapeName = "sphere";

    public Sphere(double radius)
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
        return (4 * PI * radius * radius);
    }

    @Override
    public double getVolume()
    {
        return ((4.0/3.0) * (PI) * (radius * radius * radius));
    }
}
