import java.security.PublicKey;

class Racquet
{
    //fields
    int length;
    int weight;

    //default constructor
    public Racquet()
    {
        length = 20;
        weight = 200;

    }

    //parameterized constructors
    public Racquet(int length, int weight)
    {
        this.length = length;
        this.weight = weight;
    }

    //method
    public void swingRacquet()
    {
        System.out.println("Padmasree is swinging the racquet");
    }


}

public class Main
{
    public static void main (String[] args)
    {
        System.out.println("First discussion");

        //methods, objects, constructors

        //1) what is the use of constructor?

        //2) what is the use of THIS keyword?

        //3) what is SDK (software development kit)?

        Racquet Wilson = new Racquet(26, 260);

        Racquet Yonex = new Racquet();

        System.out.println(Wilson.length);
        System.out.println(Yonex.length);

        //1) allocates memory for the object
        //2) it initializes default value





    }

}
