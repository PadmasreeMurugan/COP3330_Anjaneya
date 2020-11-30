import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContactApp extends MainApp
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

    //Main function
    public static void main(String[] args)
    {
        ContactApp contact = new ContactApp();
        contact.processMainMenu();
    }

    //processes main menu options
    private void processMainMenu()
    {
        int userResponse = getContinueResponseForMain();

        while(shouldContinue(userResponse))
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
            input.nextLine();
        }
    }

    private String getFirstName()
    {
        System.out.println("First name: ");
        return input.nextLine();
    }

    private String getLastname()
    {
        System.out.println("Last name: ");
        return input.nextLine();
    }

    private String getPhoneNumber()
    {
        System.out.println("Phone number (xxx-xxx-xxxx): ");
        return input.nextLine();
    }

    private String getEmailAddress()
    {
        System.out.println("Email address (x@y.z): ");
        return input.nextLine();
    }

    //display List Opeartion Menu
    private void displayListOperationMenu()
    {
        System.out.println("List Operation Menu");
        System.out.println("--------------------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) save the current list");
        System.out.println("6) quit to the main menu");
    }

    //get user input for list operation menu
    private int getContinueResponseForListMenu()
    {
        displayListOperationMenu();
        return input.nextInt();
    }

    //user response for list operation menu menu is not equal to 8, then continue
    private boolean shouldContinueList (int userResponse)
    {
        return (userResponse != 6);
    }


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
        System.out.println("Which contact will you edit?");
        return input.nextInt();
    }

    private int getRemoveIndex()
    {
        System.out.println("Which contact will you remove?");
        return input.nextInt();
    }

    private String getFileNameToSave()
    {
        System.out.println("Enter the filename to save as: ");
        return input.next();
    }

    private String getFileNameToLoad()
    {
        System.out.println("Enter the filename to load : ");
        return input.next();
    }

    private void createNewList()
    {
        int response = getContinueResponseForListMenu();
        input.nextLine();

        while(shouldContinueList(response))
        {
            if(response == 1)
            {
                displayCurrentContacts();
            }

            if(response == 2)
            {
                String firstName = getFirstName();
                String lastName = getLastname();
                String phone = getPhoneNumber();
                String emailAddress = getEmailAddress();

                addContactItem(firstName, lastName, phone, emailAddress);
            }
            if(response == 3)
            {
                editContacts();
            }

            if(response == 4)
            {
                removeContacts();
            }

            if(response == 5)
            {
                saveTheList();
            }
            response = getContinueResponseForListMenu();
            input.nextLine();
        }
    }

    private void displayCurrentContacts()
    {
        listOfContacts.displayCurrentContacts();
    }

    private void addContactItem(String firstname, String lastname, String phone, String emailAddress)
    {
        listOfContacts.addingContactItem(firstname, lastname, phone, emailAddress);
    }

    private void editContacts()
    {
        String firstName = "";
        String lastName = "";
        String phone = "";
        String emailAddress = "";

        try
        {
            if(listOfContacts.newListIsEmpty())
            {
                System.out.println("Warning: Your list is empty. Please add an item to edit");
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
            System.out.println("Your index is invalid; no contact can be edited");
        }
    }

    private void removeContacts()
    {
        int size = listOfContacts.sizeOfContactList();

        try {
            if (listOfContacts.newListIsEmpty()) {
                System.out.println("Your contact list is empty.Please add an contact item to remove");

            } else {
                int index = getRemoveIndex();
                listOfContacts.removeContactItem(index);
            }
        }
        catch(InvalidIndexException ex)
        {
            System.out.println("Warning: Your index is invalid; no contact can be removed");
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

            createNewList();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Warning: Invalid filename. file cannot be loaded");
        }
    }

    private void saveTheList()
    {
        if(listOfContacts.newListIsEmpty())
        {
            System.out.println("Your contact list should contain one or more contact items ");
            System.out.println("contact list cannot be saved");
        }
        else
        {
            String filename = getFileNameToSave();
            listOfContacts.writeContactdata(filename);
            System.out.println("contact list has been saved\n");
        }
    }

    private void loadExistingList()
    {
        listOfContacts.removeAllContactItem();
        String fileName = getFileNameToLoad();
        readContacts(fileName);
    }

}
