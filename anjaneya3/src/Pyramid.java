public class Pyramid extends Shape3D
{
    private double length;
    private double width;
    private double height;
    private String ShapeName = "pyramid";

    public Pyramid(double length, double width, double height)
    {
        this.length    = length;
        this.width     = width;
        this.height    = height;
        this.ShapeName = ShapeName;
    }

    @Override
    public String getName() {
        return ShapeName;
    }

    @Override
    public double getArea() {
        return ((length * width) + length * Math.sqrt(((width/2.0)* (width/2.0)) + (height * height))
                + width * Math.sqrt((length/2.0) * (length/2.0) + (height * height)));
    }

    @Override
    public double getVolume() {
        return ((length *width *height)/3.0);
    }
}

