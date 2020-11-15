import java.util.ArrayList;
import java.util.List;

public class TaskList
{
    ArrayList<TaskItem> tasks;

    public TaskList()
    {
        tasks = new ArrayList<>();
    }

    public void add(TaskItem data)
    {
        tasks.add(data);
    }

    public int sizeOfTaskList()
    {
        return tasks.size();
    }

    public boolean ListIsEmpty()
    {
        return tasks.isEmpty();
    }

    public void remove(TaskItem data)
    {
        tasks.remove(data);
    }

    public void displayCurrentTasks()
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            TaskItem data = tasks.get(i);
            System.out.format("%d) [%s] %s: %s %n", i, data.getDate(), data.getTitle(), data.getDescription());
        }
    }

    public boolean indexIsValid(int userIndex)
    {
        return (userIndex >= 0 && userIndex < tasks.size());
    }

    public void removeTaskItem(int userIndex)
    {
        if(indexIsValid(userIndex) && (!ListIsEmpty()))
        {
            remove(tasks.get(userIndex));
        }
        else throw new InvalidIndexException("Index is not valid");
    }

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
        }
        else throw new InvalidIndexException("Index is Invalid");
        return description;
    }

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

    public void markingTaskCompleted(int userIndex)
    {
        if(indexIsValid(userIndex))
        {

        }
    }

}

class InvalidIndexException extends IndexOutOfBoundsException
{
    public InvalidIndexException(String msg)
        {
            super(msg);
        }

}


