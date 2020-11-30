import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    /*Test 1*/
    @Test
    public void constructorFailsWithInvalidDueDate()
    {
        assertThrows(InvalidDateException.class, () -> new TaskItem("My First Task", "Programming Assignment 4", "01-02-2020", " "));
    }

    /*Test 2*/
    @Test
    public void constructorFailsWithInvalidTitle()
    {
        assertThrows(InvalidTitleException.class, () -> new TaskItem("", "Programming Assignment 4", "2020-11-20", " "));
    }

    /*Test 3*/
    @Test
    public void constructorSucceedsWithValidDueDate()
    {
        assertDoesNotThrow(() -> new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " "));
    }

    /*Test 4*/
    @Test
    public void constructorSucceedsWithValidTitle()
    {
        assertDoesNotThrow(() -> new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " "));
    }

    /*editing description succeeds with Expected value*/
    @Test
    public void editingDescriptionSucceedsWithExpectedValue()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");

        assertDoesNotThrow(() -> data.setDescription("Programming assignment 5: TaskList and ContactList"));

        assertEquals("Programming assignment 5: TaskList and ContactList", data.getDescription());
    }

    /*Test 5*/
    @Test
    public void editingDueDateFailsWithInValidDateFormat()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");

        assertThrows(InvalidDateException.class, () -> data.setDate("20-11-2020"));
    }

    /*Task 6*/
    @Test
    public void editingDueDateFailsWithInValidYYYYMMDD()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");

        assertThrows(InvalidDateException.class, () -> data.setDate("3567-13-48"));
    }

    @Test
    public void editingDueDateSucceedsWithExpectedValue()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");

        assertDoesNotThrow(() -> data.setDate("2024-12-22"));

        assertEquals("2024-12-22", data.getDate());
    }

    /*Task 7*/
    @Test
    public void editingTitleFailsWithEmptyString()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");

        assertThrows(InvalidTitleException.class, () -> data.setTitle(""));
    }

    /*Task 8*/
    @Test
    public void editingTitleSucceedsWithExpectedValue()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");

        assertDoesNotThrow(() -> data.setTitle("Task 2: Assignment 5"));

        assertEquals("Task 2: Assignment 5", data.getTitle());
    }

    /*ADDED SIX NEW TEST CASES*/

    @Test
    public void creatingTaskItemFailsWithInvalidDueDateWrongFormat()
    {
        assertThrows(InvalidDateException.class, () -> new TaskItem("My First Task", "Programming Assignment 4", "01-02-2020", " "));
    }

    @Test
    public void creatingTaskItemFailsWithInvalidDueDateNoInput()
    {
        assertThrows(InvalidDateException.class, () -> new TaskItem("My First Task", "Programming Assignment 4", " ", " "));
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitleUsingAssertEquals() throws InvalidTitleException
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");
        try
        {
            data.setTitle("");
        }
        catch(InvalidTitleException e)
        {
            assertNotEquals("", data.getTitle());
        }
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitleUsingAssertEquals()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");
        data.setTitle("Updated first task");
        assertEquals("Updated first task", data.getTitle());

    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDateUsingAssertEquals()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");

        try
        {
            data.setDate(" ");
        }
        catch(InvalidDateException e)
        {
            assertEquals("2020-11-20", data.getDate());
        }
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDateUsingAssertEquals()
    {
        TaskItem data = new TaskItem("My First Task", "Programming Assignment 4", "2020-11-20", " ");
        data.setDate("2020-12-24");
        assertEquals("2020-12-24", data.getDate());
    }

}