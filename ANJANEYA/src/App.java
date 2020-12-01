import java.util.Scanner;

public class App
{
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        processMainApplicationMenu();
    }

    private static void processMainApplicationMenu()
    {

        int userInput = getUserInputForApplicationMenu();

        while(shouldContinue(userInput))
        {
            if(userInput == 1)
            {
                TaskApp tasks = new TaskApp();
                tasks.processMainMenu();
            }

            if(userInput == 2)
            {
                ContactApp  contacts = new ContactApp();
                contacts.processMainMenu();
            }

            userInput = getUserInputForApplicationMenu();
        }
    }

    private static void displayApplicationMenu()
    {
        System.out.println("Select Your Application");
        System.out.println("-----------------------");
        System.out.println("1) task list");
        System.out.println("2) contact list");
        System.out.println("3) quit\n");
        System.out.print("> ");

    }

    private static int getUserInputForApplicationMenu()
    {
        displayApplicationMenu();

        return input.nextInt();
    }

    public static boolean shouldContinue(int userResponse)
    {
        return (userResponse != 3);
    }

}
