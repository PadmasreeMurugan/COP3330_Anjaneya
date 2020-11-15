import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class TaskList
{
    List <TaskItem> tasks = tasks = new ArrayList<>();

    public void add(TaskItem data)
    {
        tasks.add(data);
    }

    public void displayTasks()
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            TaskItem data = tasks.get(i);
            System.out.printf("%s %s %s %n", data.getTitle(), data.getDescription(), data.getDate());
        }
    }

    public void editTaskItemTitle(int index, String title)
    {
        TaskItem data = tasks.get(index);
        data.setTitle(title);
    }

    public void editTaskItemDescription(int index, String description)
    {
        TaskItem data = tasks.get(index);
        data.setDescription(description);
    }

    public void editTaskItemDate(int index, String date)
    {
        TaskItem data = tasks.get(index);
        data.setDate(date);
    }

    public String getTitle(int index)
    {
        TaskItem data = tasks.get(index);
        return data.getTitle();
    }

    public String getDate(int index)
    {
        TaskItem data = tasks.get(index);
        return data.getDate();
    }

    public String getDescription(int index)
    {
        TaskItem data = tasks.get(index);
        return data.getDescription();
    }

    public void write(String filename)
    {
        try (Formatter output = new Formatter(filename))
        {
            for (int i = 0; i < tasks.size(); i++)
            {
                TaskItem data = tasks.get(i);
                output.format("%s %s %s%n", data.getTitle(), data.getDescription(), data.getDate());
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
}



