import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskItem {
    private String title;
    private String description;
    private String date;
    private String statusOfCompletion;

    //Constructor
    public TaskItem(String title, String description, String date, String statusOfCompletion)
    {

        setTitle(title);
        setDescription(description);
        setDate(date);
        setCompletionStatus(statusOfCompletion);
    }

    public void setTitle(String title)
    {
        if (isTitleValid(title))
        {
            this.title = title;
        }
        else throw new InvalidTitleException("name is not valid");
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDate(String date)
    {
        if (isDateValid(date))
        {
            this.date = date;
        }
        else throw new InvalidDateException("Date is not valid");
    }

    public void setCompletionStatus(String Mark)
    {
        this.statusOfCompletion = Mark;
    }


    private boolean isTitleValid(String title)
    {
        return (title.length() > 0);
    }

    private boolean isDateValid(String date)
    {
        try
        {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        }
        catch (DateTimeParseException e)
        {
            return false;
        }
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getCompletionStatus()
    {
        return this.statusOfCompletion;
    }
}

class InvalidTitleException extends IllegalArgumentException
{
    public InvalidTitleException(String msg)
    {
        super(msg);
    }

}

class InvalidDateException extends IllegalArgumentException
{
    public InvalidDateException(String msg)
    {
        super(msg);
    }
}