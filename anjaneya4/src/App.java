import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class App
{
    private static Scanner input = new Scanner(System.in);

    private ArrayList<TaskList> tasklist = new ArrayList<>();

    public static void main(String[] args)
    {
        processTaskData();
    }

    public static void processTaskData()
    {
        TaskList m = new TaskList();

        int continueResponse = askShouldContinueMain();

        while(continueResponse != 3)
        {
            if(continueResponse == 1)
            {
                System.out.println("new task list has been created\n");

                 m.processMainMenu1();
            }
            continueResponse = askShouldContinueMain();
        }
    }

    public static int askShouldContinueMain()
    {
        displayMainMenu();
        return input.nextInt();
    }

    public static void displayMainMenu()
    {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.println("3) Quit");
    }
}





