public class Encrypter {

    private String givenInput;

    public String encrypt(String givenInput)
    {
        this.givenInput = givenInput;

        int integers;
        int[] integerArray;
        String[] encryptedString;
        String encryptedValue;

        integerArray = new int[givenInput.length()];
        encryptedString = new String[givenInput.length()];

        integers = Integer.parseInt(givenInput);

        for(int i = givenInput.length()-1; i >= 0; i--)
        {
            integerArray[i] = integers % 10;
            integers  /= 10;
        }

        add7(integerArray);
        moduloBy10(integerArray);
        swapFirstThird(integerArray);
        swapSecondFourth(integerArray);

        encryptedValue = "";

        for(int i = 0; i < givenInput.length(); i++)
        {
            encryptedString[i] = String.valueOf(integerArray[i]);
            encryptedValue = encryptedValue + encryptedString[i];
        }

        return encryptedValue;
    }

    public void add7(int[] integerArray)
    {
        for(int i = givenInput.length() - 1; i >= 0; i--)
        {
            integerArray[i] = integerArray[i] + 7;
        }
    }

    public void moduloBy10 (int[] integerArray)
    {
        for(int i = givenInput.length() - 1; i >= 0; i--)
        {
            integerArray[i] = integerArray[i] % 10;
        }
    }

    public void swapFirstThird(int[] integerArray)
    {
        int temp = integerArray[0];
        integerArray[0] = integerArray[2];
        integerArray[2] = temp;
    }

    public void swapSecondFourth(int[] integerArray)
    {
        int temp = integerArray[1];
        integerArray[1] = integerArray[3];
        integerArray[3] = temp;
    }
}

