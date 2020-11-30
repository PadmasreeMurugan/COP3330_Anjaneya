import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskApp extends App {

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
        private void processMainMenu()
        {
            int userResponse = getContinueResponseForMain();

            while(shouldContinue(userResponse))
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
                    addTaskItemData();
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
            listOfTasks.removeAllTaskItem();

            if(listOfTasks.ListIsEmpty())
            {
                readTaskData();
                System.out.println("task list has been loaded");
                createNewList();
            }

            else
            {
                listOfTasks.removeAllTaskItem();
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
        private void addTaskItemData()
        {
            TaskItem data = null;

            try
            {
                String title = getTaskTitle();
                String description = getTaskDescription();
                String date = getTaskDate();
                String completionMark = getCompletionMark();

                data = new TaskItem(title,description, date, completionMark);
                storeTaskItem(data);
            }
            catch (InvalidTitleException ex)
            {
                listOfTasks.remove(data);
                System.out.println("Warning: title must be at least 1 character long; Please enter again");
            }
            catch (InvalidDateException ex)
            {
                listOfTasks.remove(data);
                System.out.println("Warning: invalid due date; Please enter again");
            }
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
            catch(InvalidTaskIndexException e)
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
            catch(InvalidTaskIndexException e)
            {
                System.out.println("Warning: Invalid index. no task can be removed");
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
            catch(InvalidTaskIndexException e)
            {
                System.out.println("Warning: Invalid index. Please try enter again");
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

        private void displayCompletedTasks()
        {
            listOfTasks.displayCompletedTasks();
        }

        /* option7: save the current list*/

        private void writeListOfTasks()
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
            }
            catch (FileNotFoundException fileNotFoundException)
            {
                System.out.println("Warning: Unable to find the file. File cannot be loaded");
            }
        }
    }

