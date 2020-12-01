import java.util.Scanner;

public abstract class App
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

    /*Displaying Main menu is common for both TaskApp and ContactApp*/

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

    //check whether user wants to continue
    public boolean shouldContinueForMainMenu(int userResponse)
    {
        return (userResponse != 3);
    }

    //display List Operation Menu
    public void displayListOperationMenu()
    {
        System.out.println("List Operation Menu");
        System.out.println("--------------------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) mark an item as completed");
        System.out.println("6) unmark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");
    }

    //get user input for list operation menu
    public int getContinueResponseForListMenu()
    {
        displayListOperationMenu();
        return input.nextInt();
    }

    //user response for list operation menu menu is not equal to 8, then continue
    public boolean shouldContinueList (int userResponse)
    {
        return (userResponse != 8);
    }


    //common in both contact and task apps
    public String getFileNameToSave()
    {
        System.out.println("Enter the file name to save: ");
        return input.next();
    }

    public String getFileNameToLoad()
    {
        System.out.println("Enter the file name to load: ");
        return input.next();
    }

    public abstract void viewList();

    public abstract void addItem(String a, String b, String c, String d);

    public abstract void editItem();

    public abstract void removeItem();

    public abstract void markItemAsCompleted();

    public abstract void unMarkItemAsCompleted();

    public abstract void writeToFileAndSaveTheList();

    public abstract void loadDataFromFile();
}

