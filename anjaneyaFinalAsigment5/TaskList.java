import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class TaskList
{
    //creating an arraylist of task item objects
    ArrayList<TaskItem> tasks;

    //constructor
    public TaskList()
    {
        tasks = new ArrayList<>();
    }

    //adding the task item data to the list of tasks
    public void add(TaskItem data)
    {
        tasks.add(data);
    }

    //getting the size of the tasks
    public int sizeOfTaskList()
    {
        return tasks.size();
    }

    //checking whether the list is empty
    public boolean ListIsEmpty()
    {
        return tasks.isEmpty();
    }

    //removing a task item from the list of tasks
    public void remove(TaskItem data)
    {
        tasks.remove(data);
    }

    //checking whether the index is valid
    public boolean indexIsValid(int userIndex)
    {
        return (userIndex >= 0 && userIndex < tasks.size());
    }

    /* OPTION1: VIEW THE LIST*/

    //displaying the current tasks
    public void displayCurrentTasks()
    {
        System.out.println("Current Tasks");
        System.out.println("--------------");

        if(ListIsEmpty())
        {
            System.out.println("Warning: task list is empty; please add a task item");
        }

        try {
            if (sizeOfTaskList() >= 1) {
                for (int i = 0; i < tasks.size(); i++) {
                    TaskItem data = tasks.get(i);

                    if ((data.getCompletionStatus().equals("#"))) {
                        System.out.format("%d) [%s] %s: %s %n", i, data.getDate(), data.getTitle(), data.getDescription());
                    }
                    if ((data.getCompletionStatus().equals("*** "))){
                        System.out.format("%d) %s [%s] %s: %s %n", i, data.getCompletionStatus(), data.getDate(), data.getTitle(), data.getDescription());
                    }
                }
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("Warning: task list is empty; please add a task item");
        }
    }

    /*OPTION2: ADD AN ITEM*/

    public TaskItem addingTaskItem(String title, String description, String date, String completionMark)
    {
        TaskItem data = null;

        try
        {
            data = new TaskItem(title,description, date, completionMark);
            add(data);
        }
        catch (InvalidTitleException ex)
        {
            tasks.remove(data);
            System.out.println("Warning: title must be at least 1 character long; task not created");
        }
        catch (InvalidDateException ex)
        {
            tasks.remove(data);
            System.out.println("Warning: invalid due date; task not created");
        }
        return data;
    }

    /*OPTION3: EDIT AN ITEM*/

    public void editTaskTitle(String title, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setTitle(title);
        }
        else throw new InvalidTaskIndexException("Index is Invalid");
    }

    public void editTaskDescription(String description, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setDescription(description);
        }
        else throw new InvalidTaskIndexException("Index is Invalid");
    }

    public void editTaskDate(String date, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setDate(date);
        }
        else throw new InvalidTaskIndexException("Index is Invalid");
    }

    /*OPTION 4: REMOVE AN ITEM*/

    //checking whether the index is valid and performing the remove operation
    public void removeTaskItem(int userIndex)
    {
        if(indexIsValid(userIndex) && (!ListIsEmpty()))
        {
            remove(tasks.get(userIndex));
        }
        else throw new InvalidTaskIndexException("Index is not valid");
    }

    public void removeAllTaskItem()
    {
        tasks.removeAll(tasks);
    }

    /*OPTION5: MARK AN ITEM AS COMPLETED*/

    //displaying the uncompleted tasks
    public void displayUncompletedTasks()
    {
        System.out.println("Uncompleted Tasks");
        System.out.println("-----------------");
        for (int i = 0; i < tasks.size(); i++)
        {
            TaskItem data = tasks.get(i);

            if (data.getCompletionStatus().equals("#"))
            {
                System.out.format("%d) [%s] %s: %s %n", i, data.getDate(), data.getTitle(), data.getDescription());
            }
        }
    }

    //checking whether the entered index is valid and is equal to any of the indexes of the uncompleted tasks
    public boolean isUnCompletedTaskIndexValid(int userIndex)
    {
        int count = 0;

        if(indexIsValid(userIndex))
        {
            for (int i = 0; i < tasks.size(); i++)
            {
                TaskItem data = tasks.get(i);

                if (data.getCompletionStatus().equals("#"))
                {
                    if(i == userIndex)
                    {
                        return true;
                    }
                    else
                    {
                        continue;
                    }
                }

            }
            return false;
        }

        return false;
    }

    public void markingTaskCompleted(int userIndex)
    {
        if(isUnCompletedTaskIndexValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setCompletionStatus("*** ");
        }
        else throw new InvalidUncompletedTaskIndexException("Index is Invalid");
    }

    /*OPTION 6: UNMARK AN ITEM AS COMPLETED*/

    //displaying the completed tasks
    public void displayCompletedTasks()
    {
        System.out.println("Completed Tasks");
        System.out.println("-----------------");
        for (int i = 0; i < tasks.size(); i++)
        {
            TaskItem data = tasks.get(i);

            if (data.getCompletionStatus().equals("*** ")) {
                System.out.format("%d) [%s] %s: %s %n", i, data.getDate(), data.getTitle(), data.getDescription());
            }
        }
    }

    //checking whether the entered index is valid and is equal to any of the indexes of the completed tasks
    public boolean isCompletedTaskIndexValid(int userIndex)
    {
        int count = 0;

        if(indexIsValid(userIndex))
        {
            for (int i = 0; i < tasks.size(); i++)
            {
                TaskItem data = tasks.get(i);

                if (data.getCompletionStatus().equals("*** "))
                {
                    if(i == userIndex)
                    {
                        return true;
                    }
                    else
                    {
                        continue;
                    }
                }

            }
            return false;
        }

        return false;
    }


    public void unmarkingTaskAsUncompleted(int userIndex)
    {
        if(isCompletedTaskIndexValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setCompletionStatus("#");
        }
        else throw new InvalidCompletedTaskIndexException("Index is Invalid");
    }

    /*OPTION 7: SAVE THE LIST*/

    public void writeTaskdata(String fileName)
    {
            try(Formatter output = new Formatter(fileName))
            {
                for (int i = 0; i < tasks.size(); i++)
                {
                    TaskItem data = tasks.get(i);

                    output.format("%s;%s;%s;%s;%n",data.getTitle(),data.getDescription(), data.getDate(), data.getCompletionStatus());
                }
            }
            catch (FileNotFoundException ex)
            {
                System.out.println("Warning: Unable to find the file. File cannot be saved");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
    }

    public void readTasks(String fileName)
    {
        try
        {
            File input = new File(fileName);
            Scanner scanner = new Scanner(input);

            readTasksLineByLine(scanner);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Warning: Unable to find the file. file cannot be loaded");
        }
    }

    public void readTasksLineByLine(Scanner scanner)
    {
        while (scanner.hasNextLine())
        {
            String taskData = scanner.nextLine();
            String[] values = taskData.split(";");

            String statusOfCompletion;

            if(values.length <= 3)
            {
                statusOfCompletion = "#";
            }
            else {
                statusOfCompletion = values[3];
            }

            TaskItem task = new TaskItem(values[0], values[1], values[2], statusOfCompletion);
            tasks.add(task);
        }
    }

    //getting task title of particular index
    public String getTaskTitle(int userIndex)
    {
        String title = "";

        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            if(!(data.getTitle().isEmpty()))
            {
                title = data.getTitle();
            }
        }
        else throw new InvalidTaskIndexException("Index is Invalid");
        return title;
    }

    //getting task description of particular index
    public String getTaskDescription(int userIndex)
    {
        String description = "";

        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            if(!(data.getDescription().isEmpty()))
            {
                description = data.getDescription();
            }
            return description;
        }
        else throw new InvalidTaskIndexException("Index is Invalid");

    }

    //getting task date of particular index
    public String getTaskDate(int userIndex)
    {
        String date = "";

        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            if(!(data.getDate().isEmpty()))
            {
                date = data.getDate();
            }
        }
        else throw new InvalidTaskIndexException("Index is Invalid");
        return date;
    }

    //getting the completionStatus of particular index
    public String getMark(int userIndex)
    {
        String completionMark;

        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            completionMark = data.getCompletionStatus();
            return completionMark;
        }
        else throw new InvalidTaskIndexException("Index is Invalid");

    }
}

class InvalidTaskIndexException extends IndexOutOfBoundsException
{
    public InvalidTaskIndexException(String msg)
    {
        super(msg);
    }

}

class InvalidUncompletedTaskIndexException extends IndexOutOfBoundsException
{
    public InvalidUncompletedTaskIndexException(String msg)
    {
        super(msg);
    }
}

class InvalidCompletedTaskIndexException extends IndexOutOfBoundsException
{
    public InvalidCompletedTaskIndexException(String msg)
    {
        super(msg);
    }
}






