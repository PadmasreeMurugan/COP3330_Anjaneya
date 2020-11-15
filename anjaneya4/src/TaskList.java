
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class TaskList
{
    private static Scanner input1 = new Scanner(System.in);

    private ArrayList<TaskItem> taskdata = new ArrayList<>();

    public void processOption1(ArrayList<TaskItem> taskdata)
    {
        for(int i = 0; i < taskdata.size(); i++)
        {
            TaskItem data = taskdata.get(i);
            System.out.printf("%d) [%s] %s: %s %n", i, data.getDate(), data.getTitle(), data.getDescription());
        }
    }

    public void processOption2()
    {
        TaskItem data = getTaskData();

        storeTaskData(data);

        writeTaskData(taskdata);
    }

    public TaskItem getTaskData()
    {
        TaskItem data = null;
        while(true)
        {
            try
            {
                getTitle();
                String title = input1.nextLine();

                String description = getDescription();

                String date = getDate();

                data = new TaskItem(title, description, date);
                break;
            }
            catch(InvalidTitleException ex)
            {
                System.out.println("Warning: Your title is invalid");
            }
            catch(InvalidDateException ex)
            {
                System.out.println("Warning: Your date is invalid");
            }
        }
        return data;
    }

    public void getTitle()
    {
        System.out.println("Task title: ");
    }

    public String getDescription()
    {
        System.out.println("Task Description: ");
        return input1.nextLine();
    }

    public String getDate()
    {
        System.out.println("Task Date: ");
        return input1.nextLine();
    }

    public void storeTaskData(TaskItem data)
    {
        taskdata.add(data);
    }

    public void writeTaskData(ArrayList<TaskItem> taskdata)
    {
        try(Formatter output = new Formatter("tasks.txt"))
        {
            for(int i = 0; i < taskdata.size(); i++)
            {
                TaskItem data = taskdata.get(i);
                output.format("%s %s %s %n", data.getTitle(), data.getDescription(), data.getDate());
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

    public void processMainMenu1()
    {
        int continueResponse1 = askShouldContinueList();
        input1.nextLine();

        while (true)
        {
            if(continueResponse1 == 8)
            {
                break;
            }
            else if(continueResponse1 == 1)
            {
                processOption1(taskdata);
            }
            else if(continueResponse1 == 2)
            {
                processOption2();
            }

            continueResponse1 = askShouldContinueList();
            input1.nextLine();
        }

    }

    public void displayListOperationMenu()
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

    public int askShouldContinueList()
    {
        displayListOperationMenu();
        return input1.nextInt();
    }
}

