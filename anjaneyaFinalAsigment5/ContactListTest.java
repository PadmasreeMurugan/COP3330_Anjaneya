import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest
{
    /*Test 1*/
    @Test
    public void addingItemsIncreasesSize()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts.addContactItem(data);

        assertEquals(1, contacts.sizeOfContactList());

        ContactItem data1 = new ContactItem("Albert", "Einstein", "198-765-4321", "pm20@knights.ucf.edu");
        contacts.addContactItem(data1);

        assertEquals(2, contacts.sizeOfContactList());
    }

    /*Test 2*/
    @Test
    public void editingItemsFailsWithAllBlankValues()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts.addContactItem(data);

        assertThrows(InvalidContactItemException.class, () -> contacts.editContactItem("", "", "", "", 0));
    }

    /*Test 3*/
    @Test
    public void editingItemsFailsWithInvalidIndex()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts.addContactItem(data);

        assertThrows(InvalidIndexException.class, () -> contacts.editContactItem("Albert", "Einstein", "198-765-4321", "pm20@knights.ucf.edu", 5));
    }

    /*Test 4*/
    @Test
    public void editingSucceedsWithBlankFirstName()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts.addContactItem(data);

        assertDoesNotThrow(() -> contacts.editContactItem("", "Einstein", "198-765-4321", "pm20@knights.ucf.edu", 0));

        /*editing only by calling the first name of the contact item*/
        ContactList contacts1 = new ContactList();

        ContactItem data1 = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts1.addContactItem(data1);

        assertDoesNotThrow(() ->contacts.editContactFirstName("", 0));

        assertEquals("", contacts.getContactFirstName(0));
    }

    @Test
    /*Test 5*/
    public void editingSucceedsWithBlankLastname()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts.addContactItem(data);

        assertDoesNotThrow(() -> contacts.editContactItem("Albert", "", "198-765-4321", "pm20@knights.ucf.edu", 0));

        /*editing only by calling the last name of the contact item*/
        ContactList contacts1 = new ContactList();

        ContactItem data1 = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts1.addContactItem(data1);

        assertDoesNotThrow(() ->contacts.editContactLastName("", 0));

        assertEquals("", contacts.getContactLastName(0));
    }

    @Test
    /*Test 6*/
    public void editingSucceedsWithBlankPhone()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts.addContactItem(data);

        assertDoesNotThrow(() -> contacts.editContactItem("Albert", "Einstein", "", "pm20@knights.ucf.edu", 0));

        /*editing only by calling the last name of the contact item*/
        ContactList contacts1 = new ContactList();

        ContactItem data1 = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts1.addContactItem(data1);

        assertDoesNotThrow(() ->contacts.editContactPhone("", 0));

        assertEquals("", contacts.getContactPhoneNumber(0));
    }

    @Test
    /*editingSucceedsWithBlankEmailAddress()*/
    /*Test 7*/
    public void editingSucceedsWithBlankEmailAddress()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts.addContactItem(data);

        assertDoesNotThrow(() -> contacts.editContactItem("Albert", "Einstein", "198-765-4321", "", 0));

        /*editing only by calling the last name of the contact item*/
        ContactList contacts1 = new ContactList();

        ContactItem data1 = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts1.addContactItem(data1);

        assertDoesNotThrow(() ->contacts.editContactEmailAddress("", 0));

        assertEquals("", contacts.getContactEmailAddress(0));
    }

    @Test
    /*Test 8*/
    public void editingSucceedsWithNonBlankValues()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "", "");
        contacts.addContactItem(data);

        assertDoesNotThrow(() -> contacts.editContactItem("Albert", "Einstein", "123-456-7891", "pm20@knights.ucf.edu", 0));
    }

    @Test
    /*Test 9*/
    public void newListIsEmpty()
    {
        ContactList contacts = new ContactList();
        assertEquals(true, contacts.newListIsEmpty());

    }

    @Test
    /*Test 10*/
    public void removingItemsDecreasesSize()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "unknown@cia.gov");
        contacts.addContactItem(data);

        assertEquals(1, contacts.sizeOfContactList());

        contacts.remove(data);

        assertEquals(0,contacts.sizeOfContactList());
    }

    @Test
    /*Test 11*/
    //passing negative value
    public void removingItemsFailsWithInvalidIndex()
    {
        ContactList contacts = new ContactList();

        assertThrows(InvalidIndexException.class, () -> contacts.removeContactItem(-2));
    }

    @Test
    /*Test 12*/
    public void savedContactListCanBeLoaded()
    {
        ContactList contacts = new ContactList();

        ContactItem data = new ContactItem("Padmasree", "Murugan", "123-456-7891", "pm20@knights.ucf.edu");
        contacts.addContactItem(data);

        contacts.writeContactdata("FileCreatedToCheckWhetherSavedContactListcanBeLoaded.txt");

        /*AFTER WRITING TO THE FILE,REMOVING THE CONTACT ITEM FROM THE CONTACTLIST */
        contacts.remove(data);

        assertDoesNotThrow(() -> contacts.readContacts("FileCreatedToCheckWhetherSavedContactListcanBeLoaded.txt"));

        //IF THE FILE WAS LOADED AND READ SUCCESSFULLY, THEN THE CONTACT ITEM WILL BE STORED IN FIRST(0) INDEX OF THE CONTACTLIST*/
        assertEquals("Padmasree", contacts.getContactFirstName(0));
        assertEquals("Murugan", contacts.getContactLastName(0));
        assertEquals("123-456-7891", contacts.getContactPhoneNumber(0));
        assertEquals("pm20@knights.ucf.edu", contacts.getContactEmailAddress(0));
    }
}