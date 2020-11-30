import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactItemTest
{

    /*creationFailsWithAllBlankValues()
    creationSucceedsWithBlankEmail()
    creationSucceedsWithBlankFirstName()
    creationSucceedsWithBlankLastName()
    creationSucceedsWithBlankPhone()
    creationSucceedsWithNonBlankValues()
    editingFailsWithAllBlankValues()
    editingSucceedsWithBlankEmail()
    editingSucceedsWithBlankFirstName()
    editingSucceedsWithBlankLastName()
    editingSucceedsWithBlankPhone()
    editingSucceedsWithNonBlankValues()
    testToString()*/

    @Test
    public void creationFailsWithAllBlankValues()
    {
        assertThrows(InvalidContactItemException.class, () -> new ContactItem("", "", "", ""));
    }

    @Test
    public void creationSucceedsWithBlankEmail()
    {
        assertDoesNotThrow(() -> new ContactItem("Padmasree", "Murugan", "123-456-7891", ""));
    }

    @Test
    public void creationSucceedsWithBlankFirstName()
    {
        assertDoesNotThrow(() -> new ContactItem("", "Murugan", "123-456-7891", "unknown@cia.gov"));
    }

    @Test
    public void creationSucceedsWithBlankLastName()
    {
        assertDoesNotThrow(() -> new ContactItem("Padmasree", "", "123-456-7891", "unknown@cia.gov"));
    }

    @Test
    public void creationSucceedsWithBlankPhone()
    {
        assertDoesNotThrow(() -> new ContactItem("Padmasree", "Murugan", "", "unknown@cia.gov"));
    }

    @Test
    public void creationSucceedsWithNonBlankValues()
    {
        assertDoesNotThrow(() -> new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov"));
    }

    @Test
    public void editingFailsWithAllBlankValues()
    {
        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");

        assertThrows(InvalidContactItemException.class, () -> data.setContactItem("", "", "", ""));
    }

    /* The below four tests edits a valid value to a blank value*/

    @Test
    public void editingSucceedsWithBlankEmail()
    {
        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");

        //before editing
        assertEquals("unknown@cia.gov",data.getEmailAddress());

        assertDoesNotThrow(() -> data.setEmailAddress(""));

        //after editing
        assertEquals("", data.getEmailAddress());
    }

    @Test
    public void editingSucceedsWithBlankFirstName()
    {
        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");

        //before editing
        assertEquals("Padmasree",data.getFirstName());

        assertDoesNotThrow(() -> data.setFirstName(""));

        //after editing
        assertEquals("", data.getFirstName());
    }

    @Test
    public void editingSucceedsWithBlankLastName()
    {
        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");

        //before editing
        assertEquals("Murugan",data.getLastName());

        assertDoesNotThrow(() -> data.setLastName(""));

        //after editing
        assertEquals("", data.getLastName());
    }

    @Test
    public void editingSucceedsWithBlankPhone()
    {
        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");

        //before editing
        assertEquals("123-456-7891",data.getPhone());

        assertDoesNotThrow(() -> data.setPhone(""));

        //after editing
        assertEquals("", data.getPhone());
    }

    /* The below four tests edits a blank value to a valid value*/

    @Test
    public void editingSucceedsWithBlankEmailBlankToValid()
    {
        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "");

        //before editing
        assertEquals("", data.getEmailAddress());

        assertDoesNotThrow(() -> data.setEmailAddress("unknown@cia.gov"));

        //after editing
        assertEquals("unknown@cia.gov",data.getEmailAddress());
    }

    @Test
    public void editingSucceedsWithBlankFirstNameBlankToValid()
    {
        ContactItem data = new ContactItem("", "Murugan", "123-456-7891", "unknown@cia.gov");

        //before editing
        assertEquals("", data.getFirstName());

        assertDoesNotThrow(() -> data.setFirstName("Padmasree"));

        //after editing
        assertEquals("Padmasree",data.getFirstName());
    }

    @Test
    public void editingSucceedsWithBlankLastNameBlankToValid()
    {
        ContactItem data = new ContactItem("Padmasree", "", "123-456-7891", "unknown@cia.gov");

        //before editing
        assertEquals("", data.getLastName());

        assertDoesNotThrow(() -> data.setLastName("Murugan"));

        //after editing
        assertEquals("Murugan",data.getLastName());
    }

    @Test
    public void editingSucceedsWithBlankPhoneBlankToValid()
    {
        ContactItem data = new ContactItem("Padmasree", "Murugan", "", "unknown@cia.gov");

        //before editing
        assertEquals("", data.getPhone());

        assertDoesNotThrow(() -> data.setPhone("123-456-7891"));

        //after editing
        assertEquals("123-456-7891",data.getPhone());
    }

    @Test
    public void editingSucceedsWithNonBlankValues()
    {
        ContactItem data = new ContactItem("Albert", "Einstein", "944-375-5449", "alberteinstein@cia.gov");

        //Before editing
        assertEquals("Albert",data.getFirstName());
        assertEquals("Einstein",data.getLastName());
        assertEquals("944-375-5449",data.getPhone());
        assertEquals("alberteinstein@cia.gov",data.getEmailAddress());

        assertDoesNotThrow(() -> data.setContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov"));

        //after editing
        assertEquals("Padmasree",data.getFirstName());
        assertEquals("Murugan",data.getLastName());
        assertEquals("123-456-7891",data.getPhone());
        assertEquals("unknown@cia.gov",data.getEmailAddress());
    }

    @Test
    public void testToString()
    {
        String firstName = "Padmasree";
        String lastName = "Murugan";
        String phone = "123-456-7891";
        String emailAddress = "unknown@cia.gov";

        ContactItem data = new ContactItem(firstName, lastName, phone, emailAddress);

        assertEquals(data.toString(), "Padmasree"+ " " + "Murugan");
    }


}