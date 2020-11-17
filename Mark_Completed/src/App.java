import java.io.IOException;
import java.util.Scanner;

public class App
{
    //declaring scanner object
    private static Scanner input = new Scanner(System.in);

    //declaring taskList array
    private TaskList listOfTasks;

    //constructor for initiating tasks object
    public App()
    {
        listOfTasks = new TaskList();
    }

    //Main function
    public static void main(String[] args)
    {
        App task = new App();
        task.processTasks();
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

    //get user input for main menu
    private int getContinueResponseForMain()
    {
        displayMainMenu();
        return input.nextInt();
    }

    //return true if user response for main menu is not equal to 3
    private boolean shouldContinue(int userResponse)
    {
        return (userResponse != 3);
    }

    //processes main menu options
    private void processTasks()
    {
        int userResponse = getContinueResponseForMain();

        while(shouldContinue(userResponse))
        {
            if(userResponse == 1)
            {
                createNewList();
            }
            if(userResponse == 2)
            {
                loadExistingList();
            }
            userResponse = getContinueResponseForMain();
            input.nextLine();
        }
    }

    //user enters create a list
    private void createNewList()
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
                listOfTasks.displayCurrentTasks();
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
                listOfTasks.displayUncompletedTasks();
                markTaskAsCompleted();
            }

            if(userResponse == 6)
            {
                displayCompletedTasks();
                ummarkTaskAsUncompleted();
            }

            if(userResponse == 7)
            {
                writeListOfTasks(getFileName());
            }

            userResponse = getContinueResponseForListMenu();
            input.nextLine();
        }
    }

    private void loadExistingList()
    {
        for(int i = 0; i < listOfTasks.sizeOfTaskList(); i++)
        {
            listOfTasks.remove(listOfTasks.tasks.get(i));
        }

        if(listOfTasks.ListIsEmpty())
        {
            listOfTasks.readTaskData("tasks.txt");
            createNewList();
        }

        else
        {
            for(int i = 0; i < listOfTasks.sizeOfTaskList(); i++)
            {
                listOfTasks.remove(listOfTasks.tasks.get(i));
            }
            String filename = getFileName();
            listOfTasks.readTaskData(filename);
            createNewList();
        }
    }

    //display List Opeartion Menu
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

    private String getCompletionMark()
    {
        return " ";
    }

    private int getRemoveIndex()
    {
        System.out.println("Which task will you remove?");
        return input.nextInt();
    }

    private void storeTaskItem(TaskItem data)
    {
        listOfTasks.add(data);
    }

    //getting new task details from user for editing the tasks
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

    private int getMarkIndex()
    {
        System.out.println("Which task will you mark as complete:?");
        return input.nextInt();
    }

    private String getFileName()
    {
        System.out.println("Enter the filename: ");
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
                String completionMark = getCompletionMark();

                data = new TaskItem(title,description, date, completionMark);
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



    //checking for exception and removing the tasks
    private void removeTasks()
    {
        int size = listOfTasks.sizeOfTaskList();

        while(true)
        {
            try
            {
                int index = getRemoveIndex();
                listOfTasks.removeTaskItem(index);

                if((size-1) == listOfTasks.sizeOfTaskList())
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



    //checking for exception and editing the tasks
    private void EditTasks()
    {
        while(true)
        {
            try
            {
                int userIndex = getEditIndex();
                input.nextLine();

                String title = " ";
                String description = " ";
                String date  = " ";

                if(listOfTasks.indexIsValid(userIndex))
                {
                     title = getNewTaskTitle();
                     description = getNewTaskDescription();
                     date = getNewTaskDate();
                }

                listOfTasks.editTaskTitle(title, userIndex);
                listOfTasks.editTaskDescription(description, userIndex);
                listOfTasks.editTaskDate(date, userIndex);

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

    //checking for exception and marking the task as completed
    private void markTaskAsCompleted( )
    {
        while(true)
        {
            try
            {
                int index = getMarkIndex();
                listOfTasks.markingTaskCompleted(index);

                break;

            }
            catch(InvalidIndexException e)
            {
                System.out.println("Warning: Invalid index. Please try enter again");
            }
        }
    }

    private int getUnmarkIndex()
    {
        System.out.println("Which task will you unmark as complete:?");
        return input.nextInt();
    }

    private void displayCompletedTasks()
    {
        listOfTasks.displayCompletedTasks();
    }

    //checking for exception and unmarking the task as completed
    private void ummarkTaskAsUncompleted( )
    {
        while(true)
        {
            try
            {
                int index = getMarkIndex();
                listOfTasks.unmarkingTaskAsUncompleted(index);

                break;

            }
            catch(InvalidIndexException e)
            {
                System.out.println("Warning: Invalid index. Please try enter again");
            }
        }
    }

    private void writeListOfTasks(String fileName)
    {
        listOfTasks.writeTaskdata(fileName);
    }






}
