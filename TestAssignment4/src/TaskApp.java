import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskApp extends App
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

    //display main menu  - inherited from App class
    //get response for main menu - inherited from App class
    //check should continue for main menu - inherited from App class

    //processes main menu options
    public void processMainMenu()
    {
        int userResponse = getContinueResponseForMain();

        while(shouldContinueForMainMenu(userResponse))
        {
            if(userResponse == 1)
            {
                System.out.println("new task list has been created");
                listOfTasks.removeAllTaskItem();
                createNewList();

            }
            if(userResponse == 2)
            {
                loadExistingList();
            }
            userResponse = getContinueResponseForMain();
        }
    }

    //display list operation menu - inherited from App class
    //getcontinueResponse for list operation menu

    //user enters create a list
    private void createNewList()
    {
        int userResponse = getContinueResponseForListMenu();

        TaskItem data = null;

        while(shouldContinueList(userResponse))
        {
            if(userResponse == 1)
            {
                viewList();
            }
            if(userResponse == 2)
            {
                String title = getTaskTitle();
                String description = getTaskDescription();
                String date = getTaskDate();
                String completionStatus = getCompletionMark();

                addItem(title, description, date, completionStatus);
            }

            if(userResponse == 3)
            {
                editItem();
               /* displayCurrentTasks();
                editTasks();*/
            }

            if(userResponse == 4)
            {
                removeItem();
               /* displayCurrentTasks();
                removeTasks();*/
            }

            if(userResponse == 5)
            {
                markItemAsCompleted();
                /*displayUncompletedTasks();
                markTaskAsCompleted();*/
            }

            if(userResponse == 6)
            {
                unMarkItemAsCompleted();

                /*displayCompletedTasks();
                ummarkTaskAsUncompleted();*/
            }

            if(userResponse == 7)
            {
                saveListOfTasks();
                System.out.println("task list has been saved");
            }
            userResponse = getContinueResponseForListMenu();
        }
    }

    /*Option 1) view the list: List Operation Menu*/

    @Override
    public void viewList()
    {
        listOfTasks.displayCurrentTasks();
    }

    /*private void displayCurrentTasks()
    {
        viewList();
    }*/

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

    @Override
    public void addItem(String title, String description, String date, String completionStatus)
    {
        listOfTasks.addingTaskItem(title, description, date, completionStatus);
    }

    //getting an task item from user
   /* private void addTaskItemData(String title, String description, String date, String completionStatus)
    {
       addItem(title, description, date, completionStatus);
    }*/

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

    @Override
    public void editItem()
    {
        try
        {
            if(listOfTasks.tasks.isEmpty())
            {
                System.out.println("Your task list is empty. Please add an item to edit");
            }

            else{

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
            }

        }
        catch(InvalidIndexException e)
        {
            System.out.println("Warning: Your index is invalid. no task can be edited\n");
        }
        catch (InvalidTitleException ex)
        {
            System.out.println("Warning: Your Title is invalid. Task is not edited\n");
        }
        catch (InvalidDateException ex)
        {
            System.out.println("Warning: Your Date is invalid. Task is not edited\n");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

  /*  private void editTasks()
    {
        editItem();
    }*/

    /*Option 4) Remove an Item: List Operation Menu*/

    //getting index of the task to be removed from the user
    private int getRemoveIndex()
    {
        System.out.println("Which task will you remove?");
        return input.nextInt();
    }

    @Override
    public void removeItem()
    {
        int size = listOfTasks.sizeOfTaskList();

        try
        {
            if(listOfTasks.ListIsEmpty())
            {
                System.out.println("Your task list is empty. Please add an task item to remove\n");
            }

            else
            {
                int index = getRemoveIndex();
                listOfTasks.removeTaskItem(index);
            }
        }
        catch(InvalidIndexException e)
        {
            System.out.println("Warning: Invalid index. no task can be removed");
        }
    }

    private void removeTasks()
    {
       removeItem();
    }

    /* Option 5) mark an item as completed*/

    private int getMarkIndex()
    {
        System.out.println("Which task will you mark as complete?");
        return input.nextInt();
    }

    @Override
    public void markItemAsCompleted()
    {
        try
        {
            if(listOfTasks.ListIsEmpty())
            {
                System.out.println("Your task list is empty. Please add a task item to mark as completed");
            }

            else {

                int index = getMarkIndex();
                listOfTasks.markingTaskCompleted(index);
            }
        }
        catch(InvalidIndexException e)
        {
            System.out.println("Warning: Invalid index. Please try enter again");
        }
    }

    //checking for exception and marking the task as completed
    private void markTaskAsCompleted( )
    {
        markItemAsCompleted();
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

    @Override
    public void unMarkItemAsCompleted()
    {
        try
        {
            if(listOfTasks.ListIsEmpty())
            {
                System.out.println("Your task list is empty. Please add a task item to unmark as completed");
            }

            else
            {
                int index = getUnmarkIndex();
                listOfTasks.unmarkingTaskAsUncompleted(index);
            }
        }
        catch(InvalidIndexException e)
        {
            System.out.println("Warning: Invalid index. Please try enter again");
        }
    }

    //checking for exception and unmarking the task as completed
    private void ummarkTaskAsUncompleted( )
    {
        unMarkItemAsCompleted();
    }

    private void displayCompletedTasks()
    {
        listOfTasks.displayCompletedTasks();
    }

    /* option7: save the current list*/

    @Override
    public void writeToFileAndSaveTheList()
    {
        if(listOfTasks.ListIsEmpty())
        {
            System.out.println("Your task list should contain one or more task items. task list cannot be saved\n");
        }
        else{
            String filename = getFileNameToSave();
            listOfTasks.writeTaskdata(filename);
            System.out.println("task list has been saved\n");
        }
    }

    private void saveListOfTasks()
    {
        writeToFileAndSaveTheList();
    }

    //getFileNameToSave - inherited from App class
    //getFilenametoLoad - inherited from App class

    private void readTasks(String fileName)
    {
        try
        {
            File input = new File(fileName);
            Scanner scanner = new Scanner(input);

            listOfTasks.readTasksLineByLine(scanner);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Warning: Unable to find the file. File cannot be loaded");
        }
    }

    @Override
    //user enters load an existing list
    public void loadDataFromFile()
    {
        listOfTasks.removeAllTaskItem();

        try
        {
            if(listOfTasks.ListIsEmpty())
            {
                String fileName = getFileNameToLoad();
                readTasks(fileName);
                System.out.println("task list has been loaded");
                createNewList();
            }

            else
            {
                listOfTasks.removeAllTaskItem();
                String fileName = getFileNameToLoad();
                readTasks(fileName);
                System.out.println("task list has been loaded");
                createNewList();
            }
        }
        catch(Exception ex)
        {
            System.out.println("Please load the file consisting of task list and not contact list");
            System.out.println("File cannot be loaded");
        }


    }

    private void loadExistingList()
    {
        loadDataFromFile();
    }
}

