public class BodyMassIndex
{
    private double height;
    private double weight;
    private double BMIScore;
    private double BMI;
    private String BMICategory = "";

    //constructor
    public BodyMassIndex(double height, double weight)
    {
        this.height = height;
        this.weight = weight;
    }

    //Postcondition: This function calculates and returns the BMI score for the given height and weight values.
    public double BMI_Calculator()
    {
        BMIScore = (703 * weight) / (height * height);
        BMIScore = RoundOneDecimal(BMIScore);
        return BMIScore;
    }

    //Postcondition: This function returns a string (underweight) if the BMI score is less than 18.5
    public String BMI_CategoryUnderweight()
    {
        BMI = BMI_Calculator();

        if (BMI < 18.5)
        {
            BMICategory = "underweight";
            return BMICategory;
        }
        return BMICategory;
    }

    //Postcondition: This function returns a string(normal weight) if the BMI score is greater than or equal to 18.5 and less than or equal to 24.9
    public String BMI_CategoryNormalweight()
    {
        BMI = BMI_Calculator();

        if (BMI >= 18.5 && BMI <= 24.9)
        {
            BMICategory = "normal weight";
            return BMICategory;
        }
        return BMICategory;
    }

    //Postcondition: This function returns a string(overweight) if the BMI score is greater than or equal to 25 and less than or equal to 29.9
    public String BMI_CategoryOverweight()
    {
        BMI = BMI_Calculator();

        if (BMI >= 25 && BMI <= 29.9)
        {
            BMICategory = "overweight";
            return BMICategory;
        }
        return BMICategory;
    }

    //Postcondition: This function returns a string(obese) if the BMI score is greater than or equal to 30
    public String BMI_CategoryObesity()
    {
        BMI = BMI_Calculator();

        if (BMI >= 30)
        {
            BMICategory = "obese";
            return BMICategory;
        }
        return BMICategory;
    }

    //Precondition:  This function takes a parameter of double datatype
    //Postcondition: This function rounds the entered double value rounded to one decimal place.
    public double RoundOneDecimal (double BMIScore)
    {
        BMIScore = Double.parseDouble(String.format("%.1f", BMIScore));
        return BMIScore;
    }
}
