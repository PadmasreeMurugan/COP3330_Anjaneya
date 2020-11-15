import java.util.Scanner;

public class App
{
    private static Scanner input = new Scanner(System.in);

    private TaskList Tasks;

    public App()
    {
        Tasks = new TaskList();
    }

    public static void main(String[] args)
    {
        App task = new App();
        task.processTasks();
    }

    private void processTasks()
    {
        int userResponse = getContinueResponse();

        while(shouldContinue(userResponse))
        {
            if(userResponse == 1)
            {
                //System.out.println(Tasks.getTitle(0));
                processOption1();
            }
            System.out.println(Tasks.getTitle(0));
            userResponse = getContinueResponse();
            input.nextLine();
        }
    }

    private int getContinueResponse()
    {
        displayMainMenu();
        return input.nextInt();
    }

    private boolean shouldContinue(int userResponse)
    {
        return (userResponse != 3);
    }

    private void storeTaskItem(TaskItem data)
    {
        Tasks.add(data);
    }

    private TaskItem getTaskItemData()
    {
        TaskItem data = null;

        while(true)
        {
            try
            {
                String title = getTaskTitle();
                String description = getTaskDescription();
                String date = getTaskDate();

                data = new TaskItem(title,description, date);
                break;
            }
            catch (InvalidTitleException ex)
            {
                System.out.println("Warning: Your Title is invalid. Please enter again.");
            }
            catch (InvalidDateException ex)
            {
                System.out.println("Warning: Your Date is invalid. Please enter again");
            }
        }
        return data;
    }

    private String getTaskTitle()
    {
        System.out.println("Task title: ");
        return input.nextLine();
    }

    private String getTaskDescription()
    {
        System.out.println("Task Description: ");
        return input.nextLine();
    }

    private String getTaskDate()
    {
        System.out.println("Task Date: ");
        return input.nextLine();
    }

    private void displayMainMenu()
    {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.println("3) Quit");
    }

    private void displayListOperationMenu()
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

    private boolean shouldContinueList (int userResponse)
    {
        return (userResponse != 8);
    }

    private int getContinueResponseList()
    {
        displayListOperationMenu();
        return input.nextInt();
    }

    private void displayCurrentTasks()
    {
        Tasks.displayTasks();
    }

    private void processOption1()
    {
        int userResponse = getContinueResponseList();
        input.nextLine();

        while(shouldContinueList(userResponse))
        {
            if(userResponse == 1)
            {
                displayCurrentTasks();
            }

            if(userResponse == 2)
            {
                TaskItem data = getTaskItemData();
                storeTaskItem(data);
            }

            if(userResponse == 3)
            {
                System.out.println("Which task will you like to edit");
                int index = input.nextInt();
                input.nextLine();

                TaskItem data = EditTaskItemData(index);
                storeTaskItem(data);
            }
            userResponse = getContinueResponseList();
            input.nextLine();
        }
    }

    private TaskItem EditTaskItemData(int index)
    {
        TaskItem data = null;

        try
        {
            String title = getNewTaskTitle();
            String description = getNewTaskDescription();
            String date = getNewTaskDate();

            Tasks.editTaskItemTitle(index, title);
            Tasks.editTaskItemDescription(index, description);
            Tasks.editTaskItemDate(index, date);

        }
        catch (InvalidTitleException ex)
        {
            System.out.println("Warning: Your Title is invalid. Please enter again.");
        }
        catch (InvalidDateException ex)
        {
            System.out.println("Warning: Your Date is invalid. Please enter again");
        }
        catch (IndexOutOfBoundsException ex)
        {
            System.out.println("Warning: Your index is invalid. Please try again");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return data;
    }

    private String getNewTaskTitle()
    {
        System.out.println("Enter a new task title: ");
        return input.nextLine();
    }

    private String getNewTaskDescription()
    {
        System.out.println("Enter a new task Description: ");
        return input.nextLine();
    }

    private String getNewTaskDate()
    {
        System.out.println("Enter a new task Date: ");
        return input.nextLine();
    }

}
