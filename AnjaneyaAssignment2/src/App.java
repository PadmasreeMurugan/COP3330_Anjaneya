
import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput())
        {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }
        displayBmiStatistics(bmiData);
    }

    //Postcondition: This function prompts the user to enter Y or N and returns a boolean value
    public static boolean moreInput()
    {
        System.out.println("Please enter \"Y\" to continue and \"N\" to exit:");

        Scanner userInput = new Scanner(System.in);
        String input = userInput.next();
        userInput.nextLine();

        if(input.equals("Y"))
        {
            return true;
        }
        else if(input.equals("N"))
        {
            return false;
        }
        return false;
    }

    //Postcondtion: This function prompts the user to enter height in inches and returns the height
    public static double getUserHeight()
    {
        System.out.println("Please enter your height in inches: ");

        Scanner heightUserInput = new Scanner(System.in);
        double givenHeight = heightUserInput.nextDouble();
        heightUserInput.nextLine();

        //checks whether the entered height is positive, if not prompts the user to re-enter
        givenHeight =  enterPositiveValue(givenHeight);

        return givenHeight;
    }

    //Postcondtion: This function prompts the user to enter weight in pounds and returns the weight
    public static double getUserWeight()
    {
        System.out.println("Please enter your weight in pounds: ");

        Scanner weightUserInput = new Scanner(System.in);
        double givenWeight = weightUserInput.nextDouble();
        weightUserInput.nextLine();

        //checks whether the entered weight is positive, if not prompts the user to re-enter
        givenWeight =  enterPositiveValue(givenWeight);

        return givenWeight;
    }

    //Precondition:  This function takes a parameter of double datatype
    //Postcondition: Prompts the user to re-enter if the passed parameter is negative and returns the value
    public static double enterPositiveValue(double userInput)
    {
        while (userInput < 0)
        {
            System.out.println("Please enter a positive value: ");

            Scanner userInput2 = new Scanner(System.in);
            userInput = userInput2.nextDouble();
            userInput2.nextLine();
        }
        return userInput;
    }

    //Precondition:  This function takes a BodyMassIndex object as parameter
    //Postcondition: This function displays the calculated BMI score and the respective category
    public static void displayBmiInfo (BodyMassIndex bmi)
    {
        double BMI_Score     = bmi.BMI_Calculator();
        String BMI_Category1 = bmi.BMI_CategoryUnderweight();
        String BMI_Category2 = bmi.BMI_CategoryNormalweight();
        String BMI_Category3 = bmi.BMI_CategoryOverweight();
        String BMI_Category4 = bmi.BMI_CategoryObesity();

        System.out.println("Your BMI score is: ");
        System.out.printf("%.1f%n", BMI_Score);

        System.out.println("Your BMI Category is: ");

        if(!BMI_Category1.equals(""))
        {
            System.out.println(BMI_Category1);
        }

        else if(!BMI_Category2.equals(""))
        {
            System.out.println(BMI_Category2);
        }

        else if(!BMI_Category3.equals(""))
        {
            System.out.println(BMI_Category3);
        }

        else
        {
            System.out.println(BMI_Category4);
        }
    }

    //Precondition:  This function takes an arraylist as parameter
    //Postcondition: This function calculates and displays the average BMI score of all users
    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData)
    {
        double BMISum = 0.00;
        int count = 0;

        for(BodyMassIndex BMI : bmiData)
        {
            BMISum += BMI.BMI_Calculator();
            count++;
        }
        System.out.println("Average BMI score of the data:");
        System.out.printf("%.1f", (BMISum/count));
    }
}
