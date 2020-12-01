public class ContactItem
{
    String firstName;
    String lastName;
    String phone;
    String emailAddress;

    //constructor
    public ContactItem(String firstName, String lastName, String phone, String emailAddress)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.emailAddress = emailAddress;

        setContactItem(firstName, lastName, phone, emailAddress);
    }

    //setting the first name, method called when first name value is edited
    public void setFirstName(String firstName)
    {
        if(isFirstNameValid(firstName))
        {
            this.firstName = firstName;
        }
        else throw new InvalidFirstNameException("first name is not valid");
    }

    //setting the last name, method called when last name value is edited
    public void setLastName(String lastName)
    {
        if(isLastNameValid(lastName))
        {
            this.lastName = lastName;
        }
        else throw new InvalidLastNameException("last name is not valid");
    }

    //setting the phone number, method called when phone number value is edited
    public void setPhone(String phone)
    {
        if(isPhoneNumberValid(phone))
        {
            this.phone = phone;
        }
        else throw new InvalidPhoneNumberException("phone number is not valid");
    }

    //setting the email address, method called when email address value is edited
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    //checking if first name is valid
    private boolean isFirstNameValid(String firstName)
    {
        if(!(firstName.isEmpty()))
        {
            for(int i = 0; i < firstName.length(); i++)
            {
                char name = firstName.charAt(i);
                char space = ' ';

                if(! ((name >= 'A' && name <= 'Z') || (name >= 'a' && name <= 'z') || (name == ' ')))
                {
                    return false;
                }
            }
        }
        return true;
    }

    //checking if lastname is valid
    private boolean isLastNameValid(String lastName)
    {
        if(!(lastName.isEmpty()))
        {
            for(int i = 0; i < lastName.length(); i++)
            {
                char name = lastName.charAt(i);

                if(! ((name >= 'A'&& name <= 'Z') || (name >= 'a' && name <= 'z') || (name == ' ')))
                {
                    return false;
                }
            }
        }
        return true;
    }

    //checking if phone number is valid
    private boolean isPhoneNumberValid(String phone)
    {
        if(!(phone.isEmpty()))
        {
            if((phone.length() < 12) || (phone.length() > 12))
            {
                return false;
            }
            for(int i = 0; i < phone.length(); i++)
            {
                char number = phone.charAt(i);

                if(! ((number >= '0'&& number <= '9') || (number == '-')))
                {
                    return false;
                }
            }
        }
        return true;

    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    //returns the all the values in one contact item as a string
    public String toString(String firstName, String lastName, String phone, String emailAddress)
    {
        return firstName + lastName + phone + emailAddress;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

    public void setContactItem(String firstName, String lastName, String phone, String emailAddress)
    {
        if(isContactItemValid(firstName, lastName, phone, emailAddress))
        {
            setFirstName(firstName);
            setLastName(lastName);
            setPhone(phone);
            setEmailAddress(emailAddress);
        }
        else throw new InvalidContactItemException("contact item not valid");
    }

    public boolean isContactItemValid(String firstName, String lastName, String phone, String emailAddress)
    {
        if(!(toString(firstName, lastName, phone, emailAddress).isEmpty()))
        {
            return true;
        }
        return false;
    }
}

class InvalidContactItemException extends IllegalArgumentException
{
    public InvalidContactItemException(String message)
    {
        super(message);
    }
}
class InvalidFirstNameException extends IllegalArgumentException
{
    public InvalidFirstNameException(String message)
    {
        super(message);
    }
}
class InvalidLastNameException extends IllegalArgumentException
{
    public InvalidLastNameException(String message)
    {
        super(message);
    }
}

class InvalidPhoneNumberException extends IllegalArgumentException
{
    public InvalidPhoneNumberException(String message)
    {
        super(message);
    }
}
