import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskItem
{
    private String title;
    private String description;
    private String date;

    public TaskItem (String title,String description, String date)
    {
        setTitle(title);

        setDescription(description);

        setDate(date);
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

    private boolean isTitleValid(String title)
    {
        return (title.length() > 0);
    }

    private boolean isDateValid(String date)
    {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
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


