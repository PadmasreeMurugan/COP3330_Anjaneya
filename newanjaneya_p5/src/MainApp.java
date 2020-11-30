import java.util.Scanner;

public class MainApp
{
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        MainApp lists = new MainApp();
        lists.processMainApplicationMenu(args);
    }

    private void processMainApplicationMenu(String[] args)
    {
        int userInput = getUserInputForApplicationMenu();

        while(userInput != 3)
        {
            if(userInput == 1)
            {
                TaskApp  tasks = new TaskApp();
                tasks.main(args);
            }

            if(userInput == 2)
            {
                ContactApp  contacts = new ContactApp();
                contacts.main(args);
            }

            userInput = getUserInputForApplicationMenu();
        }
    }

    private void displayApplicationMenu()
    {
        System.out.println("Select Your Application");
        System.out.println("-----------------------");
        System.out.println("1) task list");
        System.out.println("2) contact list");
        System.out.println("3) quit\n");
    }

    private int getUserInputForApplicationMenu()
    {
        displayApplicationMenu();
        return input.nextInt();
    }

    public boolean shouldContinue(int userResponse)
    {
        return (userResponse != 3);
    }

    //display Main menu
    public void displayMainMenu()
    {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.println("3) Quit");
    }

    //get user input for main menu
    public int getContinueResponseForMain()
    {
        displayMainMenu();
        return input.nextInt();
    }


}
