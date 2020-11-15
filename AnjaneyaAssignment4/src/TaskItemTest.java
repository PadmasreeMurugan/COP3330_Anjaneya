import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest
{
    /*creatingTaskItemFailsWithInvalidDueDate()
creatingTaskItemFailsWithInvalidTitle()
creatingTaskItemSucceedsWithValidDueDate()
creatingTaskItemSucceedsWithValidTitle()
settingTaskItemDueDateFailsWithInvalidDate()
settingTaskItemDueDateSucceedsWithValidDate()
settingTaskItemTitleFailsWithInvalidTitle()
settingTaskItemTitleSucceedsWithValidTitle()*/

    @Test
    public void creatingTaskItemFailsWithInvalidTitle()
    {
        assertThrows(InvalidTitleException.class, () -> new TaskItem("", "Programming Assignment 4", "2020-11-11"));
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle()
    {
        assertDoesNotThrow(() -> new TaskItem("My First Task", "Programming Assignment 4", "2020-11-11"));
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-11");

        assertThrows(InvalidTitleException.class, () -> data.setTitle(""));
    }

    @Test
    public void settingTaskItemSucceedsWithValidtitle()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-11");

        assertDoesNotThrow(() -> data.setTitle("My First Task"));
    }







}