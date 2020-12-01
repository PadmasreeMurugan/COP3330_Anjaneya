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
    //getcontinueResponseForListOperationMenu - inherited from App class
    //shouldContinueList - inherited from App class

    /*Create a new list*/

    private void createNewList()
    {
        int userResponse = getContinueResponseForListMenu();

        TaskItem data = null;

        while(shouldContinueList(userResponse))
        {
            if(userResponse == 1)
            {
                viewCurrentList();
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
            }
            if(userResponse == 4)
            {
                removeItem();
            }
            if(userResponse == 5)
            {
                markItemAsCompleted();
            }
            if(userResponse == 6)
            {
                unMarkItemAsCompleted();
            }
            if(userResponse == 7)
            {
                saveListOfTasks();
            }
            userResponse = getContinueResponseForListMenu();
        }
    }

    /*Option 1) view the list: List Operation Menu*/
    @Override
    public void viewCurrentList()
    {
        listOfTasks.displayCurrentTasks();
    }

    /*Option 2) add an item: List Operation Menu*/

    //getting task title, description, date from user
    private String getTaskTitle()
    {
        System.out.print("Task title: ");
        return input.nextLine();
    }

    private String getTaskDescription()
    {
        System.out.print("Task Description: ");
        return input.nextLine();
    }

    private String getTaskDate()
    {
        System.out.print("Task Due Date(YYYY-MM-DD): ");
        return input.nextLine();
    }

    private String getCompletionMark()
    {
        return "#";
    }

    @Override
    public void addItem(String title, String description, String date, String completionStatus)
    {
        listOfTasks.addingTaskItem(title, description, date, completionStatus);
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
        System.out.print("\nWhich task will you edit? ");
        return input.nextInt();
    }

    @Override
    public void editItem()
    {
        try
        {
            if(listOfTasks.tasks.isEmpty())
            {
                System.out.println("Warning: task list is empty; please add an item to edit");
            }

            else {
                listOfTasks.displayCurrentTasks();

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
        catch(InvalidTaskIndexException e)
        {
            System.out.println("Warning: index is invalid; no task can be edited");
        }
        catch (InvalidTitleException ex)
        {
            System.out.println("Warning: title is invalid; task is not edited");
        }
        catch (InvalidDateException ex)
        {
            System.out.println("Warning: date is invalid; task is not edited");
        }
        catch (Exception ex)
        {
            System.out.println("Warning: invalid input; task is not edited");
        }
    }

    /*Option 4) Remove an Item: List Operation Menu*/

    //getting index of the task to be removed from the user
    private int getRemoveIndex()
    {
        System.out.print("\nWhich task will you remove? ");
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
                System.out.println("Warning: task list is empty; please add a task item to remove");
            }
            else
            {
                listOfTasks.displayCurrentTasks();
                int index = getRemoveIndex();
                input.nextLine();
                listOfTasks.removeTaskItem(index);
            }
        }
        catch(InvalidTaskIndexException e)
        {
            System.out.println("Warning: invalid index; no task can be removed");
        }
    }

    /* Option 5) mark an item as completed*/

    private int getMarkIndex()
    {
        System.out.print("\nWhich task will you mark as complete? ");
        return input.nextInt();
    }

    //checking for exception and marking the task as completed
    @Override
    public void markItemAsCompleted()
    {
        try
        {
            if(listOfTasks.ListIsEmpty())
            {
                System.out.println("Warning: task list is empty; please add a task item to mark as completed");
            }
            else {
                displayUncompletedTasks();
                int index = getMarkIndex();
                input.nextLine();
                listOfTasks.markingTaskCompleted(index);
            }
        }
        catch(InvalidTaskIndexException e)
        {
            System.out.println("Warning: index is invalid; no task can be mark as completed");
        }
        catch(InvalidUncompletedTaskIndexException ex)
        {
            System.out.println("Warning: index is invalid; please enter an index from the above uncompleted tasks");
        }
    }

    private void displayUncompletedTasks()
    {
        listOfTasks.displayUncompletedTasks();
    }

    /* option 6: unmark an item as completed*/

    private int getUnmarkIndex()
    {
        System.out.print("\nWhich task will you unmark as complete? ");
        return input.nextInt();
    }

    //checking for exception and unmarking the task as completed
    @Override
    public void unMarkItemAsCompleted()
    {
        try
        {
            if(listOfTasks.ListIsEmpty())
            {
                System.out.println("Warning: task list is empty; please add a task item to unmark as completed");
            }
            else
            {
                displayCompletedTasks();
                int index = getUnmarkIndex();
                input.nextLine();
                listOfTasks.unmarkingTaskAsUncompleted(index);
            }
        }
        catch(InvalidTaskIndexException e)
        {
            System.out.println("Warning: index is invalid; no task can be unmarked");
        }
        catch(InvalidCompletedTaskIndexException ex)
        {
            System.out.println("Warning: index is invalid; please enter an index from the above completed tasks");
        }
    }

    private void displayCompletedTasks()
    {
        listOfTasks.displayCompletedTasks();
    }

    /* option7: save the current list*/

    //writing taskItem data to a file and saving the file
    @Override
    public void writeToFileAndSaveTheList()
    {
        if(listOfTasks.ListIsEmpty())
        {
            System.out.println("Warning: task list should contain one or more task items; task list cannot be saved");
        }
        else{
            String filename = getFileNameToSave();
            listOfTasks.writeTaskdata(filename);
            System.out.println("task list has been saved");
        }
    }

    private void saveListOfTasks()
    {
        writeToFileAndSaveTheList();
    }

    //getFileNameToSave - inherited from App class
    //getFilenametoLoad - inherited from App class

    /*Load an existing list*/

    @Override
    public void loadDataFromFile()
    {
        listOfTasks.removeAllTaskItem();

        try
        {
            if(listOfTasks.ListIsEmpty())
            {
                String fileName = getFileNameToLoad();
                readTasks(fileName);
            }
            else
            {
                listOfTasks.removeAllTaskItem();
                String fileName = getFileNameToLoad();
                readTasks(fileName);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Warning: please load the file consisting of task list; file cannot be loaded");
        }
    }

    private void readTasks(String fileName)
    {
        try
        {
            File input = new File(fileName);
            Scanner scanner = new Scanner(input);

            listOfTasks.readTasksLineByLine(scanner);

            System.out.println("task list has been loaded");
            createNewList();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Warning: Unable to find the file. file cannot be loaded");
        }
    }

    private void loadExistingList()
    {
        loadDataFromFile();
    }
}

