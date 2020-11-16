import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class TaskList implements Serializable
{
    //creating an arralist of taskitem objects
    ArrayList<TaskItem> tasks;

    //constructor
    public TaskList()
    {
        tasks = new ArrayList<>();
    }

    //adding the taskitem data to the list of tasks
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

    //removing a taskitem from the list of tasks
    public void remove(TaskItem data)
    {
        tasks.remove(data);
    }

    //displaying the current tasks
    public void displayCurrentTasks()
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            TaskItem data = tasks.get(i);
            if(data.getCompletionStatus() == null)
            {
                System.out.format("%d) [%s] %s: %s %n", i, data.getDate(), data.getTitle(), data.getDescription());
            }
            else
                {
                    System.out.format("%d) %s [%s] %s: %s %n", i,data.getCompletionStatus(), data.getDate(), data.getTitle(), data.getDescription());
            }

        }
    }

    //displaying the uncompleted tasks
    public void displayUncompletedTasks()
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            TaskItem data = tasks.get(i);

            if (data.getCompletionStatus() == null)
            {
                System.out.format("%d) [%s] %s: %s %n", i, data.getDate(), data.getTitle(), data.getDescription());
            }
        }
    }

    //displaying the completed tasks
    public void displayCompletedTasks()
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            TaskItem data = tasks.get(i);

            if (data.getCompletionStatus() != null)
            {
                System.out.format("%d) [%s] %s: %s %n", i, data.getDate(), data.getTitle(), data.getDescription());
            }
        }

    }

    //checking whether the index is valid
    public boolean indexIsValid(int userIndex)
    {
        return (userIndex >= 0 && userIndex < tasks.size());
    }

    //checking whther the index is valid and performing the remove operation
    public void removeTaskItem(int userIndex)
    {
        if(indexIsValid(userIndex) && (!ListIsEmpty()))
        {
            remove(tasks.get(userIndex));
        }
        else throw new InvalidIndexException("Index is not valid");
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
        else throw new InvalidIndexException("Index is Invalid");
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
        else throw new InvalidIndexException("Index is Invalid");

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
        else throw new InvalidIndexException("Index is Invalid");
        return date;
    }

    public String getMark(int userIndex)
    {
        String completionMark;

        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            completionMark = data.getCompletionStatus();
            return completionMark;
        }
        else throw new InvalidIndexException("Index is Invalid");

    }

    public void markingTaskCompleted(int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setCompletionStatus("*** ");
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    public void unmarkingTaskAsUncompleted(int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setCompletionStatus(null);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    public void editTaskTitle(String title, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setTitle(title);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    public void editTaskDescription(String description, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setDescription(description);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    public void editTaskDate(String date, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            TaskItem data = tasks.get(userIndex);

            data.setDate(date);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    public void writeTaskdata(String fileName)
    {
        try(Formatter output = new Formatter(fileName))
        {
            for (int i = 0; i < tasks.size(); i++)
            {
                TaskItem data = tasks.get(i);

                output.format("%s %s %s %s %n",data.getTitle(),data.getDescription(), data.getDate(), data.getCompletionStatus());

            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Unable to find the file...");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void readTaskData(String FileName)
    {
        try
        {
            File myObj = new File("tasks.txt");
            Scanner sc = new Scanner(myObj);



            while (sc.hasNextLine())
            {
                String data = sc.nextLine();
                String[] values = data.split(" ");


                String statusOfCompletion;

                if(values.length <= 3)
                {
                    statusOfCompletion = null;
                }
                else
                {
                    statusOfCompletion = values[3];
                }

                TaskItem task = new TaskItem(values[0], values[1], values[2],statusOfCompletion );
                tasks.add(task);
            }

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

    }



   /* public void readTaskdata(String FileName)
    {
        try
        {
            Scanner sc = new Scanner(new File(FileName));
            List<String> lines = new ArrayList<String>();
            while (sc.hasNextLine())
            {
                lines.add(sc.nextLine());
            }

            String[] arr = lines.toArray(new String[lines.size()]);
            String title = " ";
            String description = " ";
            String date = " ";
            String completionStatus = " ";

            TaskItem data = null;

            for(int i = 0; i < arr.length; i = i+3)
            {
                for(int j = i; j <= (i+3); j++)
                {
                    if(j == i)
                    {
                       arr[j] = title;
                    }

                     if(j == (i+1))
                    {
                        arr[j] = description;
                    }
                    if(j == (i+2))
                    {
                         arr[j]= date;
                    }
                    if(j == (i+3))
                    {
                        arr[j] =  completionStatus;
                    }
                }
                data = new TaskItem(title, description, date, completionStatus);
                tasks.add(data);


            System.out.printf("%s", arr[0]);

            sc.close();

           Scanner input = new Scanner(new File(FileName));

            input.useDelimiter(" +"); //delimitor is one or more spaces

            while(input.hasNext())
            {
                data = input.next();
            }

            input.close();

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}


class InvalidIndexException extends IndexOutOfBoundsException
{
    public InvalidIndexException(String msg)
    {
        super(msg);
    }

}

