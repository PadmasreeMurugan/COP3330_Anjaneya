public class Decrypter
{
    private String givenInput;

    public String decrypt(String givenInput)
    {
        this.givenInput = givenInput;

        int integers;
        int[] integerArray;
        String[] decryptedString;
        String decryptedValue;

        integerArray = new int[givenInput.length()];
        decryptedString = new String[givenInput.length()];

        integers = Integer.parseInt(givenInput);

        for(int i = givenInput.length()-1; i >= 0; i--)
        {
            integerArray[i] = integers % 10;
            integers  /= 10;
        }

        swapFirstThird(integerArray);
        swapSecondFourth(integerArray);
        performDecryption(integerArray);

        decryptedValue ="";

        for(int i = 0; i < givenInput.length(); i++)
        {
            decryptedString[i] = String.valueOf(integerArray[i]);
            decryptedValue= decryptedValue + decryptedString[i];
        }

        return decryptedValue;
    }

    public void performDecryption(int[] integerArray)
    {
        for(int i = 0; i < givenInput.length(); i++)
        {
            if(integerArray[i] >= 7)
            {
                integerArray[i] = deduct7(integerArray[i]);
            }

            else if(integerArray[i] < 7)
            {
                integerArray[i] = add10(integerArray[i]);
                integerArray[i] = deduct7(integerArray[i]);
            }
        }
    }

    public int deduct7(int integer)
    {
        integer = integer - 7;
        return integer;
    }

    public int add10 (int integer)
    {
        integer = integer + 10;
        return integer;
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
