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

    /*Application Menu*/

    private static void displayApplicationMenu()
    {
        System.out.println("\nSelect Your Application");
        System.out.println("-----------------------");
        System.out.println("1) task list");
        System.out.println("2) contact list");
        System.out.println("3) quit\n");
        System.out.print("> ");

    }

    //get user input for application menu
    private static int getUserInputForApplicationMenu()
    {
        displayApplicationMenu();
        return input.nextInt();
    }

    //check whether user wants to continue
    public static boolean shouldContinue(int userResponse)
    {
        return (userResponse != 3);
    }

    /*Main Menu*/

    /*MAIN MENU OPERATIONS ARE COMMON FOR BOTH TaskApp AND ContactApp (inherited)*/

    //display Main menu
    public void displayMainMenu()
    {
        System.out.println("\nMain Menu");
        System.out.println("---------");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.println("3) Quit\n");
        System.out.print("> ");
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

    /*List Operation Menu*/

    //display List Operation Menu
    public void displayListOperationMenu()
    {
        System.out.println("\nList Operation Menu");
        System.out.println("--------------------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) mark an item as completed");
        System.out.println("6) unmark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu\n");
        System.out.print("> ");
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

    /*GETTING FILE NAME TO SAVE AND LOAD ARE COMMON FOR BOTH TaskApp and ContactApp(inherited)*/

    public String getFileNameToSave()
    {
        System.out.print("Enter the file name to save: ");
        return input.next();
    }

    public String getFileNameToLoad()
    {
        System.out.print("Enter the file name to load: ");
        return input.next();
    }

    //abstract methods
    public abstract void viewCurrentList();

    public abstract void addItem(String a, String b, String c, String d);

    public abstract void editItem();

    public abstract void removeItem();

    public abstract void markItemAsCompleted();

    public abstract void unMarkItemAsCompleted();

    public abstract void writeToFileAndSaveTheList();

    public abstract void loadDataFromFile();
}

