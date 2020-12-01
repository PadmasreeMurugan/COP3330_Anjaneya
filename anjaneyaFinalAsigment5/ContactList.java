import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ContactList
{
    //creating an arraylist of task item objects
    ArrayList<ContactItem> contacts;

    //constructor
    public ContactList() {
        contacts = new ArrayList<>();
    }

    //getting the size of the contacts
    public int sizeOfContactList()
    {
        return contacts.size();
    }

    //checking whether the list is empty
    public boolean newListIsEmpty()
    {
        return contacts.isEmpty();
    }

    //removing a task item from the list of contacts
    public void remove(ContactItem data)
    {
        contacts.remove(data);
    }

    //checking whether the index is valid
    public boolean indexIsValid(int userIndex)
    {
        return (userIndex >= 0 && userIndex < contacts.size());
    }

    //creating a new contact list

    /* OPTION1: VIEW THE LIST*/

    //displaying the current contacts
    public void displayCurrentContacts()
    {
        System.out.println("Current Contacts");
        System.out.println("--------------");

        if(newListIsEmpty())
        {
            System.out.println("Warning: contact list is empty; please add a contact item");
        }
        try
        {
            if(sizeOfContactList() >= 1)
            {
                for (int i = 0; i < contacts.size(); i++)
                {
                    ContactItem data = contacts.get(i);

                    System.out.format("%d) Name: %s %n", i, data.toString());
                    System.out.format("Phone: %s %n", data.getPhone());
                    System.out.format("Email: %s %n", data.getEmailAddress());
                }
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("Warning: contact list is empty; please add a contact item");
        }
    }

    /*OPTION2: ADD AN ITEM*/

    //getting an contact item
    public ContactItem addingContactItem(String firstName, String lastName, String phone, String emailAddress)
    {
        ContactItem data = null;

        try
        {
            data = new ContactItem(firstName, lastName, phone, emailAddress);
            addContactItem(data);

        }
        catch (InvalidFirstNameException ex)
        {
            contacts.remove(data);

            System.out.println("Warning: first Name is invalid; contact not created");

        } catch (InvalidLastNameException ex)
        {
            contacts.remove(data);

            System.out.println("Warning: last name is invalid; contact not created");

        } catch (InvalidContactItemException ex)
        {
            contacts.remove(data);

            System.out.println("Warning: contact item should contain atleast one of the four details; contact not created \n");

        }
        catch(InvalidPhoneNumberException ex)
        {
            System.out.println("Warning: phone number is invalid; contact not created");
        }
        return data;
    }

    //store contact item
    public void addContactItem(ContactItem data)
    {
        contacts.add(data);
    }

    /*OPTION3: EDIT AN ITEM*/

    //editing a contact item
    public void editAnItem(String firstName, String lastName, String phone, String emailAddress, int index)
    {
        try
        {
            editContactItem(firstName, lastName, phone, emailAddress, index);
        }
        catch (InvalidFirstNameException ex)
        {
            System.out.println("Warning: first name is invalid; contact not edited");
        }
        catch (InvalidLastNameException ex)
        {
            System.out.println("Warning: last name is invalid; contact not edited");
        }
        catch(InvalidPhoneNumberException ex)
        {
            System.out.println("Warning: phone number is invalid; contact not edited");
        }
        catch (InvalidContactItemException ex)
        {
            System.out.println("Warning: edited contact item must contain atleast one of the four details; contact not edited");
        }
    }

    //edit entire contact item
    public void editContactItem(String firstName, String lastName, String phone, String emailAddress, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setContactItem(firstName, lastName, phone, emailAddress);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    //edit contact first name
    public void editContactFirstName(String firstName, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setFirstName(firstName);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    //edit contact last name
    public void editContactLastName(String lastName, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setLastName(lastName);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    //edit contact phone
    public void editContactPhone(String phone, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setPhone(phone);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    //edit contact email Address
    public void editContactEmailAddress(String emailAddress, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setEmailAddress(emailAddress);
        }
        else throw new InvalidIndexException("Index is Invalid");
    }

    /*OPTION 4: REMOVE AN ITEM*/

    //checking whether the index is valid and performing the remove operation
    public void removeContactItem(int userIndex)
    {
        if(indexIsValid(userIndex) && (!newListIsEmpty()))
        {
            remove(contacts.get(userIndex));
        }
        else throw new InvalidIndexException("Index is not valid");
    }

    public void removeAllContactItem()
    {
        contacts.removeAll(contacts);
    }

    //getting contact first name
    public String getContactFirstName(int userIndex)
    {
        String firstName = "";

        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            if(!(data.getFirstName().isEmpty()))
            {
                firstName = data.getFirstName();
            }
        }
        else throw new InvalidIndexException("Index is Invalid");
        return firstName;
    }

    //getting contact last name
    public String getContactLastName(int userIndex)
    {
        String lastName = "";

        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            if(!(data.getFirstName().isEmpty()))
            {
                lastName = data.getLastName();
            }
        }
        else throw new InvalidIndexException("Index is Invalid");
        return lastName;
    }

    //getting contact phone number
    public String getContactPhoneNumber(int userIndex)
    {
        String phone = "";

        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            if(!(data.getFirstName().isEmpty()))
            {
                phone = data.getPhone();
            }
        }
        else throw new InvalidIndexException("Index is Invalid");
        return phone;
    }

    //getting contact email address
    public String getContactEmailAddress(int userIndex)
    {
        String email = "";

        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            if(!(data.getFirstName().isEmpty()))
            {
                email = data.getEmailAddress();
            }
        }
        else throw new InvalidIndexException("Index is Invalid");
        return email;
    }

    /*OPTION 5: SAVE THE LIST*/

    public void writeContactdata(String fileName)
    {
        try(Formatter output = new Formatter(fileName))
        {
            for (int i = 0; i < contacts.size(); i++)
            {
                ContactItem data = contacts.get(i);

                output.format("%s;%s;%s;%s;%n",data.getFirstName(),data.getLastName(), data.getPhone(), data.getEmailAddress());
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Warning: invalid filename. file cannot be saved");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /*Main Menu: OPTION 2: LOAD AN EXISTING LIST*/
    public void readContactsLineByLine(Scanner scanner)
    {
        while (scanner.hasNextLine())
        {
            String contactData = scanner.nextLine();
            String[] values = contactData.split(";");

            String firstName = "";
            String lastName = "";
            String phone = "";
            String email = "";

            if (values.length == 1)
            {
                firstName = values[0];
            }

            else if (values.length == 2)
            {
                firstName = values[0];
                lastName = values[1];
            }

            else if(values.length == 3)
            {
                firstName = values[0];
                lastName = values[1];
                phone = values[2];
            }

            else if(values.length == 4)
            {
                firstName = values[0];
                lastName = values[1];
                phone = values[2];
                email = values[3];
            }

            ContactItem loadcontact = new ContactItem(firstName, lastName, phone, email);

            addContactItem(loadcontact);
        }
    }

    public void readContacts(String fileName)
    {
        try
        {
            File input = new File(fileName);
            Scanner scanner = new Scanner(input);

            ContactApp contactApp = new ContactApp();

            readContactsLineByLine(scanner);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Warning: invalid filename. file cannot be loaded");
        }
    }


}

class InvalidIndexException extends IndexOutOfBoundsException
{
    public InvalidIndexException(String msg)
    {
        super(msg);
    }
}

