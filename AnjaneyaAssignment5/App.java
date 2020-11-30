import java.util.Scanner;

public class App
{
    private static Scanner input = new Scanner(System.in);

    public static void main(String args[])
    {
        System.out.println("Select your application\n");
        System.out.println("1) task list");
        System.out.println("2) contact list");
        System.out.println("3) quit list");

        int userInput = input.nextInt();

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
            System.out.println("Select your application\n");
            System.out.println("1) task list");
            System.out.println("2) contact list");
            System.out.println("3) quit list");

            userInput = input.nextInt();
        }

    }


}
