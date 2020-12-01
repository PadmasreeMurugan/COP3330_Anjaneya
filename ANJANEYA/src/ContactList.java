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

    //getting the size of the tasks
    public int sizeOfContactList()
    {
        return contacts.size();
    }

    //checking whether the list is empty
    public boolean newListIsEmpty()
    {
        return contacts.isEmpty();
    }

    //removing a task item from the list of tasks
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

    //displaying the current tasks
    public void displayCurrentContacts()
    {
        System.out.println("Current Contacts");
        System.out.println("--------------");

        if(newListIsEmpty())
        {
            System.out.println("Your contact list is empty. Please add an contact item\n");
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
            System.out.println("Your contact list is empty. Please add an contact item\n");
        }
    }

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

            System.out.println("Warning: Your first Name is invalid; contact not created \n");

        } catch (InvalidLastNameException ex)
        {
            contacts.remove(data);

            System.out.println("Warning: Your last name is invalid; contact not created \n");

        } catch (InvalidContactItemException ex)
        {
            contacts.remove(data);

            System.out.println("Warning: Your contact item should contain atleast one of the four details; contact not created \n");

        }
        catch(InvalidPhoneNumberException ex)
        {
            System.out.println("Warning: Your phone number is invalid; contact not created\n");
        }
        return data;
    }

    //store contact item
    public void addContactItem(ContactItem data)
    {
        contacts.add(data);
    }

    public void editAnItem(String firstName, String lastName, String phone, String emailAddress, int index)
    {
        try
        {
            editContactItem(firstName, lastName, phone, emailAddress, index);
        }
        catch (InvalidFirstNameException ex)
        {
            System.out.println("Warning: Your first name is invalid; contact not edited\n");
        }
        catch (InvalidLastNameException ex)
        {
            System.out.println("Warning: Your last name is invalid; contact not edited\n");
        }
        catch(InvalidPhoneNumberException ex)
        {
            System.out.println("Warning: Your phone number is invalid; contact not edited\n");
        }
        catch (InvalidContactItemException ex)
        {
            System.out.println("Warning: Your edited contact item must contain atleast one of the four details; contact not edited");
        }

    }

    public void editContactItem(String firstName, String lastName, String phone, String emailAddress, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setContactItem(firstName, lastName, phone, emailAddress);
        }
        else throw new InvalidContactIndexException("Index is Invalid");
    }

    public void editContactFirstName(String firstName, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setFirstName(firstName);
        }
        else throw new InvalidContactIndexException("Index is Invalid");
    }

    public void editContactLastName(String lastName, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setLastName(lastName);
        }
        else throw new InvalidContactIndexException("Index is Invalid");
    }

    public void editContactPhone(String phone, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setPhone(phone);
        }
        else throw new InvalidContactIndexException("Index is Invalid");
    }

    public void editContactEmailAddress(String emailAddress, int userIndex)
    {
        if(indexIsValid(userIndex))
        {
            ContactItem data = contacts.get(userIndex);

            data.setEmailAddress(emailAddress);
        }
        else throw new InvalidContactIndexException("Index is Invalid");
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
        else throw new InvalidContactIndexException("Index is Invalid");
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
        else throw new InvalidContactIndexException("Index is Invalid");
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
        else throw new InvalidContactIndexException("Index is Invalid");
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
        else throw new InvalidContactIndexException("Index is Invalid");
        return email;
    }

    public void removeContactItem(int userIndex)
    {
        if(indexIsValid(userIndex) && (!newListIsEmpty()))
        {
            remove(contacts.get(userIndex));
        }
        else throw new InvalidContactIndexException("Index is not valid");
    }

    public void removeAllContactItem()
    {
        contacts.removeAll(contacts);
    }

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
            System.out.println("Warning: Invalid filename. File cannot be saved");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
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
            System.out.println("Warning: Invalid filename. file cannot be loaded");
        }
    }

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
}
class InvalidContactIndexException extends IndexOutOfBoundsException
{
    public InvalidContactIndexException(String msg)
    {
        super(msg);
    }
}
