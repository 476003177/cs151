public class Prog1
{
    public static void main(String[] args) throws Exception
    {
        Person fred = new Person("Fred");
        Person barney = new Person("Barney");
        fred.befriend(barney);
        barney.befriend(fred);
        Person cloneOfFred = fred.clone(); // Does not compile because clone isn't implemented
        System.out.println(cloneOfFred);
    }
}
