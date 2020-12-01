import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContactApp extends TaskApp
{
    //declaring scanner object
    private static Scanner input = new Scanner(System.in);

    //declaring ContactList array
    private ContactList listOfContacts;

    //constructor for initiating tasks object
    public ContactApp()
    {
        listOfContacts = new ContactList();
    }

    //display main menu  - inherited from TaskApp (which extends App) class
    //get response for main menu - inherited from TaskApp (which extends App) class
    //check should continue for main menu - inherited from TaskApp (which extends App) class

    /* POLYMORPHISM APPLIED */

    //processes main menu options
    public void processMainMenu()
    {
        int userResponse = getContinueResponseForMain();

        while(shouldContinueForMainMenu(userResponse))
        {
            if(userResponse == 1)
            {
                System.out.println("new contact list has been created");
                listOfContacts.removeAllContactItem();
                createNewList();
            }
            if(userResponse == 2)
            {
                loadExistingList();
            }
            userResponse = getContinueResponseForMain();
        }
    }

    /* POLYMORPHISM APPLIED */

    //display List Opeartion Menu
    public void displayListOperationMenu()
    {
        System.out.println("\nList Operation Menu");
        System.out.println("--------------------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) save the current list");
        System.out.println("6) quit to the main menu\n");
        System.out.print("> ");
    }

    //getContinueResponseForListMenu - inherited from TaskApp (which extends App) class

    /* POLYMORPHISM APPLIED */

    //user response for list operation menu menu is not equal to 6, then continue
    public boolean shouldContinueList (int userResponse)
    {
        return (userResponse != 6);
    }

    /*Create a new list*/

    private void createNewList()
    {
        int response = getContinueResponseForListMenu();

        while(shouldContinueList(response))
        {
            if(response == 1)
            {
                viewCurrentList();
            }
            if(response == 2)
            {
                String firstName = getFirstName();
                String lastName = getLastname();
                String phone = getPhoneNumber();
                String emailAddress = getEmailAddress();

                addItem(firstName, lastName, phone, emailAddress);
            }
            if(response == 3)
            {
                editItem();
            }
            if(response == 4)
            {
                removeItem();
            }
            if(response == 5)
            {
                saveTheList();
            }
            response = getContinueResponseForListMenu();
        }
    }

    /*Option 1) view the list: List Operation Menu*/
    public void viewCurrentList()
    {
        listOfContacts.displayCurrentContacts();
    }

    /*Option 2) add an item: List Operation Menu*/

    //getting task firstname, lastname, phoneNumber, emailAddress from user
    private String getFirstName()
    {
        System.out.print("First name: ");
        return input.nextLine();
    }

    private String getLastname()
    {
        System.out.print("Last name: ");
        return input.nextLine();
    }

    private String getPhoneNumber()
    {
        System.out.print("Phone number (xxx-xxx-xxxx): ");
        return input.nextLine();
    }

    private String getEmailAddress()
    {
        System.out.print("Email address (x@y.z): ");
        return input.nextLine();
    }

    public void addItem(String firstname, String lastname, String phone, String emailAddress)
    {
        listOfContacts.addingContactItem(firstname, lastname, phone, emailAddress);
    }

    /*Option 3) Edit an item: List Operation menu*/

    //getting new contact firstname, lastname, phone number, emailAddress from user for editing the tasks
    private String getNewFirstName(int userIndex)
    {
        System.out.printf("Enter a new first name for contact  %d: ", userIndex);
        return input.nextLine();
    }

    private String getNewLastName(int userIndex)
    {
        System.out.printf("Enter a new last name for contact %d: ", userIndex);
        return input.nextLine();
    }

    private String getNewPhone(int userIndex)
    {
        System.out.printf("Enter a new phone number (xxx-xxx-xxxx) for contact %d: ", userIndex);
        return input.nextLine();
    }

    private String getNewEmailAddress(int userIndex)
    {
        System.out.printf("Enter a new email address (x@y.z) for contact %d: ", userIndex);
        return input.nextLine();
    }

    private int getEditIndex()
    {
        System.out.print("\nWhich contact will you edit? ");
        return input.nextInt();
    }

    public void editItem()
    {
        String firstName = "";
        String lastName = "";
        String phone = "";
        String emailAddress = "";

        try
        {
            if(listOfContacts.newListIsEmpty())
            {
                System.out.println("Warning: contact list is empty; please add an item to edit");
            }

            else
            {
                listOfContacts.displayCurrentContacts();

                int index = getEditIndex();
                input.nextLine();

                if(listOfContacts.indexIsValid(index))
                {
                    firstName = getNewFirstName(index);
                    lastName = getNewLastName(index);
                    phone = getNewPhone(index);
                    emailAddress = getNewEmailAddress(index);
                }

                listOfContacts.editAnItem(firstName, lastName, phone,  emailAddress, index);
            }
        }
        catch(InvalidIndexException ex)
        {
            System.out.println("Warning: index is invalid; no contact can be edited");
        }
    }

    /*Option 4) Remove an Item: List Operation Menu*/

    //getting index of the task to be removed from the user
    private int getRemoveIndex()
    {
        System.out.print("\nWhich contact will you remove? ");
        return input.nextInt();
    }

    public void removeItem()
    {
        int size = listOfContacts.sizeOfContactList();

        try {
            if (listOfContacts.newListIsEmpty()) {
                System.out.println("Warning: contact list is empty; please add a contact item to remove");

            } else {
                listOfContacts.displayCurrentContacts();
                int index = getRemoveIndex();
                input.nextLine();
                listOfContacts.removeContactItem(index);
            }
        }
        catch(InvalidIndexException ex)
        {
            System.out.println("Warning: index is invalid; no contact can be removed");
        }
    }

    /* option5: save the current list*/

    //getFileNameToSave - inherited from TaskApp (which extends App) class

    //writing taskItem data to a file and saving the file
    public void writeToFileAndSaveTheList()
    {
        if(listOfContacts.newListIsEmpty())
        {
            System.out.println("Warning: contact list should contain one or more contact items; contact list cannot be saved");
        }
        else
        {
            String filename = getFileNameToSave();
            listOfContacts.writeContactdata(filename);
            System.out.println("contact list has been saved");
        }
    }

    private void saveTheList()
    {
        writeToFileAndSaveTheList();
    }

    /* Main Menu option2) Loading a list*/

    //getFileNameToLoad - inherited from TaskApp (which extends App) class

    public void loadDataFromFile()
    {
        try
        {
            listOfContacts.removeAllContactItem();

            if(listOfContacts.newListIsEmpty())
            {
                String fileName = getFileNameToLoad();
                readContacts(fileName);
            }
            else
            {
                listOfContacts.removeAllContactItem();
                String fileName = getFileNameToLoad();
                readContacts(fileName);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Please load the file consisting of contact list; file cannot be loaded ");
        }

    }

    private void readContacts(String fileName)
    {
        try
        {
            File input = new File(fileName);
            Scanner scanner = new Scanner(input);

            ContactApp contactApp = new ContactApp();

            listOfContacts.readContactsLineByLine(scanner);

            System.out.println("contact list has been loaded");
            createNewList();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Warning: invalid filename. file cannot be loaded");
        }
    }

    private void loadExistingList()
    {
        loadDataFromFile();

    }
}
