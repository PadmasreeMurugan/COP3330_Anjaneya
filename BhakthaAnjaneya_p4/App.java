import java.util.Scanner;

//Creating a class
public class App
{
    /* program that allows user to enter different tasks and each task contains a
    title, date and description */


    /* User Scenario
    User starts the program and is presented with a prompt asking if he wants to create a list or
    load a existing list or quit from the main menu.
     */


    //declaring scanner object
    private static Scanner input = new Scanner(System.in);

    //declaring Tasklist array, with tasks objects
    private TaskList Tasks;

    //constructor for initiating tasks object
    public App()
    {
        Tasks = new TaskList();
    }

    //Main function
    public static void main(String[] args)
    {
        App task = new App();
        task.processTasks();
    }

    private void processTasks()
    {
        int userResponse = getContinueResponseForMain();

        while(shouldContinue(userResponse))
        {
            if(userResponse == 1)
            {
                processOption1();
            }
            userResponse = getContinueResponseForMain();
            input.nextLine();
        }
    }

    //display Main menu
    private void displayMainMenu()
    {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.println("3) Quit");
    }

    //add exception for invalid input

    //get user input for main menu
    private int getContinueResponseForMain()
    {
        displayMainMenu();
        return input.nextInt();
    }

    //user response for main menu is not equal to 3, then continue
    private boolean shouldContinue(int userResponse)
    {
        return (userResponse != 3);
    }

    //if user enters chooses option one in main menu, display list operation menu
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

    //get user input for list operation menu
    private int getContinueResponseForListMenu()
    {
        displayListOperationMenu();
        return input.nextInt();
    }

    //user response for list operation menu menu is not equal to 8, then continue
    private boolean shouldContinueList (int userResponse)
    {
        return (userResponse != 8);
    }

    //get user input for title, description and date
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

    //get a task which contains title, date and description
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

    private int getRemoveIndex()
    {
        System.out.println("Which task will you remove?");
        return input.nextInt();
    }

    private void removeTasks()
    {
        int size = Tasks.sizeOfTaskList();

        while(true)
        {
            try
            {
                int index = getRemoveIndex();
                Tasks.removeTaskItem(index);

                if((size-1) == Tasks.sizeOfTaskList())
                {
                    break;
                }
            }
            catch(InvalidIndexException e)
            {
                System.out.println("Warning: Invalid index. Please try enter again");
            }
        }
    }

    private void storeTaskItem(TaskItem data)
    {
        Tasks.add(data);
    }

   private void EditTasks()
   {
       while(true)
       {
           try
           {
               int userIndex = getEditIndex();
               input.nextLine();

               TaskItem originalData = Tasks.tasks.get(userIndex);


               String title = getNewTaskTitle();
               String description = getNewTaskDescription();
               String date = getNewTaskDate();

               Tasks.editTaskTitle(title, userIndex);
               Tasks.editTaskDescription(description, userIndex);
               Tasks.editTaskDate(date, userIndex);

               TaskItem editedData = Tasks.tasks.get(userIndex);
              /* System.out.println("Edited Data");
               System.out.println(editedData.getTitle());
               System.out.println(editedData.getDescription());
               System.out.println(editedData.getDate());
               */

               String originalTitle = originalData.getTitle();
               String editedTitle   = editedData.getTitle();

               if(originalTitle == (editedTitle))
               {
                   System.out.println("Task title is not edited");
               }

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
           catch(InvalidIndexException e)
           {
               System.out.println("Warning: Your index is invalid. Please try enter again");
           }
           catch (Exception ex)
           {
               ex.printStackTrace();
           }
       }

   }

    private int getEditIndex()
    {
        System.out.println("Which task will you edit?");
        return input.nextInt();
    }

    private String getNewTaskTitle()
    {
        System.out.println("Enter new task title: ");
        return input.nextLine();
    }

    private String getNewTaskDescription()
    {
        System.out.println("Enter new task description: ");
        return input.nextLine();
    }

    private String getNewTaskDate()
    {
        System.out.println("Enter new task date: ");
        return input.nextLine();
    }

    //user enters create a list
    private void processOption1()
    {
        int userResponse = getContinueResponseForListMenu();
        input.nextLine();

        TaskItem data = null;

        while(shouldContinueList(userResponse))
        {
            if(userResponse == 1)
            {
                System.out.println("Current tasks:");
                System.out.println("---------------");
                Tasks.displayCurrentTasks();
            }
            if(userResponse == 2)
            {
                data = getTaskItemData();
                storeTaskItem(data);
            }

            if(userResponse == 3)
            {
                EditTasks();
            }

            if(userResponse == 4)
            {
                removeTasks();
            }

            if(userResponse == 5)
            {
               // completeTasks();
            }

            userResponse = getContinueResponseForListMenu();
            input.nextLine();
        }
    }


}
