import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskApp
{
    //declaring scanner object
    private static Scanner input = new Scanner(System.in);

    //declaring taskList array
    private TaskList listOfTasks;

    //constructor for initiating tasks object
    public TaskApp()
    {
        listOfTasks = new TaskList();
    }

    //Main function
    public static void main(String[] args)
    {
        TaskApp task = new TaskApp();
        task.processMainMenu();
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
    public void processMainMenu()
    {
        int userResponse = getContinueResponseForMain();

        while(shouldContinue(userResponse))
        {
            if(userResponse == 1)
            {
                System.out.println("new task list has been created");
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
                displayCurrentTasks();
            }
            if(userResponse == 2)
            {
                data = getTaskItemData();
                storeTaskItem(data);
            }

            if(userResponse == 3)
            {
                displayCurrentTasks();
                editTasks();
            }

            if(userResponse == 4)
            {
                displayCurrentTasks();
                removeTasks();
            }

            if(userResponse == 5)
            {
                displayUncompletedTasks();
                markTaskAsCompleted();
            }

            if(userResponse == 6)
            {
                displayCompletedTasks();
                ummarkTaskAsUncompleted();
            }

            if(userResponse == 7)
            {
                writeListOfTasks();
                System.out.println("task list has been saved");
            }
            userResponse = getContinueResponseForListMenu();
            input.nextLine();
        }
    }

    //user enters load an existing list
    private void loadExistingList()
    {
        for(int i = 0; i < listOfTasks.sizeOfTaskList(); i++)
        {
            listOfTasks.remove(listOfTasks.tasks.get(i));
        }

        if(listOfTasks.ListIsEmpty())
        {
            readTaskData();
            System.out.println("task list has been loaded");
            createNewList();
        }

        else
        {
            for(int i = 0; i < listOfTasks.sizeOfTaskList(); i++)
            {
                listOfTasks.remove(listOfTasks.tasks.get(i));
            }
            readTaskData();
            System.out.println("task list has been loaded");
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

    /*Option 1) view the list: List Operation Menu*/

    private void displayCurrentTasks()
    {
        listOfTasks.displayCurrentTasks();
    }

    /*Option 2) add an item: List Operation Menu*/

    //getting task title, description, date from user
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
        System.out.println("Task Due Date(YYYY-MM-DD): ");
        return input.nextLine();
    }

    private String getCompletionMark()
    {
        return " ";
    }

    //getting an task item from user
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

                System.out.println("Warning: title must be at least 1 character long; Please enter again");
            }
            catch (InvalidDateException ex)
            {

                System.out.println("Warning: invalid due date; Please enter again");
            }
        }
        return data;
    }

    //storing the task item to the list of tasks
    private void storeTaskItem(TaskItem data)
    {
        listOfTasks.add(data);
    }

    /*Option 3) Edit an item: List Operation menu*/

    //getting new task title, description, date from user for editing the tasks
    private String getNewTaskTitle(int userIndex)
    {
        System.out.printf("Enter new task title for task %d: ", userIndex);
        return input.nextLine();
    }

    private String getNewTaskDescription(int userIndex)
    {
        System.out.printf("Enter new task description for task %d: ", userIndex);
        return input.nextLine();
    }

    private String getNewTaskDate(int userIndex)
    {
        System.out.printf("Enter new task due date date(YYYY-MM-DD) for task %d: ", userIndex);
        return input.nextLine();
    }

    private int getEditIndex()
    {
        System.out.println("Which task will you edit?");
        return input.nextInt();
    }

    private void editTasks()
    {
        while(true)
        {
            try
            {
                if(listOfTasks.tasks.isEmpty())
                {
                    System.out.println("Your task list is empty");
                    break;
                }

                int userIndex = getEditIndex();
                input.nextLine();

                String title = " ";
                String description = " ";
                String date  = " ";

                if(listOfTasks.indexIsValid(userIndex))
                {
                    title = getNewTaskTitle(userIndex);
                    description = getNewTaskDescription(userIndex);
                    date = getNewTaskDate(userIndex);
                }

                listOfTasks.editTaskTitle(title, userIndex);
                listOfTasks.editTaskDescription(description, userIndex);
                listOfTasks.editTaskDate(date, userIndex);

                break;
            }
            catch(InvalidIndexException e)
            {
                System.out.println("Warning: Your index is invalid. Please try enter again");
            }
            catch (InvalidTitleException ex)
            {
                System.out.println("Warning: Your Title is invalid. Please enter again.");
            }
            catch (InvalidDateException ex)
            {
                System.out.println("Warning: Your Date is invalid. Please enter again");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /*Option 4) Remove an Item: List Operation Menu*/

    //getting index of the task to be removed from the user
    private int getRemoveIndex()
    {
        System.out.println("Which task will you remove?");
        return input.nextInt();
    }

    private void removeTasks()
    {
        int size = listOfTasks.sizeOfTaskList();

        while(true)
        {
            try
            {
                if(listOfTasks.ListIsEmpty())
                {
                    System.out.println("Your task list is empty");
                    break;
                }

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

    /* Option 5) mark an item as completed*/

    private int getMarkIndex()
    {
        System.out.println("Which task will you mark as complete?");
        return input.nextInt();
    }

    //checking for exception and marking the task as completed
    private void markTaskAsCompleted( )
    {
        while(true)
        {
            try
            {
                if(listOfTasks.ListIsEmpty())
                {
                    System.out.println("Your task list is empty");
                    break;
                }

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

    private void displayUncompletedTasks()
    {
        listOfTasks.displayUncompletedTasks();
    }

    /* option 6: unmark an item as completed*/

    private int getUnmarkIndex()
    {
        System.out.println("Which task will you unmark as complete:?");
        return input.nextInt();
    }

    //checking for exception and unmarking the task as completed
    private void ummarkTaskAsUncompleted( )
    {
        while(true)
        {
            try
            {
                if(listOfTasks.ListIsEmpty())
                {
                    System.out.println("Your task list is empty");
                    break;
                }

                int index = getUnmarkIndex();
                listOfTasks.unmarkingTaskAsUncompleted(index);

                break;

            }
            catch(InvalidIndexException e)
            {
                System.out.println("Warning: Invalid index. Please try enter again");
            }
        }
    }

    private void displayCompletedTasks()
    {
        listOfTasks.displayCompletedTasks();
    }

    /* option7: save the current list*/

    private void writeListOfTasks()
    {
        String filename = getFileNameToSave();
        listOfTasks.writeTaskdata(filename);
    }

    private String getFileNameToSave()
    {
        System.out.println("Enter the file name to save: ");
        return input.nextLine();
    }

    private String getFileNameToLoad()
    {
        System.out.println("Enter the file name to load: ");
        return input.next();
    }

    private void readTaskData()
    {
        while(true)
        {
            try
            {
                String fileName = getFileNameToLoad();
                File input = new File(fileName);
                Scanner scanner = new Scanner(input);

                while (scanner.hasNextLine())
                {
                    String taskData = scanner.nextLine();
                    String[] values = taskData.split(";");

                    String statusOfCompletion;

                    if(values.length <= 3)
                    {
                        statusOfCompletion = " ";
                    }
                    else {
                        statusOfCompletion = values[3];
                    }

                    TaskItem task = new TaskItem(values[0], values[1], values[2],statusOfCompletion);
                    storeTaskItem(task);
                }

                break;
            }
            catch (FileNotFoundException fileNotFoundException)
            {
                System.out.println("Warning: Unable to find the file. Please try again");
            }
        }
    }
}
