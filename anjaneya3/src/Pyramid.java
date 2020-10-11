public class Pyramid extends Shape3D
{
    private double length;
    private double width;
    private double height;
    private String ShapeName = "pyramid";

    //constructor
    public Pyramid(double length, double width, double height)
    {
        this.length    = length;
        this.width     = width;
        this.height    = height;
    }

    //Postcondition: This function returns the name of the shape
    @Override
    public String getName()
    {
        return ShapeName;
    }

    //Postcondition: This function returns the area of pyramid
    @Override
    public double getArea()
    {
        return ((length * width) + length * Math.sqrt(((width/2.0)* (width/2.0)) + (height * height))
                + width * Math.sqrt((length/2.0) * (length/2.0) + (height * height)));
    }

    //Postcondition: This function returns the volume of pyramid
    @Override
    public double getVolume()
    {
        return ((length *width *height)/3.0);
    }
}

