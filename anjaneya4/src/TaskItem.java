public class TaskItem
{
    private String title;
    private String description;
    private String date;

    public TaskItem (String title,String description, String date)
    {
        if (isTitleValid(title))
        {
            this.title = title;
        }
        else throw new InvalidTitleException("name is not valid");

        this.description = description;

        if (isDateValid(date))
        {
            this.date = date;
        }
        else throw new InvalidDateException("Date is not valid");
    }

    private boolean isTaskItemValid(String title, String description, String date)
    {
        return isTitleValid(title) && isDateValid(date);
    }

    private boolean isTitleValid(String title)
    {
        return title.length() > 0;
    }

    private boolean isDateValid(String Date)
    {
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


