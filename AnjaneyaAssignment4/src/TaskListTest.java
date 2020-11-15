import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest
{
    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex()
    {
        TaskList data = new TaskList();

        assertThrows(IndexOutOfBoundsException.class, () -> data.getTitle(-2));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex()
    {
        TaskList data = new TaskList();

        assertDoesNotThrow(() -> data.getTitle(0));
    }

    @Test
    public void editingTaskItemTitleChangesValue()
    {
        TaskList data = new TaskList();

        data.editTaskItemTitle(0, "New Task");

        assertEquals("New Task",data.getTitle(0));
    }
}